package cn.houno.hotel.home.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import cn.houno.hotel.home.bean.OrderListBean;
import cn.houno.hotel.home.bean.OrderParamsBean;
import cn.houno.hotel.ui.activity.BaseActivity;
import cn.houno.hotel.ui.activity.book.adapter.OrderListAdapter;

import cn.houno.hotel.utils.QUtil;

/**
 * 订单查询
 * Created by qzc on 2017-06-12.
 */

public class OrderListActivity extends BaseActivity {

    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.iv_search)
    ImageView ivSearch;
    @Bind(R.id.ly_top_bar)
    RelativeLayout lyTopBar;
    @Bind(R.id.tv_msg)
    TextView tvMsg;
    @Bind(R.id.lv_order)
    ListView mListView;

    private OrderListActivity mActivity;

    private List<OrderListBean.InfoBean> mOrderList = new ArrayList<>();

    private OrderListAdapter mAdapter;

    private OrderParamsBean mParamsBean;

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_order_list);
        ButterKnife.bind(this);
    }

    @Override
    public void initData() {
        super.initData();

        mActivity = OrderListActivity.this;

        mAdapter = new OrderListAdapter(mActivity, mOrderList);
        mListView.setAdapter(mAdapter);

        getDataFromServer();
    }

    @Override
    public void initEvent() {
        super.initEvent();
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(mActivity, OrderDetailActivity.class);
                intent.putExtra("data", mOrderList.get(position));
                startActivityForResult(intent, 104);
            }
        });
    }

    private void getDataFromServer() {

        final Dialog gDialog = QUtil.createLoadingDialog(mActivity, "获取数据");
        gDialog.show();
        RequestParams params = new RequestParams(Api.SELECT_ORDER);
        params.addBodyParameter("hotelid", bHotelId);
        params.addBodyParameter("companyid", bCompanyId);
        params.addBodyParameter("MainName", mParamsBean == null ? "" : mParamsBean.getMainname());
        params.addBodyParameter("phone", mParamsBean == null ? "" : mParamsBean.getPhone());
        params.addBodyParameter("OrderIDCode", mParamsBean == null ? "" : mParamsBean.getOrderid());
        params.addBodyParameter("BeginDate", mParamsBean == null ? "" : mParamsBean.getBegindate());
        params.addBodyParameter("EndDate", mParamsBean == null ? "" : mParamsBean.getEnddate());
        params.addBodyParameter("RoomTypeIDCode", mParamsBean == null ? "" : mParamsBean.getRoomtypeidcode());
        params.addBodyParameter("RateIDCode", mParamsBean == null ? "" : mParamsBean.getRateidcode());
        params.addBodyParameter("PackageIDCode", mParamsBean == null ? "" : mParamsBean.getPackageidcode());
        params.addBodyParameter("CopanyIDCode", mParamsBean == null ? "" : mParamsBean.getCompanyidcode());
        params.addBodyParameter("GroupIDCode", mParamsBean == null ? "" : mParamsBean.getGroupidcode());
        params.addBodyParameter("TreavlsIDCode", mParamsBean == null ? "" : mParamsBean.getTravelidcode());
        params.addBodyParameter("linkname", mParamsBean == null ? "" : mParamsBean.getLinkname());

        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("order_list_success", result);
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
                if (gDialog.isShowing()) {
                    gDialog.dismiss();
                }
            }
        });

    }

    private void parseData(String result) {
        try {
            JSONObject jsonObject = new JSONObject(result);
            mAdapter.setData(null);
            if (jsonObject.getInt("status") == 2) {
                OrderListBean orderListBean = new Gson().fromJson(result, OrderListBean.class);
                mOrderList = orderListBean.getInfo();
                mAdapter.setData(mOrderList);
                tvMsg.setVisibility(View.GONE);
                mListView.setVisibility(View.VISIBLE);
            } else {
                mListView.setVisibility(View.GONE);
                tvMsg.setVisibility(View.VISIBLE);
                tvMsg.setText(jsonObject.getString("msg"));
            }

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
                startActivityForResult(new Intent(mActivity, QueryOrderActivity.class), 103);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 103 && resultCode == 111) {
            mParamsBean = (OrderParamsBean) data.getSerializableExtra("data");
            getDataFromServer();
        }

        if (requestCode == 104 && resultCode == 112) {
            getDataFromServer();
        }
    }
}
