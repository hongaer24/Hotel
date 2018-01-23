package cn.houno.hotel.ui.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.widget.AppCompatCheckBox;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.houno.hotel.R;
import cn.houno.hotel.adapter.PerCleanDocAdapter;
import cn.houno.hotel.bean.MyCleanRoomListBean;
import cn.houno.hotel.global.Api;
import cn.houno.hotel.utils.PrefUtils;
import cn.houno.hotel.utils.QUtil;

/**
 * 个人清洁单据
 * Created by qzc on 2017-05-10.
 */

public class PersonalCleanDocActivity extends BaseActivity {


    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.rl_top_bar)
    RelativeLayout rlTopBar;
    @Bind(R.id.tv_region)
    TextView tvRegion;
    @Bind(R.id.tv_head)
    TextView tvHead;
    @Bind(R.id.tv_room_number)
    TextView tvRoomNumber;
    @Bind(R.id.tv_business_date)
    TextView tvBusinessDate;
    @Bind(R.id.tv_floor)
    TextView tvFloor;
    @Bind(R.id.lv_clean_doc)
    ListView lvCleanDoc;
    @Bind(R.id.cb_clean_room)
    AppCompatCheckBox cbCleanRoom;
    @Bind(R.id.ll_list)
    LinearLayout llList;
    @Bind(R.id.tv_message)
    TextView tvMessage;

    private PersonalCleanDocActivity mActivity;

    private List<MyCleanRoomListBean.InfoBean> mList;

    private PerCleanDocAdapter mCleanDocAdapter;
    private String mWorkid;

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_per_clean_doc);
        ButterKnife.bind(this);
    }

    @Override
    public void initData() {
        super.initData();
        mActivity = PersonalCleanDocActivity.this;

        getDataFromServer();
    }


    Dialog tDdialog;

    /*
    * 获取我的工作区域
    * */
    private void getDataFromServer() {
        tDdialog = QUtil.createLoadingDialog(mActivity, "获取数据中");
        tDdialog.show();
        RequestParams params = new RequestParams(Api.GET_MY_WORD);
        params.addBodyParameter("hotelid", PrefUtils.getString(mActivity, "hotelidcode", ""));
        params.addBodyParameter("companyid", PrefUtils.getString(mActivity, "companyinfoidcode", ""));
        params.addBodyParameter("userid", PrefUtils.getString(mActivity, "loginname", ""));
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("1111", result);
                parseData1(result);
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


    /*
    *获取我营业日当天的打扫房间
    * */
    private void getMyCleanRoom() {
        RequestParams params = new RequestParams(Api.GET_MY_ROOM);
        params.addBodyParameter("workid", mWorkid);
        params.addBodyParameter("operateday", PrefUtils.getString(mActivity, "businessday", ""));
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("2222", result);
                parseData2(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
                if (tDdialog.isShowing()) {
                    tDdialog.dismiss();
                }
            }
        });

    }

    private void parseData1(String result) {
        try {
            JSONObject object = new JSONObject(result);
            if (object.getInt("status") == 2) {
                JSONObject info = object.getJSONObject("info");
                String idcode = info.getString("idcode");
                String workloginname = info.getString("workloginname");
                String roomcount = info.getString("roomcount");
                String floorsgroupname = info.getString("floorsgroupname");
                mWorkid = info.getString("id");

                getMyCleanRoom();

                //PrefUtils.setString(mActivity,"workid",workid);

                tvRegion.setText(Html.fromHtml("区域：<font color='#838281'>" + idcode + "</font>"));
                tvHead.setText(Html.fromHtml("负责人：<font color='#838281'>" + workloginname + "</font>"));
                tvRoomNumber.setText(Html.fromHtml("房间数：<font color='#838281'>" + roomcount + "</font>"));
                tvBusinessDate.setText(Html.fromHtml("营业日期：<font color='#838281'>"
                        + PrefUtils.getString(mActivity, "businessday", "") + "</font>"));
                tvFloor.setText(Html.fromHtml("楼层：<font color='#838281'>" + floorsgroupname + "</font>"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void parseData2(String result) {
        try {
            JSONObject object = new JSONObject(result);
            if (object.getInt("status") == 2) {
                MyCleanRoomListBean myCleanRoomListBean = new Gson().fromJson(result, MyCleanRoomListBean.class);
                mList = myCleanRoomListBean.getInfo();
                mCleanDocAdapter = new PerCleanDocAdapter(mActivity, mList);
                lvCleanDoc.setAdapter(mCleanDocAdapter);
                tvMessage.setVisibility(View.GONE);
                llList.setVisibility(View.VISIBLE);
            } else {
                llList.setVisibility(View.GONE);
                tvMessage.setVisibility(View.VISIBLE);
                tvMessage.setText(object.getString("msg"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @OnClick({R.id.iv_back, R.id.cb_clean_room, R.id.btn_add_my_bills, R.id.btn_delete_bills, R.id.btn_complete_bills})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.cb_clean_room:
                //全选或者全取消
                mCleanDocAdapter.setChecked(cbCleanRoom.isChecked());
                break;
            case R.id.btn_add_my_bills:
                //生成单据
                addMyCleanRoomBills();
                break;
            case R.id.btn_delete_bills:
                //删除单据
                deleteMyCleanRoomBills();
                break;
            case R.id.btn_complete_bills:
                //完成单据
                completeMyCleanRoomBills();
                break;
        }

    }

    private void completeMyCleanRoomBills() {
        if (mList == null || mList.size() == 0) {
            return;
        }
        String checkList = getCheckList();
        Log.e("checkList", checkList);
        if (TextUtils.isEmpty(checkList)) {
            showToast("至少选择一项");
            return;
        }
        final Dialog cDialog = QUtil.createLoadingDialog(mActivity, "完成单据");
        cDialog.show();
        RequestParams params = new RequestParams(Api.CLEAN_MY_ROOM);
        params.addBodyParameter("id", checkList);
        params.addBodyParameter("userid", PrefUtils.getString(mActivity, "userid", ""));

        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject object = new JSONObject(result);
                    if (object.getInt("status") == 2) {
                        getMyCleanRoom();
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
                if (cDialog.isShowing()) {
                    cDialog.dismiss();
                }
            }
        });
    }


    private void addMyCleanRoomBills() {
        final Dialog aDialog = QUtil.createLoadingDialog(mActivity, "生成单据");
        aDialog.show();
        RequestParams params = new RequestParams(Api.ADD_MY_BILL);
        params.addBodyParameter("workid", mWorkid);
        params.addBodyParameter("operateday", PrefUtils.getString(mActivity, "businessday", ""));
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("addMyCleanRoomBills",result);
                try {
                    JSONObject object = new JSONObject(result);
                    if (object.getInt("status") == 2) {
                        getMyCleanRoom();
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

    private void deleteMyCleanRoomBills() {
        if (mList == null || mList.size() == 0) {
            return;
        }
        String checkList = getCheckList();
        Log.e("checkList", checkList);
        if (TextUtils.isEmpty(checkList)) {
            showToast("至少选择一项");
            return;
        }
        final Dialog aDialog = QUtil.createLoadingDialog(mActivity, "删除单据");
        aDialog.show();
        RequestParams params = new RequestParams(Api.DELETE_MY_ROOM);
        params.addBodyParameter("id", checkList);

        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject object = new JSONObject(result);
                    if (object.getInt("status") == 2) {
                        getMyCleanRoom();
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
    * 获取勾选的房间id
    * */
    private String getCheckList() {
        List<Boolean> checked = mCleanDocAdapter.getChecked();
        StringBuilder sbCheckList = new StringBuilder();
        for (int i = 0; i < checked.size(); i++) {
            if (checked.get(i)) {
                sbCheckList.append(mList.get(i).getId()).append(",");
            }
        }
        if (!TextUtils.isEmpty(sbCheckList)) {
            return sbCheckList.substring(0, sbCheckList.length() - 1);
        } else {
            return "";
        }
    }


}
