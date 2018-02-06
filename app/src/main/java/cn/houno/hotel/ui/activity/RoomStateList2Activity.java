package cn.houno.hotel.ui.activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

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

import cn.houno.hotel.bean.RoomStatusListBean;
import cn.houno.hotel.global.Api;
import cn.houno.hotel.home.adapter.RoomStateListAdapter;
import cn.houno.hotel.utils.PrefUtils;
import cn.houno.hotel.utils.QUtil;

/**
 * Created by qzc on 2017/5/9
 */

public class RoomStateList2Activity extends BaseActivity {

    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.tv_top_title)
    TextView tvTopTitle;
    @Bind(R.id.rl_top_bar)
    RelativeLayout rlTopBar;
    @Bind(R.id.gv_room_status)
    GridView mGridView;
    @Bind(R.id.trl_room_status)
    TwinklingRefreshLayout mRefreshView;

    private RoomStateList2Activity mActivity;

    private List<RoomStatusListBean.InfoBean> list = new ArrayList<>();

    private RoomStateListAdapter mAdapter;


    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_room_state2);
        ButterKnife.bind(this);
    }

    @Override
    public void initData() {
        super.initData();
        mActivity = RoomStateList2Activity.this;

        mRefreshView.startRefresh();
    }

    @Override
    public void initEvent() {
        super.initEvent();

        mRefreshView.setEnableLoadmore(false);
        mRefreshView.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(TwinklingRefreshLayout refreshLayout) {
                getDataFromServer();
            }
        });



        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.e("gridViewClick",list.get(i).getRoomcode());
                showEditRoomStateDialog(list.get(i));
            }
        });
    }

    private void showEditRoomStateDialog(final RoomStatusListBean.InfoBean roomStatusListBean) {
        String roomstatecode = roomStatusListBean.getSys_roomstatecode();
        String roomstatecode2 = "";
        if (roomstatecode.equals("VC")){
            roomstatecode2="VD";
        }else if (roomstatecode.equals("VD")){
            roomstatecode2="VC";
        }else if (roomstatecode.equals("OC")){
            roomstatecode2="OD";
        }else if (roomstatecode.equals("OD")){
            roomstatecode2="OC";
        }else {
            return;
        }
        final String finalRoomstatecode = roomstatecode2;

        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
        builder.setTitle(roomStatusListBean.getRoomcode()+"  "+roomstatecode);
        builder.setMessage("确认修改成"+" "+roomstatecode2+"吗？");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                editRoomState(roomStatusListBean.getId(), finalRoomstatecode);
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

    private void getDataFromServer() {
        RequestParams params = new RequestParams(Api.GET_ROOM_STATE);
        params.addBodyParameter("hotelid", PrefUtils.getString(mActivity, "hotelidcode", ""));
        params.addBodyParameter("companyid", PrefUtils.getString(mActivity, "companyinfoidcode", ""));
        params.addBodyParameter("operateday", PrefUtils.getString(mActivity, "businessday", ""));
//        params.addBodyParameter("keyword", etSearch.getText().toString().trim());
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
                mRefreshView.finishRefreshing();
            }
        });
    }

    private void parseData(String result) {
        try {
            JSONObject object = new JSONObject(result);
            if (object.getInt("status") == 2) {

                try {
                    RoomStatusListBean roomStatusListBean = new Gson().fromJson(result, RoomStatusListBean.class);
                    list = roomStatusListBean.getInfo();
                } catch (Exception e) {
                    Log.e("123123123", e.getMessage());
                }
                if (mAdapter == null) {
                    mAdapter = new RoomStateListAdapter(mActivity, list);
                    mGridView.setAdapter(mAdapter);
                } else {
                    mAdapter.setData(list);
                }
            } else {

            }
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("Error", e.getMessage());
        }
    }

    private void editRoomState(String id,String roomstatecode2){
        final Dialog eDialog = QUtil.createLoadingDialog(mActivity, "正在修改");
        eDialog.show();
        RequestParams params = new RequestParams(Api.EDIT_ROOM_STATE);
        params.addBodyParameter("roomstatusid", id);
        params.addBodyParameter("status",  roomstatecode2);
        params.addBodyParameter("userid", PrefUtils.getString(mActivity, "loginname", ""));

        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("11", result);
                try {
                    JSONObject object = new JSONObject(result);
                    if (object.getInt("status")==2){
                       getDataFromServer();
                    }else {
                        showToast(object.getString("msg"));
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
                if (eDialog.isShowing()){
                    eDialog.dismiss();
                }
            }
        });
    }




    @OnClick(R.id.iv_back)
    public void onClick() {
        finish();
    }
}
