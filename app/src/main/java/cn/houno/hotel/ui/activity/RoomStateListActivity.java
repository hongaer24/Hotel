package cn.houno.hotel.ui.activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatEditText;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import cn.houno.hotel.adapter.RoomStateListAdapter;
import cn.houno.hotel.bean.RoomStatusListBean;
import cn.houno.hotel.global.Api;
import cn.houno.hotel.utils.PrefUtils;
import cn.houno.hotel.utils.QUtil;

/**
 * 房态列表
 * Created by qzc on 2017/5/8 0008.
 */

public class RoomStateListActivity extends BaseActivity {


    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.et_search)
    AppCompatEditText etSearch;
    @Bind(R.id.tv_top_title)
    TextView tvTopTitle;
    @Bind(R.id.iv_search)
    ImageView ivSearch;
    @Bind(R.id.gv_room_status)
    GridView mGridView;
    @Bind(R.id.trl_room_status)
    TwinklingRefreshLayout mRefreshView;
    @Bind(R.id.rl_top_bar)
    RelativeLayout rlTopBar;
   /* @Bind(R.id.tv_vc_count)
    TextView tvVcCount;*/
    @Bind(R.id.ll_vc)
    LinearLayout llVc;
  /*  @Bind(R.id.tv_vd_count)
    TextView tvVdCount;*/
    @Bind(R.id.ll_vd)
    LinearLayout llVd;
   /* @Bind(R.id.tv_oc_count)
    TextView tvOcCount;*/
    @Bind(R.id.ll_oc)
    LinearLayout llOc;
   /* @Bind(R.id.tv_od_count)
    TextView tvOdCount;*/
    @Bind(R.id.ll_od)
    LinearLayout llOd;
   /* @Bind(R.id.tv_ooo_count)
    TextView tvOooCount;*/
    @Bind(R.id.ll_ooo)
    LinearLayout llOoo;
    /*@Bind(R.id.tv_ea_count)
    TextView tvEaCount;*/
    @Bind(R.id.ll_ea)
    LinearLayout llEa;
   /* @Bind(R.id.tv_ed_count)
    TextView tvEdCount;*/
    @Bind(R.id.ll_ed)
    LinearLayout llEd;
   /* @Bind(R.id.tv_inco_count)
    TextView tvIncoCount;*/
    @Bind(R.id.ll_inco)
    LinearLayout llInco;
    /*@Bind(R.id.tv_vip_count)
    TextView tvVipCount;*/
    @Bind(R.id.ll_vip)
    LinearLayout llVip;
   /* @Bind(R.id.tv_all_count)
    TextView tvAllCount;*/
    @Bind(R.id.ll_all)
    LinearLayout llAll;

    private RoomStateListActivity mActivity;

    private List<RoomStatusListBean.InfoBean> list = new ArrayList<>();

    private RoomStateListAdapter mAdapter;

    private boolean isShowEditText = false;

    private static final String ALL = "";
    private static final String VC = "VC";
    private static final String VD = "VD";
    private static final String OC = "OC";
    private static final String OD = "OD";
    private static final String OOO = "OOO";
    private static final String EA = "EA";
    private static final String ED = "ED";
    private static final String INCO = "INCO";
    private static final String VIP = "VIP";

    private String selectType = "";

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_room_status);
        ButterKnife.bind(this);
    }

    @Override
    public void initData() {
        super.initData();
        mActivity = RoomStateListActivity.this;
        mRefreshView.startRefresh();
    }

    @Override
    public void initEvent() {
        super.initEvent();
        mRefreshView.setEnableLoadmore(false);
        mRefreshView.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(TwinklingRefreshLayout refreshLayout) {
                getDataFromServer(selectType);
            }
        });

        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                String keyWord = etSearch.getText().toString().trim();
                mRefreshView.startRefresh();
                if (TextUtils.isEmpty(keyWord)) {
                    if (isShowEditText) {
                        etSearch.setVisibility(View.GONE);
                        tvTopTitle.setVisibility(View.VISIBLE);
                        isShowEditText = !isShowEditText;
                    }
                }
                return false;
            }
        });

        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.e("gridViewClick", list.get(i).getRoomcode());
                showEditRoomStateDialog(list.get(i));
            }
        });


        llVc.setOnClickListener(new RadioButtonClick());
        llVd.setOnClickListener(new RadioButtonClick());
        llOc.setOnClickListener(new RadioButtonClick());
        llOd.setOnClickListener(new RadioButtonClick());
        llOoo.setOnClickListener(new RadioButtonClick());
        llEa.setOnClickListener(new RadioButtonClick());
        llEd.setOnClickListener(new RadioButtonClick());
        llInco.setOnClickListener(new RadioButtonClick());
        llVip.setOnClickListener(new RadioButtonClick());
        llAll.setOnClickListener(new RadioButtonClick());

    }

    private void showEditRoomStateDialog(final RoomStatusListBean.InfoBean roomStatusListBean) {
        String roomstatecode = roomStatusListBean.getSys_roomstatecode();
        String roomstatecode2 = "";
        if (roomstatecode.equals("VC")) {
            roomstatecode2 = "VD";
        } else if (roomstatecode.equals("VD")) {
            roomstatecode2 = "VC";
        } else if (roomstatecode.equals("OC")) {
            roomstatecode2 = "OD";
        } else if (roomstatecode.equals("OD")) {
            roomstatecode2 = "OC";
        } else {
            return;
        }
        final String finalRoomstatecode = roomstatecode2;

        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
        builder.setTitle(roomStatusListBean.getRoomcode() + "  " + roomstatecode);
        builder.setMessage("确认修改成" + " " + roomstatecode2 + "吗？");
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


    private void getRoomStateCount() {
        RequestParams params = new RequestParams(Api.GET_ROOM_STATE_COUNT);
        params.addBodyParameter("hotelid", PrefUtils.getString(mActivity, "hotelidcode", ""));
        params.addBodyParameter("companyid", PrefUtils.getString(mActivity, "companyinfoidcode", ""));
        params.addBodyParameter("operateday", PrefUtils.getString(mActivity, "businessday", ""));
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("get_room_state_count", result);
                parseCountData(result);

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

    private void parseCountData(String result) {
        try {
            JSONObject object = new JSONObject(result);
            if (object.getInt("status") == 2) {
                JSONObject info = object.getJSONObject("info");
             /*   tvVcCount.setText("(" + info.getString("vc") + ")");
                tvVdCount.setText("(" + info.getString("vd") + ")");
                tvOcCount.setText("(" + info.getString("oc") + ")");
                tvOdCount.setText("(" + info.getString("od") + ")");
                tvOooCount.setText("(" + info.getString("ooo") + ")");
                tvEaCount.setText("(" + info.getString("ea") + ")");
                tvEdCount.setText("(" + info.getString("ed") + ")");
                tvIncoCount.setText("(" + info.getString("inco") + ")");
                tvVipCount.setText("(" + info.getString("vip") + ")");*/
                int allCount =
                        Integer.parseInt(info.getString("vc")) +
                                Integer.parseInt(info.getString("vd")) +
                                Integer.parseInt(info.getString("oc")) +
                                Integer.parseInt(info.getString("od")) +
                                Integer.parseInt(info.getString("ooo")) +
                                Integer.parseInt(info.getString("ea")) +
                                Integer.parseInt(info.getString("ed")) +
                                Integer.parseInt(info.getString("inco")) +
                                Integer.parseInt(info.getString("vip"));
                //tvAllCount.setText("(" + allCount + ")");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void getDataFromServer(String type) {
        getRoomStateCount();
        RequestParams params = new RequestParams(Api.GET_ROOM_STATE);
        params.addBodyParameter("hotelid", PrefUtils.getString(mActivity, "hotelidcode", ""));
        params.addBodyParameter("companyid", PrefUtils.getString(mActivity, "companyinfoidcode", ""));
        params.addBodyParameter("operateday", PrefUtils.getString(mActivity, "businessday", ""));
        params.addBodyParameter("keyword", etSearch.getText().toString().trim());
        params.addBodyParameter("RoomStateCode", type);

        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("get_room_state", result);
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
            list.clear();
            if (object.getInt("status") == 2) {
                try {
                    RoomStatusListBean roomStatusListBean = new Gson().fromJson(result, RoomStatusListBean.class);
                    list = roomStatusListBean.getInfo();
                } catch (Exception e) {
                    Log.e("parse_room_state", e.getMessage());
                }
            }

            if (mAdapter == null) {
                mAdapter = new RoomStateListAdapter(mActivity, list);
                mGridView.setAdapter(mAdapter);
            } else {
                mAdapter.setData(list);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("Error", e.getMessage());
        }
    }

    private void editRoomState(String id, String roomstatecode2) {
        final Dialog eDialog = QUtil.createLoadingDialog(mActivity, "正在修改");
        eDialog.show();
        RequestParams params = new RequestParams(Api.EDIT_ROOM_STATE);
        params.addBodyParameter("roomstatusid", id);
        params.addBodyParameter("status", roomstatecode2);
        params.addBodyParameter("userid", PrefUtils.getString(mActivity, "loginname", ""));

        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("edit_room_state", result);
                try {
                    JSONObject object = new JSONObject(result);
                    if (object.getInt("status") == 2) {
                        getDataFromServer(selectType);
                    } else {
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
                if (eDialog.isShowing()) {
                    eDialog.dismiss();
                }
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
                searchRoomState();
                break;
        }
    }

    private void searchRoomState() {
        if (isShowEditText) {
            String keyWord = etSearch.getText().toString().trim();
            mRefreshView.startRefresh();
            if (TextUtils.isEmpty(keyWord)) {
                etSearch.setVisibility(View.GONE);
                tvTopTitle.setVisibility(View.VISIBLE);
            } else {
                return;
            }
        } else {
            tvTopTitle.setVisibility(View.GONE);
            etSearch.setVisibility(View.VISIBLE);
        }
        isShowEditText = !isShowEditText;
    }


    private class RadioButtonClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            setAllUnSelect();
            switch (v.getId()) {
                case R.id.ll_vc:
                    llVc.setSelected(true);
                    selectType = VC;
                    break;
                case R.id.ll_vd:
                    llVd.setSelected(true);
                    selectType = VD;
                    break;
                case R.id.ll_oc:
                    llOc.setSelected(true);
                    selectType = OC;
                    break;
                case R.id.ll_od:
                    llOd.setSelected(true);
                    selectType = OD;
                    break;
                case R.id.ll_ooo:
                    llOoo.setSelected(true);
                    selectType = OOO;
                    break;
                case R.id.ll_ea:
                    llEa.setSelected(true);
                    selectType = EA;
                    break;
                case R.id.ll_ed:
                    llEd.setSelected(true);
                    selectType = ED;
                    break;
                case R.id.ll_inco:
                    llInco.setSelected(true);
                    selectType = INCO;
                    break;
                case R.id.ll_vip:
                    llVip.setSelected(true);
                    selectType = VIP;
                    break;
                case R.id.ll_all:
                    llAll.setSelected(true);
                    selectType = ALL;
                    break;


            }

            mRefreshView.startRefresh();
        }
    }

    private void setAllUnSelect() {
        if (llVc.isSelected()) {
            llVc.setSelected(false);
        }
        if (llVd.isSelected()) {
            llVd.setSelected(false);
        }
        if (llOc.isSelected()) {
            llOc.setSelected(false);
        }
        if (llOd.isSelected()) {
            llOd.setSelected(false);
        }
        if (llOoo.isSelected()) {
            llOoo.setSelected(false);
        }
        if (llEa.isSelected()) {
            llEa.setSelected(false);
        }
        if (llEd.isSelected()) {
            llEd.setSelected(false);
        }
        if (llInco.isSelected()) {
            llInco.setSelected(false);
        }
        if (llVip.isSelected()) {
            llVip.setSelected(false);
        }
        if (llAll.isSelected()) {
            llAll.setSelected(false);
        }

    }
}
