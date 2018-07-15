package zhubaoseller.sunnsoft.com.pop;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;

import zhubaoseller.sunnsoft.com.myfresco.R;
import zhubaoseller.sunnsoft.com.myfresco.Utils;

public class CommentOrPraisePopupWindow extends PopupWindow implements View.OnClickListener {
	private Context mContext;
	private int mPopupWindowHeight;
	private int mPopupWindowWidth;
	private int mCurrentPosition;

	public CommentOrPraisePopupWindow(Context context) {
		View contentView = LayoutInflater.from(context).inflate(R.layout.popup_window_praise_or_comment_view, null);
		this.setContentView(contentView);
		contentView.findViewById(R.id.layout_praise).setOnClickListener(this);
		contentView.findViewById(R.id.layout_comment).setOnClickListener(this);
		//不设置宽高将无法显示popupWindow
		this.mPopupWindowHeight = Utils.dip2px(context, 38);
		this.mPopupWindowWidth = Utils.dip2px(context, 190);
		this.setHeight(mPopupWindowHeight);
		this.setWidth(mPopupWindowWidth);
		// 设置SelectPicPopupWindow弹出窗体可点击
		this.setFocusable(true);
		this.setOutsideTouchable(true);
		this.setAnimationStyle(R.style.PraiseOrCommentAnimationStyle);
		// 刷新状态
		this.update();
		// 实例化一个ColorDrawable颜色为半透明
		ColorDrawable dw = new ColorDrawable(0x00000000);
		this.setBackgroundDrawable(dw);
	}

	public CommentOrPraisePopupWindow setCurrentPosition(int currentPosition) {
		mCurrentPosition = currentPosition;
		return this;
	}

	public void showPopupWindow(View anchor) {
		if (anchor == null) {
			return;
		}
		int[] location = new int[2];
		anchor.getLocationOnScreen(location);
		int xOffset = location[0] - mPopupWindowWidth - Utils.dip2px(anchor.getContext(), 10f);
		int yOffset = location[1] + (anchor.getHeight() - mPopupWindowHeight) / 2;
		showAtLocation(anchor, Gravity.NO_GRAVITY, xOffset, yOffset);
	}

	@Override
	public void onClick(View v) {
		dismiss();
		switch (v.getId()) {
			case R.id.layout_praise:
				break;
			case R.id.layout_comment:
				break;
			default:
				break;
		}
	}
}
