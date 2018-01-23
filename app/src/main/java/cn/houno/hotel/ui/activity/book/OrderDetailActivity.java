package cn.houno.hotel.ui.activity.book;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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
import cn.houno.hotel.bean.AllotRoomParamsBean;
import cn.houno.hotel.global.Api;
import cn.houno.hotel.ui.activity.AllotRoomListActivity;
import cn.houno.hotel.ui.activity.SearchAllotRoomActivity;
import cn.houno.hotel.ui.activity.BaseActivity;
import cn.houno.hotel.ui.activity.book.bean.OrderListBean;
import cn.houno.hotel.utils.QUtil;

/**
 * 订单详情
 * <p>
 * Created by qzc on 2017-06-14.
 */

public class OrderDetailActivity extends BaseActivity {
    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.rl_top_bar)
    RelativeLayout rlTopBar;
    @Bind(R.id.tv_order_id)
    TextView tvOrderId;
    @Bind(R.id.tv_name)
    TextView tvName;
    @Bind(R.id.tv_sex)
    TextView tvSex;
    @Bind(R.id.tv_room_count)
    TextView tvRoomCount;
    @Bind(R.id.tv_people_number)
    TextView tvPeopleNumber;
    @Bind(R.id.tv_date)
    TextView tvDate;
    @Bind(R.id.tv_type)
    TextView tvType;
    @Bind(R.id.tv_package)
    TextView tvPackage;
    @Bind(R.id.tv_rate)
    TextView tvRate;
    @Bind(R.id.tv_price)
    TextView tvPrice;
    @Bind(R.id.tv_from)
    TextView tvFrom;
    @Bind(R.id.tv_allot_room)
    TextView tvAllotRoom;
    @Bind(R.id.tv_separate)
    TextView tvSeparate;

    private OrderDetailActivity mActivity;

    private String mOrderId;

    private OrderListBean.InfoBean dataBean;

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_order_detail);
        ButterKnife.bind(this);
    }

    @Override
    public void initData() {
        super.initData();
        mActivity = OrderDetailActivity.this;

        Intent intent = getIntent();
        dataBean = (OrderListBean.InfoBean) intent.getSerializableExtra("data");

        mOrderId = dataBean.getIdcode();
        tvOrderId.setText(mOrderId);
        tvName.setText(dataBean.getMainname());
        tvSex.setText(dataBean.getSex());
        String roomsCount = dataBean.getRoomscount();
        if (Integer.parseInt(roomsCount) > 1) {
            tvSeparate.setVisibility(View.VISIBLE);
        }
        tvRoomCount.setText(dataBean.getRoomscount());
        tvPeopleNumber.setText(dataBean.getPersoncount());
        tvDate.setText(dataBean.getBegindate().substring(0, 10) + " - " + dataBean.getEnddate().substring(0, 10));
        tvType.setText(dataBean.getRoomtypeidcode() + " - " + dataBean.getRoomtypename());
        tvPackage.setText(dataBean.getPackageidcode() + " - " + dataBean.getPackagename());
        tvRate.setText(dataBean.getRateidcode() + " - " + dataBean.getRatename());
        tvPrice.setText(dataBean.getRatevalues());
        if (TextUtils.isEmpty(dataBean.getSourcename())) {
            tvFrom.setText(dataBean.getLoginname());
        } else {
            tvFrom.setText(dataBean.getLoginname() + "(" + dataBean.getSourcename() + ")");
        }

    }


    @OnClick({R.id.iv_back, R.id.tv_allot_room, R.id.tv_separate})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_allot_room:
                AllotRoom();
                break;
            case R.id.tv_separate:
                separateOrder();
                break;
        }

    }

    private void AllotRoom() {
        Intent intent = new Intent(mActivity, AllotRoomListActivity.class);
        AllotRoomParamsBean params = new AllotRoomParamsBean(
                dataBean.getIdcode(), bLoginName, bBusinessday, dataBean.getRoomtypeidcode(),
                "", "", "", "", "", "", "", "", "", "", "", bHotelId, bCompanyId

        );
        intent.putExtra("data", params);
        startActivityForResult(intent, 106);
    }

    private void editOrder() {
        Intent intent = new Intent(mActivity, NewOrderActivity.class);

        intent.putExtra("data", dataBean);
        intent.putExtra("from", "order");
        startActivityForResult(intent, 105);
    }

    private void separateOrder() {
        final Dialog sDialog = QUtil.createLoadingDialog(mActivity, "正在分离订单");
        sDialog.show();

        RequestParams params = new RequestParams(Api.SEPARATE_ORDER);
        params.addBodyParameter("OrderIDCode", mOrderId);
        params.addBodyParameter("userid", bLoginName);
        params.addBodyParameter("BusinessDay", bBusinessday);

        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("order_separate", result);
                try {
                    JSONObject object = new JSONObject(result);
                    if (object.getInt("status") == 2) {
                        setResult(112);
                        finish();
                    }
                    showToast(object.getString("msg"));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 105) {
                setResult(112);
                finish();
            }
            if (requestCode==106){
                setResult(112);
                finish();
            }
        }
    }
}
