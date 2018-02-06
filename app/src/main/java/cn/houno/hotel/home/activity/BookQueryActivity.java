package cn.houno.hotel.home.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

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
import cn.houno.hotel.home.activity.NewOrderActivity;
import cn.houno.hotel.home.bean.BookQueryListBean;
import cn.houno.hotel.ui.activity.BaseActivity;
import cn.houno.hotel.ui.activity.book.adapter.BookQueryListAdapter;

import cn.houno.hotel.utils.DateUtil;
import cn.houno.hotel.utils.PrefUtils;
import cn.houno.hotel.utils.QUtil;

/**
 * 预订询价
 * Created by qzc on 2017-05-23.
 */

public class BookQueryActivity extends BaseActivity {
    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.iv_search)
    ImageView ivSearch;
    @Bind(R.id.lv_book_query)
    ListView mListView;

    private BookQueryActivity mActivity;

    private String mStartDate="";
    private String mEndDate="";
    private String mRoomPriceCode="";
    private String mRoomTypeCode="";
    private int mRoomCount=1;
    private String mPackageCode="";
    private String mCompanyCode="";
    private String mTravelCode="";

    private List<BookQueryListBean.InfoBean> mList = new ArrayList<>();

    private BookQueryListAdapter mAdapter;


    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_book_query);
        ButterKnife.bind(this);
    }

    @Override
    public void initData() {
        super.initData();
        mActivity = BookQueryActivity.this;

        mStartDate = bBusinessday;
        mEndDate = DateUtil.getTheDate(bBusinessday,1);
        mAdapter = new BookQueryListAdapter(mActivity, mList);
        mListView.setAdapter(mAdapter);

        getDataFromServer();
    }

    @Override
    public void initEvent() {
        super.initEvent();

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(mActivity, NewOrderActivity.class);
                intent.putExtra("data",  mList.get(position));
                intent.putExtra("from", "query");
                startActivity(intent);
            }
        });
    }

    @OnClick({R.id.iv_back, R.id.iv_search})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_search:
                startActivityForResult(new Intent(mActivity, QueryConditionsActivity.class), 100);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == 200 && requestCode == 100) {

            mStartDate = data.getStringExtra("start_date");
            mEndDate = data.getStringExtra("end_date");
            mRoomCount = data.getIntExtra("count", 1);
            mRoomPriceCode = data.getStringExtra("price");
            mRoomTypeCode = data.getStringExtra("type");
            mPackageCode = data.getStringExtra("package");
            mCompanyCode = data.getStringExtra("company");
            mTravelCode = data.getStringExtra("travel");

            getDataFromServer();
        }

    }

    private void getDataFromServer() {
        final Dialog sDialog = QUtil.createLoadingDialog(mActivity, "正在查询");
        sDialog.show();
        RequestParams params = new RequestParams(Api.BOOK_QUERY);
        params.addBodyParameter("hotelid", bHotelId);
        params.addBodyParameter("companyid", bCompanyId);
        params.addBodyParameter("startdate", mStartDate);
        params.addBodyParameter("enddate", mEndDate);
        params.addBodyParameter("roomratecode", mRoomPriceCode);
        params.addBodyParameter("roomtypecode", mRoomTypeCode);
        params.addBodyParameter("roomcount", mRoomCount + "");
        params.addBodyParameter("packagecode", mPackageCode);
        params.addBodyParameter("travelcode", mTravelCode);
        params.addBodyParameter("companycode", mCompanyCode);
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
                if (sDialog.isShowing()){
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
                BookQueryListBean bookQueryListBean = new Gson().fromJson(result, BookQueryListBean.class);
                mList = bookQueryListBean.getInfo();
                mAdapter.setData(mList);
                mListView.setSelection(0);
            } else {
                showToast(object.getString("msg"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
