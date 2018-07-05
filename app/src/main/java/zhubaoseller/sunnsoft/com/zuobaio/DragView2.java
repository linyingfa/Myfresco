package zhubaoseller.sunnsoft.com.zuobaio;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by xiaolin on 2018/7/5.
 */
public class DragView2 extends View {
	private int x, y;

	public DragView2(Context context) {
		super(context);
	}

	public DragView2(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
	}

	public DragView2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	//这里省略构造方法
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int getX = (int) event.getX();
		int getY = (int) event.getY();
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
				offsetLeftAndRight(offsetX);//同时对left和right偏移
				offsetTopAndBottom(offsetY);//同时对top和bottom偏移
				break;
		}
		return true;
	}
}
