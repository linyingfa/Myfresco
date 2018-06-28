package zhubaoseller.sunnsoft.com.myfresco;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        setNavigationBarStatusBarTranslucent(this);
    }

    /**
     * 导航栏，状态栏透明
     * 在设置了透明状态栏(StatusBar)或者导航栏(NavigationBar)之后，
     * activity的内容会延伸至对应的区域，使得该区域出现重叠现象，这对内容包含交互控件的情况影响尤其巨大
     *
     * @param activity
     */
    public static void setNavigationBarStatusBarTranslucent(Activity activity) {
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = activity.getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            activity.getWindow().setNavigationBarColor(Color.TRANSPARENT);
            activity.getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

    }
}
