package zhubaoseller.sunnsoft.com.myfresco;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import zhubaoseller.sunnsoft.com.pop.CommentOrPraisePopupWindow;

public class Main3Activity extends AppCompatActivity {
	TextView textView;
	ImageView icon;
	RelativeLayout root_rl;
	int iconWidth, layoutwidth;
	int left, right;
	int iconheight;
	int w, h;
	CommentOrPraisePopupWindow mCommentOrPraisePopupWindow;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main3);
		textView = findViewById(R.id.root_ll);
		icon = findViewById(R.id.icon);
		root_rl = findViewById(R.id.root_rl);
		icon.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (mCommentOrPraisePopupWindow == null) {
					mCommentOrPraisePopupWindow = new CommentOrPraisePopupWindow(Main3Activity.this);
				}
				if (mCommentOrPraisePopupWindow.isShowing()) {
					mCommentOrPraisePopupWindow.dismiss();
				} else {
					mCommentOrPraisePopupWindow.showPopupWindow(v);
				}
			}
		});
	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
		w = Utils.px2dip(this, root_rl.getWidth());
		h = Utils.px2dip(this, root_rl.getHeight());
		w = textView.getWidth();
		h = textView.getHeight();
		iconWidth = Utils.px2dip(this, icon.getWidth());
		layoutwidth = Utils.px2dip(this, textView.getHeight());
		iconheight = Utils.px2dip(this, icon.getHeight());
		Log.i("lyf", "iconWidth" + iconWidth + "---" + "layoutwidth" + layoutwidth);
		left = Utils.px2dip(this, icon.getLeft());
		right = Utils.px2dip(this, icon.getRight());
		Log.i("lyf", "left" + left + "---" + "right" + right);//left20---right60
	}

	private void doAnimation() {
		ValueAnimator animator = new ValueAnimator();
		int temp = iconWidth / 2 + left;
//        animator.setIntValues(temp, layoutwidth - (iconWidth / 2 + left));
//		animator.setIntValues(right, Utils.getWindowWidth(this) / 2 + right);
		animator.setIntValues(right, Utils.getWindowWidth(this) / 2 + right);
		animator.setDuration(2000);
		animator.setRepeatCount(0);
		animator.setInterpolator(new LinearInterpolator());
		animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				int curValue = (int) animation.getAnimatedValue();
				Log.i("lyf", curValue + "");
//                layout.setX(curValue);
//                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) textView.getLayoutParams();
//                layoutParams.width = curValue;
//                layoutParams.height = Utils.dip2px(Main3Activity.this, 40);
//                textView.setLayoutParams(layoutParams);
				textView.layout(curValue, 0, Utils.getWindowWidth(Main3Activity.this) / 4, h);
			}
		});
		animator.addListener(new AnimatorListenerAdapter() {
			@Override
			public void onAnimationStart(Animator animation) {
				super.onAnimationStart(animation);
				textView.setVisibility(View.VISIBLE);
			}

			@Override
			public void onAnimationEnd(Animator animation) {
				super.onAnimationEnd(animation);
//                layout.setVisibility(View.INVISIBLE);
			}
		});
		animator.start();
	}

	public void onclick(View view) {
		doAnimation();
	}
}
