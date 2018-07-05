package zhubaoseller.sunnsoft.com.zuobaio;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import zhubaoseller.sunnsoft.com.myfresco.R;

/**
 * Created by xiaolin on 2018/7/5.
 */
public class TestViewAry extends AppCompatActivity {
	TextView text;
	LinearLayout root_ll;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_testview);
		text = findViewById(R.id.text);
		root_ll = findViewById(R.id.root_ll);
		root_ll.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				return false;
			}
		});
	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);

/*		（一）首先明确一下 android 中的坐标系统 ：
		屏幕的左上角是坐标系统原点（0,0）
		原点向右延伸是X轴正方向，原点向下延伸是Y轴正方向*/
//		text.setX()=setTranslationX(x - mLeft);
//		getX = getLeft + getTraslationX
//		getdefuViewZUOBIAO();
//		I/System.out: 100.0---------cX-----------
//		I/System.out: 100.0--------cY--------
//		I/System.out: 100------cLeft---------
//		I/System.out: 100------cTop---------
//		I/System.out: 200------cBottom---------
//		I/System.out: 200------cRight---------
//		I/System.out: =========================
//		getdefuViewZUOBIAO2();
//		I/System.out: 210.0---------cX-----------
//		I/System.out: 418.0--------cY--------
//		I/System.out: 210------cLeft---------
//		I/System.out: 418------cTop---------
//		I/System.out: 718------cBottom---------
//		I/System.out: 510------cRight---------
//		I/System.out: =========================


/*		（二）关于Scroll： 屏幕显示的内容很多时，会有超出一屏的情况，于是就产生了Scroll的概念。
		在View类中有个方法：
		getScrollY()  英文原文描述是：
		Return the scrolled top position of this view. This is the top edge of the displayed part of your view....
		其实理解起来仍然就是：就是这个view相对于“坐标系统原点”(见上图)在Y轴上的偏移量.(getScrollX同理)
		getScrollY()就是当前视图相对于屏幕原点在Y轴上的偏移量.*/
//		getScrollY();//默认是0
//		I/System.out: 0------cScrollY---------
//		I/System.out: 0------cScrollX---------

/*		（三）MotionEvent类中 getRawX()和 getX()的区别：
		event.getRawX()： 触摸点相对于屏幕原点的x坐标
		event.getX()：  触摸点相对于其所在组件原点的x坐标
		于是乎： view.getScrollY() + event.getY() 就得到了view中的触摸点在Y轴上的偏移量*/
	}

	private void getdefuViewZUOBIAO() {
		System.out.println(text.getX() + "---------cX-----------");
		System.out.println(text.getY() + "--------cY--------");
		System.out.println(text.getLeft() + "------cLeft---------");
		System.out.println(text.getTop() + "------cTop---------");
		System.out.println(text.getBottom() + "------cBottom---------");
		System.out.println(text.getRight() + "------cRight---------");
		System.out.println("=========================");
	}

	private void getdefuViewZUOBIAO2() {
		System.out.println(root_ll.getX() + "---------cX-----------");
		System.out.println(root_ll.getY() + "--------cY--------");
		System.out.println(root_ll.getLeft() + "------cLeft---------");
		System.out.println(root_ll.getTop() + "------cTop---------");
		System.out.println(root_ll.getBottom() + "------cBottom---------");
		System.out.println(root_ll.getRight() + "------cRight---------");
		System.out.println("=========================");
	}

	private void getScrollY() {
		System.out.println(root_ll.getScrollX() + "------cScrollX---------");
		System.out.println(root_ll.getScrollY() + "------cScrollY---------");
	}
}
