package cn.houno.hotel.ui.activity.book;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.houno.hotel.R;
import cn.houno.hotel.global.Api;
import cn.houno.hotel.ui.activity.BaseActivity;
import cn.houno.hotel.ui.activity.book.adapter.BookInfoAdapter;
import cn.houno.hotel.bean.BookInfoListBean;
import cn.houno.hotel.utils.QUtil;

/**
 * 获取个人信息
 * Created by qzc on 2017-06-07.
 */

public class GetPersonalInfoActivity extends BaseActivity {


    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.tv_top_title)
    TextView tvTopTitle;
    @Bind(R.id.et_search)
    AppCompatEditText etSearch;
    @Bind(R.id.iv_search)
    ImageView ivSearch;
    @Bind(R.id.rl_top_bar)
    RelativeLayout rlTopBar;
    @Bind(R.id.lv_personal_info)
    ListView mListView;

    private GetPersonalInfoActivity mActivity;

    private List<BookInfoListBean.InfoBean> mList = new ArrayList<>();

    private BookInfoAdapter mAdapter;

    private String mKeyWord = "";

    private boolean isShowEditText = false;

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_get_personal_info);
        ButterKnife.bind(this);
    }

    @Override
    public void initData() {
        super.initData();
        mActivity = GetPersonalInfoActivity.this;

        mAdapter = new BookInfoAdapter(mActivity, mList);
        mListView.setAdapter(mAdapter);

        getDataFromServer();
    }

    @Override
    public void initEvent() {
        super.initEvent();

        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
               mKeyWord = etSearch.getText().toString().trim();
               getDataFromServer();
                if (TextUtils.isEmpty(mKeyWord)) {
                    if (isShowEditText) {
                        etSearch.setVisibility(View.GONE);
                        tvTopTitle.setVisibility(View.VISIBLE);
                        isShowEditText = !isShowEditText;
                    }
                }
                return false;
            }
        });

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                BookInfoListBean.InfoBean infoBean = mList.get(position);
                Intent intent = new Intent();
                intent.putExtra("info",infoBean);
                setResult(201,intent);
                finish();
            }
        });
    }

    private void getDataFromServer() {
        final Dialog sDialog = QUtil.createLoadingDialog(mActivity, "正在获取信息");
        sDialog.show();
        RequestParams params = new RequestParams(Api.GET_GROUP_OR_INDIVIDUAL_CODE);
        params.addBodyParameter("hotelid", bHotelId);
        params.addBodyParameter("companyid", bCompanyId);
        params.addBodyParameter("stype", "Individual");
        params.addBodyParameter("keyword", mKeyWord);

        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("11", result);
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
                if (sDialog.isShowing()) {
                    sDialog.dismiss();
                }
            }
        });
    }

    private void parseData(String result) {
        try {
            JSONObject object = new JSONObject(result);
            mList.clear();
            if (object.getInt("status") == 2) {
                BookInfoListBean bookInfoListBean = new Gson().fromJson(result, BookInfoListBean.class);
                mList = bookInfoListBean.getInfo();
                mListView.setSelection(0);
            } else {
                showToast(object.getString("msg"));
            }
            mAdapter.setData(mList);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    @OnClick({R.id.iv_back, R.id.iv_search})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_search:
                searchPerson();
                break;
        }
    }

    private void searchPerson() {
        if (isShowEditText) {
            mKeyWord = etSearch.getText().toString().trim();
            getDataFromServer();
            if (TextUtils.isEmpty(mKeyWord)) {
                etSearch.setVisibility(View.GONE);
                tvTopTitle.setVisibility(View.VISIBLE);
            }else {
                return;
            }
        } else {
            tvTopTitle.setVisibility(View.GONE);
            etSearch.setVisibility(View.VISIBLE);
        }
        isShowEditText = !isShowEditText;
    }

}
