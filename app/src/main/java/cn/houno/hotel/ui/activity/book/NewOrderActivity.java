package cn.houno.hotel.ui.activity.book;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatSpinner;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;

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
import cn.houno.hotel.adapter.RoomPriceCodeAdapter;
import cn.houno.hotel.adapter.RoomTypeCodeAdapter;
import cn.houno.hotel.bean.RoomPriceCodeListBean;
import cn.houno.hotel.bean.RoomTypeCodeBean;
import cn.houno.hotel.global.Api;
import cn.houno.hotel.ui.activity.BaseActivity;
import cn.houno.hotel.ui.activity.DatePickerActivity;
import cn.houno.hotel.ui.activity.book.adapter.CountryListAdapter;
import cn.houno.hotel.ui.activity.book.adapter.RoomRateAdapter;
import cn.houno.hotel.ui.activity.book.bean.AreaListBean;
import cn.houno.hotel.ui.activity.book.bean.BookQueryListBean;
import cn.houno.hotel.bean.BookInfoListBean;
import cn.houno.hotel.utils.DateUtil;
import cn.houno.hotel.utils.QUtil;

/**
 * 创建订单
 * Created by qzc on 2017-06-07.
 */

public class NewOrderActivity extends BaseActivity {


    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.rl_top_bar)
    RelativeLayout rlTopBar;
    @Bind(R.id.et_name)
    EditText etName;
    @Bind(R.id.iv_contract)
    ImageView ivContract;
    @Bind(R.id.sp_sex)
    AppCompatSpinner spSex;
    @Bind(R.id.tv_country)
    TextView tvCountry;
    @Bind(R.id.tv_province)
    TextView tvProvince;
    @Bind(R.id.tv_city)
    TextView tvCity;
    @Bind(R.id.et_card)
    EditText etCard;
    @Bind(R.id.et_phone)
    EditText etPhone;
    @Bind(R.id.tv_arr_txt)
    TextView tvArrTxt;
    @Bind(R.id.tv_arr_date)
    TextView tvStartDate;
    @Bind(R.id.fl_arr_txt)
    FrameLayout flArrTxt;
    @Bind(R.id.ll_arr_date)
    LinearLayout llArrDate;
    @Bind(R.id.tv_lea_date)
    TextView tvEndDate;
    @Bind(R.id.tv_total_days)
    TextView tvTotalDays;
    @Bind(R.id.tv_lea_time)
    TextView tvEndTime;
    @Bind(R.id.tv_room_count)
    TextView tvRoomCount;
    @Bind(R.id.fl_room_count)
    FrameLayout flRoomCount;
    @Bind(R.id.tv_people_number)
    TextView tvPeopleNumber;
    @Bind(R.id.fl_people_number)
    FrameLayout flPeopleNumber;
    @Bind(R.id.tv_room_type)
    TextView tvRoomType;
    @Bind(R.id.tv_package)
    TextView tvPackage;
    @Bind(R.id.tv_rate_code)
    TextView tvRateCode;
    @Bind(R.id.tv_price)
    TextView tvPrice;
    @Bind(R.id.et_info)
    EditText etInfo;

    private NewOrderActivity mActivity;

    private BookInfoListBean.InfoBean mUserInfoBean;

    private String mUserIdCode = "";
    private String mOrderIdCode = "";

    private String mSex = "男";

    private String mStartDate;
    private String mEndDate;
    private String mLiveDays = "1";
    private String mEndTime = "12:00:00";

    private int mRoomCount = 1;
    private int mMaxRoomCount = 10;
    private int mPersonCount = 1;

    private String mRoomTypeIDCode = "";
    private String mRoomTypeName = "";
    private String mRateIDCode = "";
    private String mRateValues = "";
    private String mRateName = "";
    private String mPackageIDCode = "";
    private String mPackageName = "";


    private static final int COUNTRY = 10;
    private static final int PROVINCES = 11;
    private static final int CITY = 12;

    private static final int ROOM_TYPE = 13;
    private static final int PACKAGE = 14;
    private static final int ROOM_RATE = 15;

    private static final int ROOM_COUNT = 16;


    private List<AreaListBean.InfoBean> mCountryList = new ArrayList<>();
    private int mCountrySelect = -1;

    private List<AreaListBean.InfoBean> mProvincesList = new ArrayList<>();
    private int mProvincesSelect = -1;

    private List<AreaListBean.InfoBean> mCityList = new ArrayList<>();


    private List<RoomTypeCodeBean> mRoomTypeCodeList = new ArrayList<>();


    private List<RoomPriceCodeListBean.InfoBean> mPackageList = new ArrayList<>();


    private List<BookQueryListBean.InfoBean> mRoomRateList = new ArrayList<>();


    private String mFrom;


    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_new_order);
        ButterKnife.bind(this);
    }

    @Override
    public void initData() {
        super.initData();
        mActivity = NewOrderActivity.this;

        Intent intent = getIntent();
        if (null != intent.getSerializableExtra("data")) {
            mFrom = intent.getStringExtra("from");
            if (TextUtils.equals("query", mFrom)) {
                setFromQueryText(intent);
                mStartDate = bBusinessday;
                mEndDate = DateUtil.getTheDate(bBusinessday, 1);
            }
        } else {
            mStartDate = bBusinessday;
            mEndDate = DateUtil.getTheDate(bBusinessday, 1);
        }
        setTheDate();
    }

    /*
    * 预订询价
    * */
    private void setFromQueryText(Intent intent) {
        BookQueryListBean.InfoBean data = (BookQueryListBean.InfoBean) intent.getSerializableExtra("data");

        mMaxRoomCount = Integer.parseInt(data.getRoomcount());

        mPackageIDCode = data.getPackageheaderidcode();
        mRoomTypeIDCode = data.getRoomtypeidcode();
        mRateIDCode = data.getRoomratemainidcode();
        mRateValues = data.getAmountsex();
        mRateName = data.getRoomratemainname();

        tvPrice.setText(mRateValues);
        tvRateCode.setText(mRateName);

    }


    private void setTheDate() {
        tvStartDate.setText(mStartDate);
        tvEndDate.setText(mEndDate);
        tvTotalDays.setText("共" + mLiveDays + "晚");
    }

    @Override
    public void initEvent() {
        super.initEvent();
        spSex.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] sex = getResources().getStringArray(R.array.sex);
                mSex = sex[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

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

    private void parseData(int type, String result) {
        if (!TextUtils.isEmpty(result)) {
            try {
                JSONObject object = new JSONObject(result);
                if (object.getInt("status") == 2) {
                    Gson gson = new Gson();
                    if (type == COUNTRY) {
                        AreaListBean areaListBean = gson.fromJson(result, AreaListBean.class);
                        mCountryList = areaListBean.getInfo();
                        showCountryListDialog();
                    } else if (type == PROVINCES) {
                        AreaListBean provincesListBean = gson.fromJson(result, AreaListBean.class);
                        mProvincesList = provincesListBean.getInfo();
                        showProvincesDialog();
                    } else if (type == CITY) {
                        AreaListBean cityListBean = gson.fromJson(result, AreaListBean.class);
                        mCityList = cityListBean.getInfo();
                        showCityDialog();
                    } else if (type == ROOM_TYPE) {
                        JSONArray info = object.getJSONArray("info");
                        RoomTypeCodeBean roomTypeCodeBean;
                        mRoomTypeCodeList.clear();
                        mRoomTypeCodeList.add(new RoomTypeCodeBean("", "所有"));
                        for (int i = 0; i < info.length(); i++) {
                            roomTypeCodeBean = new RoomTypeCodeBean();
                            roomTypeCodeBean.setCode(info.getJSONObject(i).getString("idcode"));
                            roomTypeCodeBean.setName(info.getJSONObject(i).getString("name"));
                            mRoomTypeCodeList.add(roomTypeCodeBean);
                        }
                        showRoomTypeDialog();
                    } else if (type == PACKAGE) {
                        RoomPriceCodeListBean roomPriceCodeListBean = gson.fromJson(result, RoomPriceCodeListBean.class);
                        mPackageList.add(new RoomPriceCodeListBean.InfoBean("", "所有"));
                        mPackageList.addAll(roomPriceCodeListBean.getInfo());
                        showPackageCodeDialog();
                    } else if (type == ROOM_RATE) {
                        BookQueryListBean bookQueryListBean = gson.fromJson(result, BookQueryListBean.class);
                        mRoomRateList = bookQueryListBean.getInfo();
                        showRateCodeDialog();
                    } else {

                    }
                } else {
                    showToast(object.getString("msg"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private void showTimePickerDialog() {
        new TimePickerDialog(mActivity, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                mEndTime = DateUtil.addZero(hourOfDay) + ":" + DateUtil.addZero(minute) + ":00";
                tvEndTime.setText(mEndTime);
                tvEndTime.setTextColor(ContextCompat.getColor(mActivity, R.color.app_theme_black));
            }
        }, 12, 0, true).show();
    }

    private void showRoomCountDialog() {
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_number_picker, null);
        AlertDialog.Builder builder =
                new AlertDialog.Builder(this)
                        .setView(view);

        final AlertDialog dialog = builder.show();
        final NumberPicker numberPicker = (NumberPicker) view.findViewById(R.id.np_number_picker);
        TextView tvTitle = (TextView) view.findViewById(R.id.tv_title);
        tvTitle.setText("房间数");
        numberPicker.setMaxValue(mMaxRoomCount);
        numberPicker.setMinValue(1);
        numberPicker.setValue(mRoomCount);

        view.findViewById(R.id.tv_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                mRoomCount = numberPicker.getValue();
                tvRoomCount.setText(String.valueOf(mRoomCount));
            }
        });

        view.findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }


    private void showPeopleNumberDialog() {
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_number_picker, null);
        AlertDialog.Builder builder =
                new AlertDialog.Builder(this)
                        .setView(view);

        final AlertDialog dialog = builder.show();
        final NumberPicker numberPicker = (NumberPicker) view.findViewById(R.id.np_number_picker);
        TextView tvTitle = (TextView) view.findViewById(R.id.tv_title);
        tvTitle.setText("人数");
        numberPicker.setMaxValue(10);
        numberPicker.setMinValue(1);
        numberPicker.setValue(1);

        view.findViewById(R.id.tv_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                mPersonCount = numberPicker.getValue();
                tvPeopleNumber.setText(String.valueOf(mPersonCount));
            }
        });

        view.findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }


    private void showCountryListDialog() {

        showDialog("国家", new CountryListAdapter(mActivity, mCountryList), new OnItemClickListener() {
            @Override
            public void OnItemClickListener(int position) {
                tvCountry.setText(mCountryList.get(position).getName());
                mCountrySelect = position;
            }
        });
    }

    private void showProvincesDialog() {

        showDialog("省份", new CountryListAdapter(mActivity, mProvincesList), new OnItemClickListener() {
            @Override
            public void OnItemClickListener(int position) {
                tvProvince.setText(mProvincesList.get(position).getName());
                mProvincesSelect = position;
            }
        });
    }

    private void showCityDialog() {

        showDialog("城市", new CountryListAdapter(mActivity, mCityList), new OnItemClickListener() {
            @Override
            public void OnItemClickListener(int position) {
                tvCity.setText(mCityList.get(position).getName());

            }
        });
    }

    private void showRoomTypeDialog() {

        showDialog("房型", new RoomTypeCodeAdapter(mActivity, mRoomTypeCodeList), new OnItemClickListener() {
            @Override
            public void OnItemClickListener(int position) {

                mRoomTypeIDCode = mRoomTypeCodeList.get(position).getCode();
                mRoomTypeName = mRoomTypeCodeList.get(position).getName();
                tvRoomType.setText(mRoomTypeName);

                //清空套餐和房价码
                mPackageList.clear();
                tvPackage.setText("请选择套餐");
                mRoomRateList.clear();
                tvRateCode.setText("请选择房价码");
                tvPrice.setText("");
            }
        });
    }

    private void showPackageCodeDialog() {
        showDialog("套餐", new RoomPriceCodeAdapter(mActivity, mPackageList), new OnItemClickListener() {
            @Override
            public void OnItemClickListener(int position) {

                mPackageIDCode = mPackageList.get(position).getIDCode();
                mPackageName = mPackageList.get(position).getName();
                tvPackage.setText(mPackageName);

                mRoomRateList.clear();
                tvRateCode.setText("请选择房价码");
                tvPrice.setText("");
            }
        });
    }

    private void showRateCodeDialog() {
        showDialog("房价", new RoomRateAdapter(mActivity, mRoomRateList), new OnItemClickListener() {
            @Override
            public void OnItemClickListener(int position) {

                mRateIDCode = mRoomRateList.get(position).getRoomratemainidcode();
                mRateName = mRoomRateList.get(position).getRoomratemainname();
                mRateValues = mRoomRateList.get(position).getAmountsex();

                tvRateCode.setText(mRateName);
                tvPrice.setText(mRateValues);
            }
        });
    }

    private RequestParams getRequestParams(int type) {

        RequestParams params = null;
        switch (type) {
            case COUNTRY:
                params = new RequestParams(Api.GET_COUNTRY_CODE);
                break;
            case PROVINCES:
                params = new RequestParams(Api.GET_STATE_CODE);
                if (mCountryList.size() != 0) {
                    params.addBodyParameter("state", mCountryList.get(mCountrySelect).getIdcode());
                } else {

                }
                break;
            case CITY:
                params = new RequestParams(Api.GET_CITY_CODE);
                params.addBodyParameter("city", mProvincesList.get(mProvincesSelect).getIdcode());
                break;
            case ROOM_TYPE:
                params = new RequestParams(Api.GET_ROOM_TYPE_CODE);
                break;
            case PACKAGE:
                params = new RequestParams(Api.GET_ROOM_PACKAGE_CODE);
                break;
            case ROOM_RATE:
                params = new RequestParams(Api.BOOK_QUERY);
                params.addBodyParameter("startdate", mStartDate);
                params.addBodyParameter("enddate", mEndDate);
                params.addBodyParameter("roomtypecode", tvRoomType.getText().toString().contains("选择") ? "" : mRoomTypeIDCode);
                params.addBodyParameter("roomcount", mRoomCount + "");
                params.addBodyParameter("packagecode", tvPackage.getText().toString().contains("选择") ? "" : mPackageIDCode);
                break;

        }
        if (params != null) {
            params.addBodyParameter("hotelid", bHotelId);
            params.addBodyParameter("companyid", bCompanyId);
        }
        return params;
    }


    @OnClick({R.id.iv_back, R.id.iv_contract, R.id.tv_country, R.id.tv_province, R.id.tv_city, R.id.tv_arr_date
            , R.id.tv_lea_date, R.id.tv_lea_time, R.id.tv_room_count, R.id.tv_people_number
            , R.id.tv_room_type, R.id.tv_package, R.id.tv_rate_code, R.id.btn_submit_order})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_contract:
                startActivityForResult(new Intent(mActivity, GetPersonalInfoActivity.class), 101);
                break;
            case R.id.tv_country:
                getDataFromServer(COUNTRY);
                break;
            case R.id.tv_province:
                if (tvCountry.getText().toString().contains("选择")) {
                    showToast("请选择国家");
                } else {
                    getDataFromServer(PROVINCES);
                }
                break;
            case R.id.tv_city:
                if (tvProvince.getText().toString().contains("选择")) {
                    showToast("请选择省份");
                } else {
                    getDataFromServer(CITY);
                }
                break;
            case R.id.tv_arr_date:
            case R.id.tv_lea_date:
                Intent dateIntent = new Intent(mActivity, DatePickerActivity.class);
                dateIntent.putExtra("date", bBusinessday);
                startActivityForResult(dateIntent, 102);
                break;
            case R.id.tv_lea_time:
                showTimePickerDialog();
                break;
            case R.id.tv_room_count:
                showRoomCountDialog();
                break;
            case R.id.tv_people_number:
                showPeopleNumberDialog();
                break;
            case R.id.tv_room_type:
                if (mRoomTypeCodeList.size() == 0) {
                    getDataFromServer(ROOM_TYPE);
                } else {
                    showRoomTypeDialog();
                }
                break;
            case R.id.tv_package:
                if (mPackageList.size() == 0) {
                    getDataFromServer(PACKAGE);
                } else {
                    showPackageCodeDialog();
                }
                break;
            case R.id.tv_rate_code:
                if (mRoomRateList.size() == 0) {
                    getDataFromServer(ROOM_RATE);
                } else {
                    showRateCodeDialog();
                }
                break;
            case R.id.btn_submit_order:
                if (TextUtils.isEmpty(etName.getText().toString().trim())) {
                    showToast("请输入姓名");
                    return;
                }
                if (TextUtils.isEmpty(tvRateCode.getText().toString()) || TextUtils.isEmpty(tvPrice.getText().toString())) {
                    showToast("请选择房价码");
                    return;
                }
                submitOrder();
                break;
        }
    }

    private void submitOrder() {

        final Dialog sDialog = QUtil.createLoadingDialog(mActivity, "提交订单");
        sDialog.show();
        RequestParams params = new RequestParams(Api.CREATE_ORDER);
        params.addBodyParameter("IDCode", mUserInfoBean == null ? "" : mUserInfoBean.getIDCode());
        params.addBodyParameter("MainName", etName.getText().toString());
        params.addBodyParameter("Sex", mSex);
        params.addBodyParameter("Country", tvCountry.getText().toString().contains("选择") ? "" : tvCountry.getText().toString());
        params.addBodyParameter("State", tvProvince.getText().toString().contains("选择") ? "" : tvProvince.getText().toString());
        params.addBodyParameter("City", tvCity.getText().toString().contains("选择") ? "" : tvCity.getText().toString());
        params.addBodyParameter("IDCard", etCard.getText().toString());
        params.addBodyParameter("phone", etPhone.getText().toString());
        params.addBodyParameter("userid", bLoginName);
        params.addBodyParameter("hotelid", bHotelId);
        params.addBodyParameter("companyid", bCompanyId);
        params.addBodyParameter("OrderIDCode", mOrderIdCode);
        params.addBodyParameter("BeginDate", mStartDate);
        params.addBodyParameter("EndDate", mEndDate);
        params.addBodyParameter("EndTime", mEndTime);
        params.addBodyParameter("DayCount", mLiveDays);
        params.addBodyParameter("RoomsCount", mRoomCount + "");
        params.addBodyParameter("PersonCount", mPersonCount + "");
        params.addBodyParameter("RoomTypeIDCode", mRoomTypeIDCode);
        params.addBodyParameter("RoomTypeName", mRoomTypeName);
        params.addBodyParameter("RateIDCode", mRateIDCode);
        params.addBodyParameter("RateValues", mRateValues);
        params.addBodyParameter("RateName", mRateName);
        params.addBodyParameter("PackageIDCode", mPackageIDCode);
        params.addBodyParameter("PackageName", mPackageName);
        params.addBodyParameter("info", etInfo.getText().toString());
        params.addBodyParameter("BusinessDay", bBusinessday);

        Log.e("params", "IDCode=" + (mUserInfoBean == null ? "" : mUserInfoBean.getIDCode())
                + "&MainName=" + etName.getText().toString()
                + "&Sex=" + mSex
                + "&Country=" + (tvCountry.getText().toString().contains("选择") ? "" : tvCountry.getText().toString())
                + "&State=" + (tvProvince.getText().toString().contains("选择") ? "" : tvProvince.getText().toString())
                + "&City=" + (tvCity.getText().toString().contains("选择") ? "" : tvCity.getText().toString())
                + "&IDCard=" + etCard.getText().toString()
                + "&phone=" + etPhone.getText().toString()
                + "&userid=" + bLoginName
                + "&hotelid=" + bHotelId
                + "&companyid=" + bCompanyId
                + "&OrderIDCode=" + mOrderIdCode
                + "&BeginDate=" + mStartDate
                + "&EndDate=" + mEndDate
                + "&EndTime=" + mEndTime
                + "&DayCount=" + mLiveDays
                + "&RoomsCount=" + mRoomCount
                + "&PersonCount=" + mPersonCount
                + "&RoomTypeIDCode=" + (mRoomTypeIDCode)
                + "&RoomTypeName=" + (mRoomTypeName)
                + "&RateIDCode=" + (mRateIDCode)
                + "&RateValues=" + (mRateValues)
                + "&RateName=" + (mRateName)
                + "&PackageIDCode=" + (mPackageIDCode)
                + "&PackageName=" + (mPackageName)
                + "&info=" + etInfo.getText().toString()
                + "&BusinessDay=" + bBusinessday
        );

        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("create_order_success", result);
                try {
                    JSONObject object = new JSONObject(result);
                    showToast(object.getString("msg"));
                    if (object.getInt("status") == 2) {
                        if (TextUtils.equals("order", mFrom)) {
                            setResult(RESULT_OK);
                        }
                        finish();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e("create_order_error", ex.getMessage());
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
        if (resultCode == 201 && requestCode == 101) {
            mUserInfoBean = (BookInfoListBean.InfoBean) data.getSerializableExtra("info");
            etName.setText(mUserInfoBean.getMainName());
            // mUserInfoBean.getClientID();
            tvCountry.setText(mUserInfoBean.getCountry());
            tvCountry.setTextColor(ContextCompat.getColor(mActivity, R.color.app_theme_black));
            tvProvince.setText(mUserInfoBean.getState());
            tvProvince.setTextColor(ContextCompat.getColor(mActivity, R.color.app_theme_black));
            tvCity.setText(mUserInfoBean.getCity());
            tvCity.setTextColor(ContextCompat.getColor(mActivity, R.color.app_theme_black));
            etCard.setText(mUserInfoBean.getIDCard());
            etPhone.setText(mUserInfoBean.getContactinformationStr());
        }
        if (resultCode == 300 && requestCode == 102) {
            Bundle extras = data.getExtras();
            mStartDate = extras.getString("dateIn");
            mEndDate = extras.getString("dateOut");
            mLiveDays = extras.getString("days");
            setTheDate();
        }
    }


    private void showDialog(String title, BaseAdapter adapter, final OnItemClickListener listener) {
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_list_with_title, null);
        AlertDialog.Builder builder =
                new AlertDialog.Builder(this)
                        .setView(view);

        final AlertDialog dialog = builder.show();
        TextView tvTitle = (TextView) view.findViewById(R.id.title);
        ListView lvCode = (ListView) view.findViewById(R.id.lv_content);
        tvTitle.setText(title);
        lvCode.setAdapter(adapter);

        lvCode.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                dialog.dismiss();
                listener.OnItemClickListener(position);
            }
        });

        view.findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    //定义一个接口
    public interface OnItemClickListener {
        public void OnItemClickListener(int position);
    }

    private OnItemClickListener mListener;

    //写一个设置接口监听的方法
    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

}
