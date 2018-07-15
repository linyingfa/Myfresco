package zhubaoseller.sunnsoft.com.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;

import zhubaoseller.sunnsoft.com.myfresco.Utils;

/**
 * Created by xiaolin on 2018/7/15.
 */
public class MyLinelayout extends View {
	private int w, h;

	public MyLinelayout(Context context) {
		super(context);
	}

	public MyLinelayout(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
	}

	public MyLinelayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	@Override
	protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
		super.onLayout(changed, left, top, right, bottom);
		w = Utils.px2dip(getContext(), getWidth());
		h = Utils.px2dip(getContext(), getHeight());
//		07-15 06:19:17.837 3405-3405/zhubaoseller.sunnsoft.com.myfresco I/lyf: 360-------w-------
//		07-15 06:19:17.837 3405-3405/zhubaoseller.sunnsoft.com.myfresco I/lyf: 100-------h-------
		Log.i("lyf", w + "-------w-------");
		Log.i("lyf", h + "-------h-------");
	}
}
