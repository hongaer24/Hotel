package cn.houno.hotel.ui.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
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
import java.util.Calendar;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.houno.hotel.R;
import cn.houno.hotel.adapter.SearchBillsAdapter;
import cn.houno.hotel.bean.SearchBillsBean;
import cn.houno.hotel.global.Api;
import cn.houno.hotel.utils.DateUtil;
import cn.houno.hotel.utils.PrefUtils;

/**
 * 单据查询
 * Created by qzc on 2017-05-11.
 */

public class SearchBillsActivity extends BaseActivity {
    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.rl_top_bar)
    RelativeLayout rlTopBar;
    @Bind(R.id.tv_start_date)
    TextView tvStartDate;
    @Bind(R.id.tv_end_date)
    TextView tvEndDate;
    @Bind(R.id.btn_search)
    Button btnSearch;
    @Bind(R.id.lv_search_bills)
    ListView lvSearchBills;

    private SearchBillsActivity mActivity;

    private String startDate, endDate;

    private List<SearchBillsBean.InfoBean> mList = new ArrayList<>();

    private SearchBillsAdapter mEditAdapter;

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_search_bills);
        ButterKnife.bind(this);
    }

    @Override
    public void initData() {

        super.initData();

        mActivity = SearchBillsActivity.this;

        startDate = bBusinessday;
        endDate = DateUtil.getTomorrowTime(startDate);

        setDateText();
    }


    @OnClick({R.id.iv_back, R.id.ll_date_pick, R.id.btn_search})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.ll_date_pick:
                Intent intent = new Intent(mActivity, DatePickerActivity.class);
                intent.putExtra("date", PrefUtils.getString(mActivity, "businessday", ""));
                startActivityForResult(intent,101);
                break;
            case R.id.btn_search:
                getDataFromServer();
                break;
        }
    }


    private void getDataFromServer() {
        RequestParams params = new RequestParams(Api.SEARCH_MY_BILLS);
        params.addBodyParameter("startdate", startDate);
        params.addBodyParameter("enddate", endDate);
        params.addBodyParameter("userid", PrefUtils.getString(mActivity, "loginname", ""));
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("search_bill_success",result);
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
            mList.clear();
            if (object.getInt("status") == 2) {
                SearchBillsBean searchBillsBean = new Gson().fromJson(result, SearchBillsBean.class);
                mList = searchBillsBean.getInfo();
            }
            mEditAdapter = new SearchBillsAdapter(mActivity, mList);
            lvSearchBills.setAdapter(mEditAdapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private void setDateText(){
        tvStartDate.setText(Html.fromHtml("开始：<font color='#838281'>" + startDate + "</font>"));
        tvEndDate.setText(Html.fromHtml("结束：<font color='#838281'>" + endDate + "</font>"));
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==101&resultCode==300){
            Bundle extras = data.getExtras();
            startDate = extras.getString("dateIn");
            endDate = extras.getString("dateOut");
            setDateText();
        }
    }
}
