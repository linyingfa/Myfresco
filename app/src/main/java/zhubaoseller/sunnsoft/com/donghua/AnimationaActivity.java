package zhubaoseller.sunnsoft.com.donghua;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import zhubaoseller.sunnsoft.com.myfresco.R;

public class AnimationaActivity extends AppCompatActivity {
	Animation mAnimation;
	TextView text;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main3);
//		mAnimation = AnimationUtils.loadAnimation(this, R.anim.my_alpha_action);
//		mAnimation = AnimationUtils.loadAnimation(this, R.anim.my_rotate_action);
//		mAnimation = AnimationUtils.loadAnimation(this, R.anim.my_scale_action);
//		mAnimation = AnimationUtils.loadAnimation(this, R.anim.my_translate_action);
		mAnimation = AnimationUtils.loadAnimation(this, R.anim.my_translate_action2);
		text = findViewById(R.id.textview00);
	}

	public void onB(View view) {
		text.startAnimation(mAnimation);
	}
}
