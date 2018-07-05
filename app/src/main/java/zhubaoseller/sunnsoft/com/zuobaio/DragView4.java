package zhubaoseller.sunnsoft.com.zuobaio;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * Created by xiaolin on 2018/7/5.
 */
public class DragView4 extends View {
	private int x, y;
	private Scroller mScroller;

	public DragView4(Context context) {
		super(context);
		initScroller(context);
	}

	public DragView4(Context context, AttributeSet attrs) {
		super(context, attrs);
		initScroller(context);
	}

	public DragView4(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initScroller(context);
	}

	private void initScroller(Context context) {
		setBackgroundColor(Color.RED);
		mScroller = new Scroller(context);
	}

	/*		（三）MotionEvent类中 getRawX()和 getX()的区别：
	event.getRawX()： 触摸点相对于屏幕原点的x坐标
	event.getX()：  触摸点相对于其所在组件原点的x坐标
	于是乎： view.getScrollY() + event.getY() 就得到了view中的触摸点在Y轴上的偏移量


	记住:每次执行完一个事件，都会重新走执行一次onTouchEvent方法，
	    I/onTouchEvent: getRawX=138---getRawY=441
        I/onTouchEvent: X=138---Y=441
	   I/onTouchEvent: getRawX=126---getRawY=441

	*/
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int getRawX = (int) event.getRawX();// 触摸点相对于屏幕原点的x坐标
		int getRawY = (int) event.getRawY();
		Log.i("onTouchEvent", "getRawX=" + getRawX + "---" + "getRawY=" + getRawY);
		switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				// 记录触摸点坐标
				x = (int) event.getRawX();
				y = (int) event.getRawY();
				Log.i("onTouchEvent", "X=" + x + "---" + "Y=" + y);
				break;
			case MotionEvent.ACTION_MOVE:
				//计算偏移量
				int offsetX = getRawX - x;
				int offsetY = getRawY - y;
				Log.i("onTouchEvent", "offsetX=" + offsetX + "---" + "offsetY=" + offsetY);
				((View) getParent()).scrollBy(-offsetX, -offsetY);
				//重新设置初始坐标
				x = getRawX;
				y = getRawY;
				break;
			case MotionEvent.ACTION_UP:
				/**
				 * 实现拖动回弹回去，需配合方法一、二、四中任一方法
				 */
				View viewGroup = (View) getParent();
				mScroller.startScroll(viewGroup.getScrollX(),
						viewGroup.getScrollY(),
						-viewGroup.getScrollX(),
						-viewGroup.getScrollY()
				);
				invalidate();
				break;
		}
		return true;
	}

	@Override
	public void computeScroll() {
		super.computeScroll();
		//判断Scroller是否执行完毕
		if (mScroller.computeScrollOffset()) {
			((View) getParent()).scrollTo(
					mScroller.getCurrX(),
					mScroller.getCurrY());
			invalidate();
		}
	}
}
