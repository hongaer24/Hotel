package cn.houno.hotel.ui.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.inqbarna.tablefixheaders.TableFixHeaders;

import org.json.JSONArray;
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
import cn.houno.hotel.adapter.MatrixTableAdapter;
import cn.houno.hotel.adapter.RoomTypeCodeAdapter;
import cn.houno.hotel.bean.RoomTypeCodeBean;
import cn.houno.hotel.bean.SearchRoomNumberBean;
import cn.houno.hotel.global.Api;
import cn.houno.hotel.utils.DateUtil;
import cn.houno.hotel.utils.PrefUtils;
import cn.houno.hotel.utils.QUtil;

/**
 * 房间数查询
 * Created by qzc on 2017-05-12.
 */

public class SearchRoomNumberActivity extends BaseActivity {


    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.table)
    TableFixHeaders tableFixHeaders;
    @Bind(R.id.rl_top_bar)
    RelativeLayout rlTopBar;
    @Bind(R.id.tv_start_date)
    TextView tvStartDate;
    @Bind(R.id.tv_end_date)
    TextView tvEndDate;
    @Bind(R.id.tv_room_type)
    TextView tvRoomType;
    @Bind(R.id.btn_search)
    Button btnSearch;

    private SearchRoomNumberActivity mActivity;

    private String[][] roomNumber;

    private MatrixTableAdapter<String> mMatrixTableAdapter;

    private List<RoomTypeCodeBean> roomCodeList = new ArrayList<>();

    private String startDate;
    private String endDate;

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_search_room_number);
        ButterKnife.bind(this);
    }

    @Override
    public void initData() {
        super.initData();

        mActivity = SearchRoomNumberActivity.this;
        startDate = bBusinessday;
        endDate = DateUtil.getTomorrowTime(startDate);
        setDateText();
        getDataFromServer();
        getRoomTypeCodeList();

    }



    @Override
    public void initEvent() {
        super.initEvent();

    }

    private void setDateText(){
        tvStartDate.setText(Html.fromHtml("开始：<font color='#838281'>" + startDate + "</font>"));
        tvEndDate.setText(Html.fromHtml("结束：<font color='#838281'>" + endDate + "</font>"));
    }

    //hotelid=LF001&companyid=BOTAO&startdate=2016-11-29&enddate=2016-12-02&operateday=2016-11-29&days=&keyword=
    private void getDataFromServer() {

        final Dialog sDialog = QUtil.createLoadingDialog(mActivity, "正在查询");
        sDialog.show();
        String keyWord = "";
        if (!tvRoomType.getText().toString().contains("所有")) {
            if (tvRoomType.getText().toString().contains("房型代码：")) {
                keyWord = tvRoomType.getText().toString().substring(5);
            }
        } else {

        }
        RequestParams params = new RequestParams(Api.SEARCH_ROOM_NUMBER);
        params.addBodyParameter("hotelid", PrefUtils.getString(mActivity, "hotelidcode", ""));
        params.addBodyParameter("companyid", PrefUtils.getString(mActivity, "companyinfoidcode", ""));
        params.addBodyParameter("startdate", startDate);
        params.addBodyParameter("enddate", endDate);
        params.addBodyParameter("userid", PrefUtils.getString(mActivity, "userid", ""));
        params.addBodyParameter("days", "");
        params.addBodyParameter("keyword", keyWord);

        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("TABLE", result);
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
                if (sDialog.isShowing()) {
                    sDialog.dismiss();
                }
            }
        });

    }


    private void parseData(String result) {

        try {
            JSONObject object = new JSONObject(result);
            if (object.getInt("status") == 2) {
                SearchRoomNumberBean searchRoomNumberBean = null;
                try {
                    searchRoomNumberBean = new Gson().fromJson(result, SearchRoomNumberBean.class);
                } catch (Exception e) {
                    Log.e("Error1:", e.getMessage());
                }

                if ((searchRoomNumberBean != null ? searchRoomNumberBean.getInfo() : null) != null) {
                    int horizontalSize = searchRoomNumberBean.getInfo().get(0).getInfo().size();
                    int verticalSize = searchRoomNumberBean.getInfo().size();

                    roomNumber = new String[verticalSize + 1][horizontalSize + 1];
                    roomNumber[0][0] = "日期/房型";
                    //第一行
                    for (int i = 0; i < horizontalSize; i++) {
                        roomNumber[0][i + 1] = searchRoomNumberBean.getInfo().get(0).getInfo().get(i).getIdcode();
                    }
                    //第一列
                    for (int i = 0; i < verticalSize; i++) {
                        roomNumber[i + 1][0] = searchRoomNumberBean.getInfo().get(i).getDay();
                    }
                    //
                    for (int i = 0; i < verticalSize; i++) {
                        //每一行的数据
                        for (int j = 0; j < horizontalSize; j++) {
                            try {
                                if (null == searchRoomNumberBean.getInfo().get(i).getInfo().get(j).getNum()) {
                                    roomNumber[i + 1][j + 1] = "";
                                } else {
                                    roomNumber[i + 1][j + 1] = searchRoomNumberBean.getInfo().get(i).getInfo().get(j).getNum();
                                }
                            } catch (Exception e) {
                                Log.e("Error2:", e.getMessage());
                            }
                        }
                    }
                }
                if (mMatrixTableAdapter == null) {
                    mMatrixTableAdapter = new MatrixTableAdapter<>(this, roomNumber);
                    tableFixHeaders.setAdapter(mMatrixTableAdapter);
                } else {
                    mMatrixTableAdapter.setData(this, roomNumber);
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("ERROR:", e.getMessage());
        }
    }

    private void getRoomTypeCodeList() {
        RequestParams params = new RequestParams(Api.GET_ROOM_TYPE_CODE);
        params.addBodyParameter("hotelid",bHotelId );
        params.addBodyParameter("companyid", bCompanyId);

        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("ROOM_CODE", result);
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

            }
        });

    }

    private void parseData2(String result) {
        try {
            JSONObject object = new JSONObject(result);

            if (object.getInt("status") == 2) {
                JSONArray info = object.getJSONArray("info");
                RoomTypeCodeBean roomTypeCodeBean;
                roomCodeList.add(new RoomTypeCodeBean("", "所有"));
                for (int i = 0; i < info.length(); i++) {
                    roomTypeCodeBean = new RoomTypeCodeBean();
                    //roomCodeList.add(idcode + " " + info.getJSONObject(i).getString("name"));
                    roomTypeCodeBean.setCode(info.getJSONObject(i).getString("idcode"));
                    roomTypeCodeBean.setName(info.getJSONObject(i).getString("name"));
                    roomCodeList.add(roomTypeCodeBean);
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("ERROR:", e.getMessage());
        }
    }


    @OnClick({R.id.iv_back, R.id.tv_room_type, R.id.ll_date_pick, R.id.btn_search})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_room_type:
                showRoomTypeDialog();
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


    private void showRoomTypeDialog() {
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_list_with_title, null);
        AlertDialog.Builder builder =
                new AlertDialog.Builder(this)
                        .setView(view);

        final AlertDialog dialog = builder.show();

        ListView lvCode = (ListView) view.findViewById(R.id.lv_content);
        lvCode.setAdapter(new RoomTypeCodeAdapter(mActivity, roomCodeList));

        lvCode.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                dialog.dismiss();
                if (position == 0) {
                    tvRoomType.setText("房型代码：" + roomCodeList.get(position).getName());
                } else {
                    tvRoomType.setText("房型代码：" + roomCodeList.get(position).getCode());
                }
            }
        });

        view.findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
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
