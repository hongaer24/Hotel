package cn.houno.hotel.ui.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;

import org.json.JSONArray;
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
import cn.houno.hotel.adapter.NameCodeListAdapter;
import cn.houno.hotel.adapter.RoomTypeCodeAdapter;
import cn.houno.hotel.bean.AllotRoomParamsBean;
import cn.houno.hotel.bean.NameCodeBean;
import cn.houno.hotel.bean.RoomSceneryBean;
import cn.houno.hotel.bean.RoomTypeCodeBean;
import cn.houno.hotel.global.Api;
import cn.houno.hotel.utils.QUtil;

/**
 * 排房查询
 * Created by qzc on 2017-06-22.
 */

public class SearchAllotRoomActivity extends BaseActivity {


    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.ly_top_bar)
    RelativeLayout lyTopBar;
    @Bind(R.id.et_room_code)
    EditText etRoomCode;
    @Bind(R.id.et_bed_count)
    EditText etBedCount;
    @Bind(R.id.tv_room_type_code)
    TextView tvRoomTypeCode;
    @Bind(R.id.tv_scenery_code)
    TextView tvSceneryCode;
    @Bind(R.id.cb_floor)
    CheckBox cbFloor;
    @Bind(R.id.et_begin_floor)
    EditText etBeginFloor;
    @Bind(R.id.et_end_floor)
    EditText etEndFloor;
    @Bind(R.id.ll_floor)
    LinearLayout llFloor;
    @Bind(R.id.cb_no_smoking)
    CheckBox cbNoSmoking;
    @Bind(R.id.cb_invented)
    CheckBox cbInvented;
    @Bind(R.id.cb_add_bed)
    CheckBox cbAddBed;
    @Bind(R.id.cb_status_vc)
    CheckBox cbStatusVc;
    @Bind(R.id.cb_status_vd)
    CheckBox cbStatusVd;
    @Bind(R.id.btn_query)
    Button btnQuery;


    private SearchAllotRoomActivity mActivity;


    //景观列表
    private List<NameCodeBean> roomSceneryCodeList = new ArrayList<>();

    AllotRoomParamsBean paramsBean;

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_search_allot_room);
        ButterKnife.bind(this);
    }

    @Override
    public void initData() {
        super.initData();
        mActivity = SearchAllotRoomActivity.this;

        Intent intent = getIntent();
        paramsBean = (AllotRoomParamsBean) intent.getSerializableExtra("data");

        tvRoomTypeCode.setText(paramsBean.getRoomTypeCode());
    }


    @Override
    public void initEvent() {
        super.initEvent();

        cbFloor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    llFloor.setVisibility(View.VISIBLE);
                } else {
                    llFloor.setVisibility(View.GONE);
                }
            }
        });
    }


    private void getDataFromServer() {
        final Dialog gDialog = QUtil.createLoadingDialog(mActivity, "获取数据");
        gDialog.show();
        RequestParams params = new RequestParams(Api.GET_ROOM_SCENERY);
        params.addBodyParameter("hotelid", bHotelId);
        params.addBodyParameter("companyid", bCompanyId);

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
        if (!TextUtils.isEmpty(result)) {
            try {
                JSONObject object = new JSONObject(result);
                if (object.getInt("status") == 2) {
                    Gson gson = new Gson();

                    roomSceneryCodeList.clear();
                    RoomSceneryBean roomSceneryBean = gson.fromJson(result, RoomSceneryBean.class);
                    NameCodeBean nameCodeBean;
                    for (int i = 0; i < roomSceneryBean.getInfo().size(); i++) {
                        nameCodeBean = new NameCodeBean(roomSceneryBean.getInfo().get(i).getIdcode(), roomSceneryBean.getInfo().get(i).getName());
                        roomSceneryCodeList.add(nameCodeBean);
                    }
                    showRoomSceneryCodeDialog();

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }


    @OnClick({R.id.iv_back, R.id.tv_scenery_code, R.id.btn_query})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_scenery_code:
                getDataFromServer();
                break;
            case R.id.btn_query:
                setParamsAndReturn();
                break;
        }
    }


    private void showRoomSceneryCodeDialog() {
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_list_with_title, null);
        AlertDialog.Builder builder =
                new AlertDialog.Builder(this)
                        .setView(view);

        final AlertDialog dialog = builder.show();
        TextView tvTitle = (TextView) view.findViewById(R.id.title);
        ListView lvCode = (ListView) view.findViewById(R.id.lv_content);
        tvTitle.setText("景观代码");
        lvCode.setAdapter(new NameCodeListAdapter(mActivity, roomSceneryCodeList));

        lvCode.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                dialog.dismiss();

                tvSceneryCode.setText(roomSceneryCodeList.get(position).getCode());

            }
        });

        view.findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }


    private void setParamsAndReturn() {
        AllotRoomParamsBean params = new AllotRoomParamsBean();
        params.setOrderIDCode(paramsBean.getOrderIDCode());
        params.setUserid(bLoginName);
        params.setBusinessDay(bBusinessday);
        params.setRoomTypeCode(tvRoomTypeCode.getText().toString());
        params.setSmoking(cbNoSmoking.isChecked() ? "1" : "");
        params.setSceneryCode(tvSceneryCode.getText().toString());
        params.setIsInvented(cbInvented.isChecked() ? "1" : "");
        params.setIsAddBeb(cbAddBed.isChecked() ? "1" : "");
        params.setBedCount(etBedCount.getText().toString());

        params.setRoomIDCode(etRoomCode.getText().toString());
        params.setBeginFloor(cbFloor.isChecked() ? etBeginFloor.getText().toString() : "");
        params.setEndFloor(cbFloor.isChecked() ? etEndFloor.getText().toString() : "");
        params.setIsReservation("");
        params.setRoomStatus_VC(cbStatusVc.isChecked() ? "VC" : "");
        params.setRoomStatus_VD(cbStatusVd.isChecked() ? "VD" : "");
        params.setHotelid(bHotelId);
        params.setCompanyid(bCompanyId);

        Intent intent = new Intent();
        intent.putExtra("data", params);
        setResult(RESULT_OK, intent);
        finish();
    }
}
