package zhubaoseller.sunnsoft.com.view;

import android.animation.AnimatorSet;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import zhubaoseller.sunnsoft.com.myfresco.R;

/**
 * Created by kuangxiaoguo on 2017/4/3.
 */
public class ATFloatingOrderView extends LinearLayout implements View.OnClickListener {
	private static final int ANIMATION_TIME = 500;
	private static final int SCALE_ANIMATION_TIME = 100;
	private ImageView mIconImageView;
	private ImageView mCloseImageView;
	private View mView;
	private int mWidth;
	private int mHeight;
	private ViewStateEnum mViewState;
	private ValueAnimator mTranslateAnimator;
	private ValueAnimator mScaleAnimator;
	private TextView mTitleTextView;
	private TextView mDesTextView;
	private TextView mDetailTextView;

	public ATFloatingOrderView(Context context) {
		this(context, null);
	}

	public ATFloatingOrderView(Context context, @Nullable AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public ATFloatingOrderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		mView = LayoutInflater.from(context).inflate(R.layout.order_progress_view, null);
		addView(mView);
		initView();
		mViewState = ViewStateEnum.OPEN;
	}

	private void initView() {
		mIconImageView = (ImageView) mView.findViewById(R.id.icon_image_view);
		mCloseImageView = (ImageView) mView.findViewById(R.id.close_image_view);
		LinearLayout closeLayout = (LinearLayout) mView.findViewById(R.id.close_layout);
		View centerView = mView.findViewById(R.id.center_layout);
		mTitleTextView = (TextView) centerView.findViewById(R.id.title_text_view);
		mDesTextView = (TextView) centerView.findViewById(R.id.des_text_text_view);
		mDetailTextView = (TextView) centerView.findViewById(R.id.detail_text_view);
		mIconImageView.setOnClickListener(this);
		closeLayout.setOnClickListener(this);
	}

	/**
	 * 根据订单进度设置中间view的对应值
	 */
	public void setCenterText(String title, String description, String detail) {
		mTitleTextView.setText(title);
		mDesTextView.setText(description);
		mDetailTextView.setText(detail);
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		super.onLayout(changed, l, t, r, b);
		mWidth = getWidth();
		mHeight = getHeight();
		Log.i("mWidth", mWidth + "--------");
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.icon_image_view:
				if (mViewState == ViewStateEnum.OPEN) {
					return;
				}
				doAnimation();
				mViewState = ViewStateEnum.OPEN;
				break;
			case R.id.close_layout:
				doAnimation();
				mViewState = ViewStateEnum.CLOSE;
				break;
			default:
				break;
		}
	}

	/**
	 * 使用AnimatorSet让两个动画同时播放
	 */
	private void doAnimation() {
		initTranslateAnimation();
		initScaleAnimation();
		AnimatorSet set = new AnimatorSet();
		set.play(mTranslateAnimator).with(mScaleAnimator);
		set.start();
	}

	/**
	 * view位移动画
	 */
	private void initTranslateAnimation() {
		int iw = mIconImageView.getWidth();
		int padd = mView.getPaddingLeft();
		int closeDistance = mWidth - iw - padd * 2;
		mTranslateAnimator = ValueAnimator.ofInt(0, closeDistance);
		/*
		当一开始为关闭状态时，给动画设置evaluator，使得动画开始位置为closeDistance
         */
		//TODO $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
		//TODO 如果是要关闭，就倒序输出  就从281-0开始
		//TODO $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
		if (mViewState == ViewStateEnum.CLOSE) {
			mTranslateAnimator.setEvaluator(new TypeEvaluator<Integer>() {
				@Override
				public Integer evaluate(float fraction, Integer startValue, Integer endValue) {
					//实现倒序输出实例
					//例如：ValueAnimator.ofInt(100, 400)；
					//当前的值 = 100 + （400 - 100）* 显示进度(x)
					// return (int)(startInt + fraction * (endValue - startInt));
					int temp = (int) (endValue - (endValue - startValue) * fraction);
					// fraction * (endValue - startInt)表示动画实际运动的距离
					//endValue减去实际运动的距离就表示随着运动距离的增加，离终点越来越远，这也就实现了从终点出发，最终运动到起点的效果了。
					Log.i("temp", temp + "--------");//281-0,,,,目标值-每次的进度
					return temp;
				}
			});
		}
		//TODO $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
		//TODO 如果是打开，就从0-281开始
		//TODO $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
		mTranslateAnimator.setDuration(ANIMATION_TIME);
		mTranslateAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
		mTranslateAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				int value = (int) animation.getAnimatedValue();
				Log.i("value", value + "--------");
				//0-281
				//281-0
				/*
				相对于父view的位置坐标
                 */
				mView.layout(value, 0, mWidth, mHeight);
			}
		});
	}

	/**
	 * 点击x号缩放动画
	 */
	private void initScaleAnimation() {
		mScaleAnimator = ValueAnimator.ofFloat(1.0f, 0.0f);
		mScaleAnimator.setDuration(SCALE_ANIMATION_TIME);
		if (mViewState == ViewStateEnum.CLOSE) {
			mScaleAnimator.setEvaluator(new TypeEvaluator<Float>() {
				@Override
				public Float evaluate(float fraction, Float startValue, Float endValue) {
					float temp2 = endValue - (endValue - startValue) * fraction;
					Log.i("temp2", temp2 + "--------");
					return endValue - (endValue - startValue) * fraction;
				}
			});
		}
		mScaleAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				float value = (float) animation.getAnimatedValue();
				mCloseImageView.setScaleX(value);
				mCloseImageView.setScaleY(value);
			}
		});
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		int viewWidth = mView.getMeasuredWidth();
		int viewHeight = mView.getMeasuredHeight();
		int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		int widthSize = MeasureSpec.getSize(widthMeasureSpec);
		int heightMode = MeasureSpec.getMode(heightMeasureSpec);
		int heightSize = MeasureSpec.getSize(heightMeasureSpec);
		if (widthMode == MeasureSpec.AT_MOST) {
			widthSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, viewWidth, getResources().getDisplayMetrics());
		}
		if (heightMode == MeasureSpec.AT_MOST) {
			heightSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, viewHeight, getResources().getDisplayMetrics());
		}
		setMeasuredDimension(widthSize, heightSize);
	}

	private enum ViewStateEnum {
		OPEN, CLOSE
	}
}
