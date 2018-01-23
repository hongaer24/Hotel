package cn.houno.hotel.ui.activity.book;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
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
import cn.houno.hotel.adapter.CompanyAndTravelAdapter;
import cn.houno.hotel.adapter.RoomPriceCodeAdapter;
import cn.houno.hotel.adapter.RoomTypeCodeAdapter;
import cn.houno.hotel.bean.BookInfoListBean;
import cn.houno.hotel.bean.RoomPriceCodeListBean;
import cn.houno.hotel.bean.RoomTypeCodeBean;
import cn.houno.hotel.global.Api;
import cn.houno.hotel.ui.activity.BaseActivity;
import cn.houno.hotel.ui.activity.DatePickerActivity;
import cn.houno.hotel.utils.DateUtil;
import cn.houno.hotel.utils.PrefUtils;
import cn.houno.hotel.utils.QUtil;

/**
 * 预订询价条件查询
 * Created by qzc on 2017-05-23.
 */

public class QueryConditionsActivity extends BaseActivity {

    private final int ROOM_PRICE_CODE = 1;  //房价代码
    private final int ROOM_TYPE_CODE = 2;   //房型代码
    private final int PACKAGE_CODE = 3; //套餐代码
    private final int COMPANY_CODE = 4;  //公司代码
    private final int TRAVEL_CODE = 5;   //旅行社代码


    @Bind(R.id.tv_arr_date)
    TextView tvArrDate;
    @Bind(R.id.tv_leave_date)
    TextView tvLeaveDate;
    @Bind(R.id.tv_live_days)
    TextView tvLiveDays;
    @Bind(R.id.tv_room_num)
    TextView tvRoomNum;
    @Bind(R.id.tv_room_price_code)
    TextView tvRoomPriceCode;
    @Bind(R.id.tv_room_type_code)
    TextView tvRoomTypeCode;
    @Bind(R.id.tv_package_code)
    TextView tvPackageCode;
    @Bind(R.id.tv_company_code)
    TextView tvCompanyCode;
    @Bind(R.id.tv_travel_code)
    TextView tvTravelCode;

    private QueryConditionsActivity mActivity;

    //抵达时间
    private String mArrDate;
    //离店时间
    private String mLeaveDate;
    //共住几晚
    private String mLiveDays;
    //房间数
    private int mRoomNumber = 1;
    //最大房间数
    private int maxRoomNumber = 50;

    //房价列表
    private List<RoomPriceCodeListBean.InfoBean> roomPriceCodeList = new ArrayList<>();
    //房型列表
    private List<RoomTypeCodeBean> roomTypeCodeList = new ArrayList<>();
    //套餐列表 （实体类与房价列表一样）
    private List<RoomPriceCodeListBean.InfoBean> packageCodeList = new ArrayList<>();
    //公司列表
    private List<BookInfoListBean.InfoBean> companyCodeList = new ArrayList<>();
    //旅行社列表
    private List<BookInfoListBean.InfoBean> travelCodeList = new ArrayList<>();

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_query_conditions);
        ButterKnife.bind(this);
    }

    @Override
    public void initData() {
        super.initData();
        mActivity = QueryConditionsActivity.this;

        mArrDate = bBusinessday;
        mLeaveDate = DateUtil.getTheDate(mArrDate, 1);
        tvArrDate.setText(bBusinessday);
        tvLeaveDate.setText(mLeaveDate);

    }

    private void getDataFromServer(final int type) {
        final Dialog gDialog = QUtil.createLoadingDialog(mActivity, "获取数据");
        gDialog.show();

        RequestParams params = getRequestParams(type);

        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("result", result);
                parseData(type, result);
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


    private RequestParams getRequestParams(int type) {


        RequestParams params = null;
        switch (type) {
            case ROOM_PRICE_CODE:
                params = new RequestParams(Api.GET_ROOM_PRICE_CODE);
                break;
            case ROOM_TYPE_CODE:
                params = new RequestParams(Api.GET_ROOM_TYPE_CODE);
                break;
            case PACKAGE_CODE:
                params = new RequestParams(Api.GET_ROOM_PACKAGE_CODE);
                break;
            case COMPANY_CODE:
                params = new RequestParams(Api.GET_COMPANY_OR_TRAVEL_CODE);
                params.addBodyParameter("stype", "company");
                break;
            case TRAVEL_CODE:
                params = new RequestParams(Api.GET_COMPANY_OR_TRAVEL_CODE);
                params.addBodyParameter("stype", "TravelAgent");
                break;

        }
        if (params != null) {
            params.addBodyParameter("hotelid", bHotelId);
            params.addBodyParameter("companyid", bCompanyId);
        }
        return params;
    }

    private void parseData(int type, String result) {
        if (!TextUtils.isEmpty(result)) {
            try {
                JSONObject object = new JSONObject(result);
                if (object.getInt("status") == 2) {
                    Gson gson = new Gson();
                    if (type == ROOM_PRICE_CODE) {
                        RoomPriceCodeListBean roomPriceCodeListBean = gson.fromJson(result, RoomPriceCodeListBean.class);
                        roomPriceCodeList.add(new RoomPriceCodeListBean.InfoBean("", ""));
                        roomPriceCodeList.addAll(roomPriceCodeListBean.getInfo());
                        showRoomPriceCodeDialog();
                    } else if (type == ROOM_TYPE_CODE) {
                        JSONArray info = object.getJSONArray("info");
                        roomTypeCodeList.add(new RoomTypeCodeBean("", "所有"));
                        RoomTypeCodeBean roomTypeCodeBean;
                        for (int i = 0; i < info.length(); i++) {
                            roomTypeCodeBean = new RoomTypeCodeBean();
                            roomTypeCodeBean.setCode(info.getJSONObject(i).getString("idcode"));
                            roomTypeCodeBean.setName(info.getJSONObject(i).getString("name"));
                            roomTypeCodeList.add(roomTypeCodeBean);
                        }
                        showRoomTypeCodeDialog();
                    } else if (type == PACKAGE_CODE) {
                        RoomPriceCodeListBean roomPriceCodeListBean = gson.fromJson(result, RoomPriceCodeListBean.class);
                        packageCodeList.add(new RoomPriceCodeListBean.InfoBean("", "所有"));
                        packageCodeList.addAll(roomPriceCodeListBean.getInfo());
                        showPackageCodeDialog();
                    } else if (type == COMPANY_CODE) {
                        BookInfoListBean infoListBean = gson.fromJson(result, BookInfoListBean.class);
                        companyCodeList.add(new BookInfoListBean.InfoBean("", "所有"));
                        companyCodeList.addAll(infoListBean.getInfo());
                        showCompanyCodeDialog();
                    } else if (type == TRAVEL_CODE) {
                        BookInfoListBean infoListBean = gson.fromJson(result, BookInfoListBean.class);
                        travelCodeList.add(new BookInfoListBean.InfoBean("", "所有"));
                        travelCodeList.addAll(infoListBean.getInfo());
                        showTravelCodeDialog();
                    } else {
                        //
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }


    /*
    * 房价列表
    * */
    private void showRoomPriceCodeDialog() {
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_list_with_title, null);
        AlertDialog.Builder builder =
                new AlertDialog.Builder(this)
                        .setView(view);

        final AlertDialog dialog = builder.show();
        TextView tvTitle = (TextView) view.findViewById(R.id.title);
        ListView lvCode = (ListView) view.findViewById(R.id.lv_content);
        tvTitle.setText("房价码");
        lvCode.setAdapter(new RoomPriceCodeAdapter(mActivity, roomPriceCodeList));

        lvCode.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                dialog.dismiss();
                tvRoomPriceCode.setText(roomPriceCodeList.get(position).getIDCode()
                        // +"  "+roomPriceCodeList.get(position).getName()
                );
            }
        });

        view.findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }


    /*
    * 套餐列表
    * */
    private void showPackageCodeDialog() {
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_list_with_title, null);
        AlertDialog.Builder builder =
                new AlertDialog.Builder(this)
                        .setView(view);

        final AlertDialog dialog = builder.show();
        TextView tvTitle = (TextView) view.findViewById(R.id.title);
        ListView lvCode = (ListView) view.findViewById(R.id.lv_content);
        tvTitle.setText("套餐代码");
        lvCode.setAdapter(new RoomPriceCodeAdapter(mActivity, packageCodeList));

        lvCode.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                dialog.dismiss();
                tvPackageCode.setText(packageCodeList.get(position).getIDCode()
                );
            }
        });

        view.findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }


    /*
    * 房型列表
    * */
    private void showRoomTypeCodeDialog() {
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_list_with_title, null);
        AlertDialog.Builder builder =
                new AlertDialog.Builder(this)
                        .setView(view);

        final AlertDialog dialog = builder.show();
        TextView tvTitle = (TextView) view.findViewById(R.id.title);
        ListView lvCode = (ListView) view.findViewById(R.id.lv_content);
        tvTitle.setText("房型代码");
        lvCode.setAdapter(new RoomTypeCodeAdapter(mActivity, roomTypeCodeList));

        lvCode.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                dialog.dismiss();

                tvRoomTypeCode.setText(roomTypeCodeList.get(position).getCode());

            }
        });

        view.findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    /*
    * 公司列表
    * */
    private void showCompanyCodeDialog() {
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_list_with_title, null);
        AlertDialog.Builder builder =
                new AlertDialog.Builder(this)
                        .setView(view);

        final AlertDialog dialog = builder.show();
        TextView tvTitle = (TextView) view.findViewById(R.id.title);
        ListView lvCode = (ListView) view.findViewById(R.id.lv_content);
        lvCode.setAdapter(new CompanyAndTravelAdapter(mActivity, companyCodeList));
        tvTitle.setText("公司代码");

        lvCode.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                dialog.dismiss();
                tvCompanyCode.setText(companyCodeList.get(position).getIDCode());
            }
        });

        view.findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }


    /*
    * 旅行社列表
    * */
    private void showTravelCodeDialog() {
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_list_with_title, null);
        AlertDialog.Builder builder =
                new AlertDialog.Builder(this)
                        .setView(view);

        final AlertDialog dialog = builder.show();

        ListView lvCode = (ListView) view.findViewById(R.id.lv_content);
        lvCode.setAdapter(new CompanyAndTravelAdapter(mActivity, travelCodeList));

        lvCode.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                dialog.dismiss();
                tvTravelCode.setText(travelCodeList.get(position).getIDCode());
            }
        });

        view.findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }


    @OnClick({R.id.iv_back, R.id.rl_select_date, R.id.iv_room_sub, R.id.iv_room_add, R.id.ll_room_price_code
            , R.id.ll_room_type_code, R.id.ll_package_code, R.id.ll_company_code, R.id.ll_travel_code, R.id.btn_search})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.rl_select_date:
                Intent intent = new Intent(mActivity, DatePickerActivity.class);
                intent.putExtra("date", PrefUtils.getString(mActivity, "businessday", ""));
                startActivityForResult(intent, 101);
                break;
            case R.id.iv_room_sub:
                subRoomNumber();
                break;
            case R.id.iv_room_add:
                addRoomNumber();
                break;
            case R.id.ll_room_price_code:
                if (roomPriceCodeList.size() == 0) {
                    getDataFromServer(ROOM_PRICE_CODE);
                } else {
                    showRoomPriceCodeDialog();
                }
                break;
            case R.id.ll_room_type_code:
                if (roomTypeCodeList.size() == 0) {
                    getDataFromServer(ROOM_TYPE_CODE);
                } else {
                    showRoomTypeCodeDialog();
                }
                break;
            case R.id.ll_package_code:
                if (packageCodeList.size() == 0) {
                    getDataFromServer(PACKAGE_CODE);
                } else {
                    showPackageCodeDialog();
                }
                break;
            case R.id.ll_company_code:
                if (companyCodeList.size() == 0) {
                    getDataFromServer(COMPANY_CODE);
                } else {
                    showCompanyCodeDialog();
                }
                break;
            case R.id.ll_travel_code:
                if (travelCodeList.size() == 0) {
                    getDataFromServer(TRAVEL_CODE);
                } else {
                    showTravelCodeDialog();
                }
                break;
            case R.id.btn_search:
                returnBook();
                break;
        }
    }


    private void addRoomNumber() {
        if (mRoomNumber == maxRoomNumber) {
            return;
        }
        mRoomNumber++;
        tvRoomNum.setText(mRoomNumber + "");

    }

    private void subRoomNumber() {
        if (mRoomNumber == 1) {
            return;
        }
        mRoomNumber--;
        tvRoomNum.setText(mRoomNumber + "");
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == 300) {
            if (requestCode == 101) {
                Bundle extras = data.getExtras();
                mArrDate = extras.getString("dateIn");
                mLeaveDate = extras.getString("dateOut");
                mLiveDays = extras.getString("days");
                tvLiveDays.setText("共" + mLiveDays + "晚");
                tvArrDate.setText(mArrDate);
                tvLeaveDate.setText(mLeaveDate);

            }
        }
    }

    private void returnBook() {
        Intent intent = new Intent();
        intent.putExtra("start_date", tvArrDate.getText().toString());
        intent.putExtra("end_date", tvLeaveDate.getText().toString());
        intent.putExtra("count", mRoomNumber);
        intent.putExtra("price", tvRoomPriceCode.getText().toString());
        intent.putExtra("type", tvRoomTypeCode.getText().toString());
        intent.putExtra("package", tvPackageCode.getText().toString());
        intent.putExtra("company", tvCompanyCode.getText().toString());
        intent.putExtra("travel", tvTravelCode.getText().toString());
        setResult(200, intent);
        finish();
    }
}
