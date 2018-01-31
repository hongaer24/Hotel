package cn.houno.hotel.ui.fragment.room;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;

import com.google.gson.Gson;
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
import cn.houno.hotel.R;
import cn.houno.hotel.adapter.RoomStateListAdapter;
import cn.houno.hotel.bean.RoomStatusListBean;
import cn.houno.hotel.global.Api;
import cn.houno.hotel.ui.fragment.BaseFragment;
import cn.houno.hotel.utils.PrefUtils;
import cn.houno.hotel.utils.QUtil;


/**
 * 项目名称：Houniaolvju
 * 类描述：首页-景点门票
 * 创建人：qzc
 * 创建时间：2016/12/13 14:09
 * 修改人：qzc
 * 修改时间：2016/12/13 14:09
 * 修改备注：
 */

public class ScenicTicketPage extends BaseFragment implements View.OnClickListener {


    @Bind(R.id.ll_vc)
    LinearLayout llVc;
    @Bind(R.id.ll_vd)
    LinearLayout llVd;
    @Bind(R.id.ll_oc)
    LinearLayout llOc;
    @Bind(R.id.ll_od)
    LinearLayout llOd;
    @Bind(R.id.ll_ooo)
    LinearLayout llOoo;
    @Bind(R.id.ll_ea)
    LinearLayout llEa;
    @Bind(R.id.ll_ed)
    LinearLayout llEd;
    @Bind(R.id.ll_inco)
    LinearLayout llInco;
    @Bind(R.id.ll_vip)
    LinearLayout llVip;
    @Bind(R.id.ll_all)
    LinearLayout llAll;
    @Bind(R.id.gv_room_status)
    GridView gvRoomStatus;
    @Bind(R.id.trl_room_status)
    TwinklingRefreshLayout trlRoomStatus;
    private String selectType = "";
    private List<RoomStatusListBean.InfoBean> list = new ArrayList<>();
    private RoomStateListAdapter mAdapter;
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

    private boolean isShowEditText = false;



    /* @Nullable
     @Override
     public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         //initView(mView);
         initData();
         initEvent();
         ButterKnife.bind(this, mView);
         return mView;

     }*/
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {
       View mView = inflater.inflate(R.layout.listitem_scenic, container, false);
        return mView;
    }

    public void initData() {
        trlRoomStatus.startRefresh();
        //Bundle arguments = getArguments();

    }

    @Override
    public void initEvent() {
        super.initEvent();
        trlRoomStatus.setEnableLoadmore(false);
        trlRoomStatus.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(TwinklingRefreshLayout refreshLayout) {
                getDataFromServer(selectType);
            }
        });


        gvRoomStatus.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.e("gridViewClick", list.get(i).getRoomcode());
                showEditRoomStateDialog(list.get(i));
            }
        });


        llVc.setOnClickListener(this);
        llVd.setOnClickListener(this);
        llOc.setOnClickListener(this);
        llOd.setOnClickListener(this);
        llOoo.setOnClickListener(this);
        llEa.setOnClickListener(this);
        llEd.setOnClickListener(this);
        llInco.setOnClickListener(this);
        llVip.setOnClickListener(this);
        llAll.setOnClickListener(this);

    }


    @Override
    protected void initFindViewById(View view) {

    }

    private void showEditRoomStateDialog(final RoomStatusListBean.InfoBean roomStatusListBean) {
        String roomstatecode = roomStatusListBean.getSys_roomstatecode();
        String roomstatecode2 = "";
        if (roomstatecode.equals("VC")) {
            roomstatecode2 = "空脏房";
        } else if (roomstatecode.equals("VD")) {
            roomstatecode2 = "干净房";
        } else if (roomstatecode.equals("OC")) {
            roomstatecode2 = "在住脏房";
        } else if (roomstatecode.equals("OD")) {
            roomstatecode2 = "在住净房";
        } else {
            return;
        }
        final String finalRoomstatecode = roomstatecode2;

        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
       // builder.setTitle(roomStatusListBean.getRoomcode() + "  " + roomstatecode);
        builder.setMessage("确认设置房间" +roomStatusListBean.getRoomcode()+ "成" + roomstatecode2 + "吗？");
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

    private void getDataFromServer(String type) {
        //getRoomStateCount();
        RequestParams params = new RequestParams(Api.GET_ROOM_STATE);
        params.addBodyParameter("hotelid", PrefUtils.getString(mActivity, "hotelidcode", ""));
        params.addBodyParameter("companyid", PrefUtils.getString(mActivity, "companyinfoidcode", ""));
        params.addBodyParameter("operateday", PrefUtils.getString(mActivity, "businessday", ""));
        //params.addBodyParameter("keyword", etSearch.getText().toString().trim());
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
                trlRoomStatus.finishRefreshing();
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
                mAdapter = new RoomStateListAdapter(getContext(), list);
                gvRoomStatus.setAdapter(mAdapter);
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
                        //showToast(object.getString("msg"));
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


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

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

        trlRoomStatus.startRefresh();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }
}



