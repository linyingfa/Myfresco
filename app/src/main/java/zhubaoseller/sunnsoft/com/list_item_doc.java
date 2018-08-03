package zhubaoseller.sunnsoft.com;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import zhubaoseller.sunnsoft.DateUtil;

/**
 * Created by xiaolin on 2018/8/3.
 */
public class list_item_doc extends AppCompatActivity {
	// 下载失败
	public static final int DOWNLOAD_ERROR = 2;
	// 下载成功
	public static final int DOWNLOAD_SUCCESS = 1;
	private static final int REQUEST_EXTERNAL_STORAGE = 1;
	private static String[] PERMISSIONS_STORAGE = {
			"android.permission.READ_EXTERNAL_STORAGE",
			"android.permission.WRITE_EXTERNAL_STORAGE"};
	/**
	 * 下载完成后  直接打开文件
	 */
	@SuppressLint("HandlerLeak")
	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
				case DOWNLOAD_SUCCESS:
					File file = (File) msg.obj;
					Intent intent = new Intent("android.intent.action.VIEW");
					intent.addCategory("android.intent.category.DEFAULT");
					intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					intent.setDataAndType(Uri.fromFile(file), "application/pdf");
//					startActivity(intent);
					startActivity(Intent.createChooser(intent, "标题"));
					/**
					 * 弹出选择框   把本activity销毁
					 */
//					finish();
					break;
				case DOWNLOAD_ERROR:
					Toast.makeText(list_item_doc.this, "文件加载失败", Toast.LENGTH_SHORT);
					break;
			}
		}
	};
	private ProgressDialog mProgressDialog;
	private File file1;

	public static void verifyStoragePermissions(Activity activity) {
		try {
			//检测是否有写的权限
			int permission = ActivityCompat.checkSelfPermission(activity,
					"android.permission.WRITE_EXTERNAL_STORAGE");
			if (permission != PackageManager.PERMISSION_GRANTED) {
				// 没有写的权限，去申请写的权限，会弹出对话框
				ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 传入文件 url  文件路径  和 弹出的dialog  进行 下载文档
	 */
	public static File downLoad(String serverpath, String savedfilepath, ProgressDialog pd) {
		try {
			URL url = new URL(serverpath);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(5000);
			if (conn.getResponseCode() == 200) {
				int max = conn.getContentLength();
				pd.setMax(max);
				InputStream is = conn.getInputStream();
				File file = new File(savedfilepath);
//				if (file.exists()) {
//					file.delete();
//				}
				FileOutputStream fos = new FileOutputStream(file);
				int len = 0;
				byte[] buffer = new byte[1024];
				int total = 0;
				while ((len = is.read(buffer)) != -1) {
					fos.write(buffer, 0, len);
					total += len;
					pd.setProgress(total);
				}
				fos.flush();
				fos.close();
				is.close();
				return file;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String getFileName(String serverurl) {
		String time = getTimeShort();
//		return time + serverurl.substring(serverurl.lastIndexOf("/") + 1);
		return serverurl.substring(serverurl.lastIndexOf("/") + 1);
	}

	public static String getTimeShort() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(new Date());
		return dateString;
	}

	/**
	 *
	 */
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initView();
		verifyStoragePermissions(this);
	}

	private void initView() {
		// TODO Auto-generated method stub
		Intent intent = getIntent();
		final String Strname = "http://kapp.oss-cn-hangzhou.aliyuncs.com/attachment/20180711170000880.xls";
		mProgressDialog = new ProgressDialog(this);
		mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		mProgressDialog.setCancelable(false);
		mProgressDialog.show();
		//截取最后14位 作为文件名
		String s = Strname.substring(Strname.length() - 14);
		//文件存储
		file1 = new File(Environment.getExternalStorageDirectory(), getFileName(s));
		new Thread() {
			public void run() {
				String absolutePath = file1.getAbsolutePath();///storage/emulated/0/1170000880.xls
				File haha = new File(file1.getAbsolutePath());
				String name = haha.getName();//1170000880.xls
				//判断是否有此文件
				if (haha.exists()) {
					//有缓存文件,拿到路径 直接打开
//					Message msg = Message.obtain();
//					msg.obj = haha;
//					msg.what = DOWNLOAD_SUCCESS;
//					handler.sendMessage(msg);
//					mProgressDialog.dismiss();
//					return;
//					haha.delete();
				}
//              本地没有此文件 则从网上下载打开
				File downloadfile = downLoad(Strname, file1.getAbsolutePath(), mProgressDialog);
//              Log.i("Log",file1.getAbsolutePath());
				Message msg = Message.obtain();
				if (downloadfile != null) {
					// 下载成功,安装....
					msg.obj = downloadfile;
					msg.what = DOWNLOAD_SUCCESS;
				} else {
					// 提示用户下载失败.
					msg.what = DOWNLOAD_ERROR;
				}
				handler.sendMessage(msg);
				mProgressDialog.dismiss();
			}
		}.start();
	}
}
