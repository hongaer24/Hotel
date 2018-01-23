package cn.houno.hotel.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

import cn.houno.hotel.MainActivity;
import cn.houno.hotel.R;
import cn.houno.hotel.utils.PrefUtils;


public class SplashActivity extends BaseActivity {


    @Override
    protected void initView(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);
    }

    @Override
    public void initData() {
        setColorStatusBar(this, R.color.white);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //是否已经登陆,是的话请求服务器看个人信息是否更改（印象太极和候鸟旅居共用账号）
                if (PrefUtils.getBoolean(SplashActivity.this, "login", false)) {
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                } else {
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                }
                SplashActivity.this.finish();
            }
        }, 1500);


    }


}
