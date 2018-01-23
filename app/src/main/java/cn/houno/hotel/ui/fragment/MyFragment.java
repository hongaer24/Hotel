package cn.houno.hotel.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.houno.hotel.R;
import cn.houno.hotel.ui.activity.EditPassWordActivity;
import cn.houno.hotel.ui.activity.LoginActivity;

/**
 * 我的页面
 * Created by qzc on 2017/5/8 0008.
 */

public class MyFragment extends BaseFragment {


    @Bind(R.id.tv_edit_pwd)
    TextView tvEditPwd;

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_my, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected void initFindViewById(View view) {

    }

    @Override
    public void initData() {

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.tv_edit_pwd,R.id.tv_logout})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_edit_pwd:
                startActivity(new Intent(mActivity, EditPassWordActivity.class));
                break;
            case R.id.tv_logout:
                startActivity(new Intent(mActivity, LoginActivity.class));
                mActivity.finish();
                break;
        }

    }
}
