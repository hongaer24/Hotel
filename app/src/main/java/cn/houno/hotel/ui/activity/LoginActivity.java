package cn.houno.hotel.ui.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.RelativeLayout;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.houno.hotel.MainActivity;
import cn.houno.hotel.R;
import cn.houno.hotel.global.Api;
import cn.houno.hotel.utils.PrefUtils;
import cn.houno.hotel.utils.QUtil;


/**
 * 登录
 * Created by qzc on 2017/5/5 0005.
 */

public class LoginActivity extends BaseActivity {

    @Bind(R.id.rl_top_bar)
    RelativeLayout rlTopBar;
    @Bind(R.id.et_user)
    AppCompatEditText etUser;
    @Bind(R.id.et_pwd)
    AppCompatEditText etPwd;
    @Bind(R.id.btn_login)
    Button btnLogin;

    private LoginActivity mActivity;


    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @Override
    public void initData() {
        super.initData();
        mActivity = LoginActivity.this;
    }

    @OnClick(R.id.btn_login)
    public void onClick() {
        if (TextUtils.isEmpty(etUser.getText().toString().trim())) {
            showToast("账号不能为空");
            return;
        }

        if (TextUtils.isEmpty(etPwd.getText().toString().trim())) {
            showToast("密码不能为空");
            return;
        }

        getDataFromServer();
    }

   Dialog lDialog ;
    private void getDataFromServer() {
        lDialog = QUtil.createLoadingDialog(mActivity, "正在登录");
        lDialog.show();
        RequestParams params = new RequestParams(Api.LOGIN);
        params.addBodyParameter("username", etUser.getText().toString().trim());
        params.addBodyParameter("password", etPwd.getText().toString().trim());
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("LOGIN", result);
                parseData(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    private void parseData(String result) {

        try {
            JSONObject object = new JSONObject(result);

            if (object.getInt("status") == 2) {
                saveInfoAndStartActivity(object);
            } else {
                showToast(object.getString("msg"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void saveInfoAndStartActivity(JSONObject object) {
        try {
            JSONObject info = object.getJSONObject("info");
            PrefUtils.setString(mActivity, "name", info.getString("name"));
            PrefUtils.setString(mActivity, "userid", info.getString("id"));
            PrefUtils.setString(mActivity, "loginname", info.getString("loginname"));
            PrefUtils.setString(mActivity, "userincid", info.getString("userincid"));
            PrefUtils.setString(mActivity, "hotelidcode", info.getString("hotelidcode"));
            PrefUtils.setString(mActivity, "companyinfoidcode", info.getString("companyinfoidcode"));

            getUserInfo();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private void getUserInfo() {
        RequestParams params = new RequestParams(Api.GET_HOTEL_INFO);
        params.addBodyParameter("hotelid", PrefUtils.getString(mActivity, "hotelidcode", ""));
        params.addBodyParameter("companyid", PrefUtils.getString(mActivity, "companyinfoidcode", ""));
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("GetHotelInfo", result);
                parseData2(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
                if (lDialog.isShowing()) {
                    lDialog.dismiss();
                }
            }
        });
    }

    private void parseData2(String result) {
        try {
            JSONObject object = new JSONObject(result);
            if (object.getInt("status") == 0) {
                JSONObject info = object.getJSONObject("info");
                String shortname = info.getString("shortname");
                String businessday = info.getString("businessday");
                PrefUtils.setString(mActivity, "shortname", shortname);
                PrefUtils.setString(mActivity, "businessday", businessday);
                PrefUtils.setBoolean(mActivity, "login", true);

                startActivity(new Intent(mActivity, MainActivity.class));
                finish();
            } else {

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}
