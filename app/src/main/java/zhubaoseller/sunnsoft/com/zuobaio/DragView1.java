package zhubaoseller.sunnsoft.com.zuobaio;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by xiaolin on 2018/7/5.
 */
public class DragView1 extends View {
	private int x, y;

	public DragView1(Context context) {
		super(context);
	}

	public DragView1(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public DragView1(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	/*		（三）MotionEvent类中 getRawX()和 getX()的区别：
		event.getRawX()： 触摸点相对于屏幕原点的x坐标
		event.getX()：  触摸点相对于其所在组件原点的x坐标
		于是乎： view.getScrollY() + event.getY() 就得到了view中的触摸点在Y轴上的偏移量*/
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int getX = (int) event.getX();// 触摸点相对于其所在组件原点的x坐标
		int getY = (int) event.getY();// 触摸点相对于其所在组件原点的Y坐标
		switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				// 记录触摸点坐标
				x = (int) event.getX();
				y = (int) event.getY();
				break;
			case MotionEvent.ACTION_MOVE:
				//计算偏移量
				int offsetX = getX - x;
				int offsetY = getY - y;
				//在当前left、top、right、bottom的基础上加上偏移量
				layout(getLeft() + offsetX,
						getTop() + offsetY,
						getRight() + offsetX,
						getBottom() + offsetY);
				break;
		}
		return true;
	}
}