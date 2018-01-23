package cn.houno.hotel.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import cn.houno.hotel.MainActivity;
import cn.houno.hotel.R;
import cn.houno.hotel.utils.PrefUtils;
import cn.houno.hotel.utils.QStatusBarUtil;

/**
 * 基类
 * Created by qzc on 2017-03-14.
 */

public abstract class BaseActivity extends AppCompatActivity {


    protected String bBusinessday;

    protected String bHotelId;

    protected String bCompanyId;

    protected String bName;     //用户真实姓名
    protected String bUserid;
    protected String bLoginName;    //登录名
    protected String bUserincid;    //序号

//            "id":"83BDE082-5718-4015-8A0E-B839DDE34FDF",
//            "name":"王正权",
//            "loginname":"LF0010021",
//            "userincid":"21",
//            "hotelidcode":"LF001",
//            "companyinfoidcode":"BOTAO"

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//绑定竖屏
        initData();
        initEvent();
    }

    protected abstract void initView(Bundle savedInstanceState);

    public void initData() {
        setColorStatusBar(this, R.color.app_theme_org_press);
        bBusinessday = PrefUtils.getString(this, "businessday", "");
        bHotelId = PrefUtils.getString(this, "hotelidcode", "");
        bCompanyId = PrefUtils.getString(this, "companyinfoidcode", "");
        bName = PrefUtils.getString(this, "name", "");
        bUserid= PrefUtils.getString(this, "userid", "");
        bLoginName= PrefUtils.getString(this, "loginname", "");
        bUserincid= PrefUtils.getString(this, "userincid", "");
    }

    public void initEvent() {

    }

    public static void setColorStatusBar(Activity activity, int colorResId) {
        QStatusBarUtil.setWindowStatusBarColor(activity, colorResId);
    }


    public void returnMain() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    public void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }


}
