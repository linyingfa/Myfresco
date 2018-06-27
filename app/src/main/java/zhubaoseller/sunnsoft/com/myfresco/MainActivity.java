package zhubaoseller.sunnsoft.com.myfresco;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ViewPager ViewPager;


    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

        }
    };

    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public int getNavigationBarHeight(Activity activity) {
        Resources resources = activity.getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        //获取NavigationBar的高度
        int height = resources.getDimensionPixelSize(resourceId);
        return height;
    }


    /**
     * 获取焦点的时候
     *
     * @param hasFocus
     */
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && Build.VERSION.SDK_INT >= 19) {
            View decorView = getWindow().getDecorView();

            //SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN 和 SYSTEM_UI_FLAG_LAYOUT_STABLE，注意两个Flag必须要结合在一起使用，
            // 表示会让应用的主体内容占用系统【状态栏】的空间
            //todo SYSTEM_UI_FLAG_LAYOUT_STABLE
            //todo SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN

            // SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION，表示会让应用的主体内容占用【系统导航栏】的空间
            //todo SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION

//            同时使用了SYSTEM_UI_FLAG_HIDE_NAVIGATION和SYSTEM_UI_FLAG_FULLSCREEN，这样就可以将状态栏和导航栏同时隐藏了
            //todo SYSTEM_UI_FLAG_HIDE_NAVIGATION
            //SYSTEM_UI_FLAG_FULLSCREEN表示全屏的意思，也就是会将状态栏隐藏
            //todo SYSTEM_UI_FLAG_FULLSCREEN


            //这是4.4版本新加的模式，设置标志为 SYSTEM_UI_FLAG_IMMERSIVE 和 SYSTEM_UI_FLAG_IMMERSIVE_STICKY两种。
            // 经常配合着 SYSTEM_UI_FLAG_HIDE_NAVIGATION 和 SYSTEM_UI_FLAG_FULLSCREEN 使用。
//          (补充：FLAG_IMMERSIVE 要和 FLAG_HIDE_NAVIGATION and FLAG_FULLSCREEN 两者其一一起使用才有效，
//           与前者用为隐藏下方的 bar，与后者用为隐藏上方的 bar)
            //todo SYSTEM_UI_FLAG_IMMERSIVE_STICKY

            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewPager = findViewById(R.id.ViewPager);


        //todo 状态栏隐藏
//        View decorView = getWindow().getDecorView();//获取到了当前界面的DecorView
//        int option = View.SYSTEM_UI_FLAG_FULLSCREEN;
//        decorView.setSystemUiVisibility(option);//设置系统UI元素的可见性,SYSTEM_UI_FLAG_FULLSCREEN表示全屏的意思，也就是会将状态栏隐藏


        //todo 态栏效果，而不是直接把整个系统状态栏给隐藏掉,5.0及以上系统才支持
/*        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            //SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN 和 SYSTEM_UI_FLAG_LAYOUT_STABLE，
            // 注意两个Flag必须要结合在一起使用，表示会让应用的主体内容占用系统状态栏的空间，
            // 最后再调用Window的setStatusBarColor()方法将状态栏设置成透明色就可以了
//            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;

            //TODO 导航栏也进行隐藏。
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }*/

/*        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            //SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION，表示会让应用的主体内容占用系统导航栏的空间，
            // 然后又调用了setNavigationBarColor()方法将导航栏设置成透明色
            int option = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setNavigationBarColor(Color.TRANSPARENT);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }*/


        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();


        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                View decorView = getWindow().getDecorView();
                int width = decorView.getWidth();
                int height = decorView.getHeight();
//                W=1080-----H1920  有导航
//                W=1080-----H1920  无导航
                //TODO  证明：代表着整个玻璃屏幕的高度.
                Log.w("yf", "W=" + width + "-----" + "H" + height);

                //
//              TODO   这个View对应的高度, 可以表示当前应用程序的有效高度.
                View rootView = getWindow().findViewById(Window.ID_ANDROID_CONTENT);

                //TODO 这个方法, 那么RootView的高度会和DecorView的高度相等;  待定？
                rootView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

                int statusbarheight = getStatusBarHeight();
                int navbarheight = getNavigationBarHeight(MainActivity.this);
                int rw = rootView.getWidth();
                int rh = rootView.getHeight();
//               rw=1080-----rh=1572---statusbarheight=72-----navbarheight=108  //有导航
//               rw=1080-----rh=1680---statusbarheight=72-----navbarheight=108  //无导航
//                rh==(DecorView的高度)-(状态栏的高度)-(导航栏的高度);
//              TODO  在没有任何修饰的情况下, 应用程序的高度是去掉状态栏和导航栏后的高度; //1928-72-108=1748
                Log.w("yf", "rw=" + rw + "-----" + "rh=" + rh
                        + "---" + "statusbarheight=" + statusbarheight +
                        "-----" + "navbarheight=" + navbarheight);


                int heightPixels = MainActivity.this.getResources().getDisplayMetrics().heightPixels;
//                这个高度表示…屏幕有效的高度;;;
//                什么意思呢?                                1920-108=1812
//                就是:DecorView的高度去掉导航栏的高度.
//              todo   这个高度不管你有没有隐藏导航栏, 这个值都不会改变;   ？？？？？？待定
//              todo   heightPixels=1812   有导航栏
//              todo  heightPixels=1920    没有导航栏
                Log.w("heightPixels", "heightPixels=" + heightPixels);

//TODO#############################################################################


/*              番外:如果你想知道你的手机有没有导航栏:
                如果 (DecorView的高度)-(heightPixels) > 0 说明 你的手机有导航栏

                番外:如果你想知道你的程序有没有隐藏导航栏:
                情况1:设置了SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN属性:
                如果 (RootView的高度)-(heightPixels) > 0 说明 显示了导航栏

                情况2:没有设置SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN属性:
                如果 (RootView的高度+状态栏的高度)-(heightPixels) > 0 说明 显示了导航栏*/

//TODO#############################################################################

/*                附加1 获取状态栏的高度
                正常情况下: (heightPixels) - (RootView的高度) = 状态栏的高度
                但是如果你设置了View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN或者View.SYSTEM_UI_FLAG_HIDE_NAVIGATION属性的话,,,这种方法就不准确了.
                        万能,100%准确的方法:
                getResources().getDimensionPixelSize(getResources().getIdentifier(“status_bar_height”, “dimen”, “android”));

                附加2:获取导航栏的高度
                正常情况下: (DecorView的高度) - (heightPixels) = 导航栏的高度
                但是如果你设置了View.SYSTEM_UI_FLAG_HIDE_NAVIGATION属性的话,,,这种方法就不准确了.

                        万能,100%准确的方法:
                getResources().getDimensionPixelSize(getResources().getIdentifier(“navigation_bar_height”, “dimen”, “android”));*/
//TODO#############################################################################
            }
        }, 1000);


        final List<String> urls = new ArrayList<>();
        urls.add("http://sddc-1256739877.cos-website.ap-guangzhou.myqcloud.com/picture/20180626191125281.png");
        urls.add("http://sddc-1256739877.cos-website.ap-guangzhou.myqcloud.com/picture/20180627110552139.png");
        List<ImageView> imageViews = new ArrayList<>();
        for (int i = 0; i < urls.size(); i++) {
            final String url = urls.get(i);
            final ImageView imageView = new ImageView(MainActivity.this);
            imageView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
            Glide.with(this)
                    .load(url).asBitmap()
                    .into(new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(Bitmap bitmap, GlideAnimation<? super Bitmap> glideAnimation) {
                            int width = bitmap.getWidth();
                            int height = bitmap.getHeight();
                            //width=1263-----height2240

                            if (height > width) {
                                imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                            } else {
                                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                            }
                            imageView.setImageBitmap(bitmap);
                            Log.i("lyf", "width=" + width + "-----" + "height" + height);
                        }
                    });
            imageViews.add(imageView);
        }
        MyViewPagerAdapter myViewPagerAdapter = new MyViewPagerAdapter(imageViews, this);
//        ViewPager.setAdapter(myViewPagerAdapter);
    }

    /*这是自定义的一个PagerAdapter的适配器*/

    public class MyViewPagerAdapter extends PagerAdapter {
        /*要加载的views*/
        private List<ImageView> mListViews;
        //    private List<String> mlistTitles;
    /*可以访问全局信息*/
        private Context context;

        public MyViewPagerAdapter(List<ImageView> mListViews, Context context) {
            this.mListViews = mListViews;
            this.context = context;
//        this.mlistTitles=mlistTitles;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mListViews.get(position));
            return mListViews.get(position);
        }

        /*如果这个view不再使用的话，那么就销毁这个view*/
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mListViews.get(position));
        }

        @Override
        public int getCount() {
            return mListViews.size();
        }

        /*api是这样提示的*/
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

   /* @Override
    public CharSequence getPageTitle(int position) {
        return mlistTitles.get(position);
    }*/
    }
}
