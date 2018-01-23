package cn.houno.hotel.ui.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.houno.hotel.R;
import cn.houno.hotel.global.Api;
import cn.houno.hotel.utils.PrefUtils;
import cn.houno.hotel.utils.QUtil;

/**
 * 修改密码
 * Created by qzc on 2017/5/9 0009.
 */

public class EditPassWordActivity extends BaseActivity {

    @Bind(R.id.ll_container)
    LinearLayout llContainer;
    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.et_old_pwd)
    EditText etOldPwd;
    @Bind(R.id.et_new_pwd)
    EditText etNewPwd;
    @Bind(R.id.et_confirm_pwd)
    EditText etConfirmPwd;
    @Bind(R.id.btn_modify)
    Button btnModify;

    private EditPassWordActivity mActivity;

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_edit_pwd);
        ButterKnife.bind(this);
    }

    @Override
    public void initData() {
        super.initData();
        mActivity = EditPassWordActivity.this;
    }

    @Override
    public void initEvent() {
        super.initEvent();
    }


    @OnClick({R.id.iv_back, R.id.btn_modify})
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_modify:
                if (QUtil.isFastDoubleClick()) {
                    return;
                }
                if (TextUtils.isEmpty(etOldPwd.getText().toString())) {
                    showToast("原密码不能为空");
                } else if (etOldPwd.getText().toString().trim().length() < 6) {
                    showToast("原密码长度不能小于6位");
                } else if (etNewPwd.getText().toString().trim().length() < 6
                        || etNewPwd.getText().toString().trim().length() > 18) {
                    showToast("新密码长度6-18位");
                } else if (!TextUtils.equals(etNewPwd.getText().toString().trim(), etConfirmPwd.getText().toString().trim())) {
                    showToast("新密码和确认密码不一致");
                } else if (TextUtils.equals(etNewPwd.getText().toString().trim(), etOldPwd.getText().toString().trim())) {
                    showToast("新密码不能和原密码一样");
                } else {
                    //修改密码+
                    getDataFromServer();
                }
                break;
        }

    }


    private void getDataFromServer() {
        final Dialog mDialog = QUtil.createLoadingDialog(mActivity, "正在修改");
        mDialog.show();
        RequestParams params = new RequestParams(Api.MODIFY_PWD);
        params.addBodyParameter("id", PrefUtils.getString(mActivity, "userid", ""));
        params.addBodyParameter("password", etOldPwd.getText().toString().trim());
        params.addBodyParameter("newpass", etNewPwd.getText().toString().trim());

        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("mmmm",result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    showToast(jsonObject.getString("msg"));
                    if (jsonObject.getInt("status") == 2) {
                        finish();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                System.out.println("error:" + ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
                if (mDialog.isShowing()) {
                    mDialog.dismiss();
                }
            }
        });
    }
}
