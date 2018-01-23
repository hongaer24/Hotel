package cn.houno.hotel.ui.activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
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
import cn.houno.hotel.adapter.AllotRoomListAdapter;
import cn.houno.hotel.bean.AllotRoomListBean;
import cn.houno.hotel.bean.AllotRoomParamsBean;
import cn.houno.hotel.global.Api;
import cn.houno.hotel.utils.QUtil;

/**
 * 排房
 * Created by qzc on 2017-06-28.
 */

public class AllotRoomListActivity extends BaseActivity {
    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.tv_top_title)
    TextView tvTopTitle;
    @Bind(R.id.rl_top_bar)
    RelativeLayout rlTopBar;
    @Bind(R.id.iv_search)
    ImageView ivSearch;
    @Bind(R.id.lv_room_list)
    ListView mListView;

    private AllotRoomListActivity mActivity;

    private List<AllotRoomListBean.InfoBean> mList = new ArrayList<>();

    private AllotRoomListAdapter mAdapter;

    private AllotRoomParamsBean mParamsBean;

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_allot_room_list);
        ButterKnife.bind(this);
    }

    @Override
    public void initData() {
        super.initData();
        mActivity = AllotRoomListActivity.this;

        Intent intent = getIntent();
        mParamsBean = (AllotRoomParamsBean) intent.getSerializableExtra("data");
        mAdapter = new AllotRoomListAdapter(mActivity, mList);
        mListView.setAdapter(mAdapter);

        getDataFromServer();
    }

    @Override
    public void initEvent() {
        super.initEvent();
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showAllotDialog(position);
            }
        });
    }

    private void showAllotDialog(final int position) {

        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
        builder.setTitle("排房");
        builder.setMessage("确定分配到" + mList.get(position).getIdcode() + "吗？");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                AllotRoom(position);
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }


    /*
    * 排房
    * */
    private void AllotRoom(int position) {
        final Dialog aDialog = QUtil.createLoadingDialog(mActivity, "分配中");
        aDialog.show();
        RequestParams params = new RequestParams(Api.ALLOT_ROOM);
        params.addBodyParameter("OrderIDCode", mParamsBean.getOrderIDCode());
        params.addBodyParameter("userid", mParamsBean.getUserid());
        params.addBodyParameter("BusinessDay", mParamsBean.getBusinessDay());
        params.addBodyParameter("RoomIDCode", mList.get(position).getIdcode());
        params.addBodyParameter("hotelid", mParamsBean.getHotelid());
        params.addBodyParameter("companyid", mParamsBean.getCompanyid());
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("result", result);
                try {
                    JSONObject object = new JSONObject(result);
                    showToast(object.getString("msg"));
                    if (object.getInt("status")==2){
                        setResult(RESULT_OK);
                        finish();
                    }
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
                if (aDialog.isShowing()) {
                    aDialog.dismiss();
                }
            }
        });
    }


    /*
    * 获取房间列表
    * */
    private void getDataFromServer() {
        final Dialog gDialog = QUtil.createLoadingDialog(mActivity, "获取数据");
        gDialog.show();
        RequestParams params = new RequestParams(Api.SEARCH_ALLOT_ROOM);
        params.addBodyParameter("OrderIDCode", mParamsBean.getOrderIDCode());
        params.addBodyParameter("userid", mParamsBean.getUserid());
        params.addBodyParameter("BusinessDay", mParamsBean.getBusinessDay());
        params.addBodyParameter("RoomTypeCode", mParamsBean.getRoomTypeCode());
        params.addBodyParameter("Smoking", mParamsBean.getSmoking());
        params.addBodyParameter("SceneryCode", mParamsBean.getSceneryCode());
        params.addBodyParameter("IsInvented", mParamsBean.getIsInvented());
        params.addBodyParameter("IsAddBeb", mParamsBean.getIsAddBeb());
        params.addBodyParameter("BedCount", mParamsBean.getBedCount());
        params.addBodyParameter("RoomIDCode", mParamsBean.getRoomIDCode());
        params.addBodyParameter("BeginFloor", mParamsBean.getBeginFloor());
        params.addBodyParameter("EndFloor", mParamsBean.getEndFloor());
        params.addBodyParameter("IsReservation", mParamsBean.getIsReservation());
        params.addBodyParameter("RoomStatus_VC", mParamsBean.getRoomStatus_VC());
        params.addBodyParameter("RoomStatus_VD", mParamsBean.getRoomStatus_VD());
        params.addBodyParameter("hotelid", mParamsBean.getHotelid());
        params.addBodyParameter("companyid", mParamsBean.getCompanyid());
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("result", result);
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
            JSONObject object = new JSONObject(result);
            mList.clear();
            if (object.getInt("status") == 2) {
                AllotRoomListBean allotRoomListBean = new Gson().fromJson(result, AllotRoomListBean.class);
                mList = allotRoomListBean.getInfo();
            } else {
                showToast(object.getString("msg"));
            }
            mAdapter.setData(mList);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 101) {
            mParamsBean = (AllotRoomParamsBean) data.getSerializableExtra("data");
            getDataFromServer();
        }
    }

    @OnClick({R.id.iv_back, R.id.iv_search})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_search:
                Intent intent = new Intent(mActivity, SearchAllotRoomActivity.class);
                intent.putExtra("data", mParamsBean);
                startActivityForResult(intent, 101);
                break;
        }
    }
}
