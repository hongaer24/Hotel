package cn.houno.hotel.home.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
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
import cn.houno.hotel.adapter.CompanyAndTravelAdapter;
import cn.houno.hotel.adapter.RoomPriceCodeAdapter;
import cn.houno.hotel.adapter.RoomTypeCodeAdapter;
import cn.houno.hotel.bean.BookInfoListBean;
import cn.houno.hotel.bean.RoomPriceCodeListBean;
import cn.houno.hotel.bean.RoomTypeCodeBean;
import cn.houno.hotel.global.Api;
import cn.houno.hotel.home.activity.NewOrderActivity;
import cn.houno.hotel.home.bean.OrderParamsBean;
import cn.houno.hotel.ui.activity.BaseActivity;
import cn.houno.hotel.ui.activity.DatePickerActivity;
import cn.houno.hotel.ui.activity.book.adapter.GroupListAdapter;

import cn.houno.hotel.utils.DateUtil;
import cn.houno.hotel.utils.QUtil;

/**
 * 订单查询
 * Created by qzc on 2017-06-09.
 */

public class QueryOrderActivity extends BaseActivity {


    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.rl_top_bar)
    RelativeLayout rlTopBar;
    @Bind(R.id.et_name)
    EditText etName;
    @Bind(R.id.et_phone)
    EditText etPhone;
    @Bind(R.id.et_order_no)
    EditText etOrderNo;
    @Bind(R.id.et_link_name)
    EditText etLinkName;
    @Bind(R.id.tv_start_date)
    TextView tvStartDate;
    @Bind(R.id.tv_end_date)
    TextView tvEndDate;
    @Bind(R.id.tv_type_code)
    TextView tvTypeCode;
    @Bind(R.id.tv_package_code)
    TextView tvPackageCode;
    @Bind(R.id.tv_price_code)
    TextView tvPriceCode;
    @Bind(R.id.tv_company_code)
    TextView tvCompanyCode;
    @Bind(R.id.tv_group_code)
    TextView tvGroupCode;
    @Bind(R.id.tv_travel_code)
    TextView tvTravelCode;


    private QueryOrderActivity mActivity;

    private String mStartDate;

    private String mEndDate;


    private static final int ROOM_TYPE = 13;
    private static final int PACKAGE = 14;
    private static final int ROOM_RATE = 15;

    private static final int COMPANY = 16;
    private static final int GROUP = 17;
    private static final int TRAVEL = 18;


    private List<BookInfoListBean.InfoBean> mCompanyList = new ArrayList<>();
    private int mCompanySelect = -1;

    private List<BookInfoListBean.InfoBean> mGroupList = new ArrayList<>();
    private int mGroupSelect = -1;

    private List<BookInfoListBean.InfoBean> mTravelList = new ArrayList<>();
    private int mTravelSelect = -1;

    private List<RoomTypeCodeBean> mRoomTypeCodeList = new ArrayList<>();
    private int mRoomTypeSelect = -1;

    private List<RoomPriceCodeListBean.InfoBean> mPackageList = new ArrayList<>();
    private int mPageageSelect = -1;

    private List<RoomPriceCodeListBean.InfoBean> mRoomRateList = new ArrayList<>();
    private int mRoomRateSelect = -1;


    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_query_order);
        ButterKnife.bind(this);
    }

    @Override
    public void initData() {
        super.initData();
        mActivity = QueryOrderActivity.this;

//        mStartDate = bBusinessday;
//        mEndDate = DateUtil.getTheDate(mStartDate, 1);
//        setDateText();
    }


    private void setDateText() {
        tvStartDate.setText(mStartDate);
        tvEndDate.setText(mEndDate);
    }

    @Override
    public void initEvent() {
        super.initEvent();


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
                    if (type == ROOM_TYPE) {
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

                        RoomPriceCodeListBean roomPriceCodeListBean = gson.fromJson(result, RoomPriceCodeListBean.class);
                        mRoomRateList.add(new RoomPriceCodeListBean.InfoBean("", "所有"));
                        mRoomRateList.addAll(roomPriceCodeListBean.getInfo());
                        showRateCodeDialog();
                    } else if (type == COMPANY) {
                        BookInfoListBean infoListBean = gson.fromJson(result, BookInfoListBean.class);
                        mCompanyList.add(new BookInfoListBean.InfoBean("", "所有"));
                        mCompanyList.addAll(infoListBean.getInfo());
                        showCompanyDialog();
                    } else if (type == TRAVEL) {
                        BookInfoListBean infoListBean = gson.fromJson(result, BookInfoListBean.class);
                        mTravelList.add(new BookInfoListBean.InfoBean("", "所有"));
                        mTravelList.addAll(infoListBean.getInfo());
                        showTravelDialog();
                    } else if (type == GROUP) {
                        BookInfoListBean infoListBean = gson.fromJson(result, BookInfoListBean.class);
                        BookInfoListBean.InfoBean all = new BookInfoListBean.InfoBean("", "所有");
                        all.setCountry("");
                        all.setState("");
                        all.setCity("");
                        mGroupList.add(all);
                        mGroupList.addAll(infoListBean.getInfo());
                        showGroupDialog();
                    } else {

                    }
                }else {
                    showToast(object.getString("msg"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }


    private RequestParams getRequestParams(int type) {

        RequestParams params = null;
        switch (type) {
            case ROOM_TYPE:
                params = new RequestParams(Api.GET_ROOM_TYPE_CODE);
                break;
            case PACKAGE:
                params = new RequestParams(Api.GET_ROOM_PACKAGE_CODE);
                break;
            case ROOM_RATE:
                params = new RequestParams(Api.GET_ROOM_PRICE_CODE);
                break;
            case COMPANY:
                params = new RequestParams(Api.GET_COMPANY_OR_TRAVEL_CODE);
                params.addBodyParameter("stype", "company");
                break;
            case TRAVEL:
                params = new RequestParams(Api.GET_COMPANY_OR_TRAVEL_CODE);
                params.addBodyParameter("stype", "TravelAgent");
                break;
            case GROUP:
                params = new RequestParams(Api.GET_GROUP_OR_INDIVIDUAL_CODE);
                params.addBodyParameter("stype", "Group");
                break;

        }
        if (params != null) {
            params.addBodyParameter("hotelid", bHotelId);
            params.addBodyParameter("companyid", bCompanyId);
        }
        return params;
    }


    private void showRoomTypeDialog() {

        showDialog("房型", new RoomTypeCodeAdapter(mActivity, mRoomTypeCodeList), new OnItemClickListener() {
            @Override
            public void OnItemClickListener(int position) {
                tvTypeCode.setText(mRoomTypeCodeList.get(position).getName());
                mRoomTypeSelect = position;
            }
        });
    }

    private void showPackageCodeDialog() {
        showDialog("套餐", new RoomPriceCodeAdapter(mActivity, mPackageList), new OnItemClickListener() {
            @Override
            public void OnItemClickListener(int position) {
                tvPackageCode.setText(mPackageList.get(position).getName());
                mPageageSelect = position;
            }
        });
    }

    private void showRateCodeDialog() {
        showDialog("房价", new RoomPriceCodeAdapter(mActivity, mRoomRateList), new OnItemClickListener() {
            @Override
            public void OnItemClickListener(int position) {
                tvPriceCode.setText(mRoomRateList.get(position).getName());
                mRoomRateSelect = position;
            }
        });
    }

    private void showCompanyDialog() {
        showDialog("公司", new CompanyAndTravelAdapter(mActivity, mCompanyList), new OnItemClickListener() {
            @Override
            public void OnItemClickListener(int position) {
                tvCompanyCode.setText(mCompanyList.get(position).getMainName());
                mCompanySelect = position;
            }
        });
    }

    private void showTravelDialog() {
        showDialog("旅行社", new CompanyAndTravelAdapter(mActivity, mTravelList), new OnItemClickListener() {
            @Override
            public void OnItemClickListener(int position) {
                tvTravelCode.setText(mTravelList.get(position).getMainName());
                mTravelSelect = position;
            }
        });
    }

    private void showGroupDialog() {
        showDialog("团队", new GroupListAdapter(mActivity, mGroupList), new OnItemClickListener() {
            @Override
            public void OnItemClickListener(int position) {
                tvGroupCode.setText(mGroupList.get(position).getMainName());
                mGroupSelect = position;
            }
        });
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


    @OnClick({R.id.iv_back, R.id.tv_start_date, R.id.tv_end_date, R.id.tv_type_code, R.id.tv_package_code
            , R.id.tv_price_code, R.id.tv_company_code, R.id.tv_group_code, R.id.tv_travel_code,R.id.btn_query})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_start_date:

            case R.id.tv_end_date:
                Intent intent = new Intent(mActivity, DatePickerActivity.class);
                intent.putExtra("date", DateUtil.getTheDate(bBusinessday, -30));
                startActivityForResult(intent, 101);
                break;
            case R.id.tv_type_code:
                if (mRoomTypeCodeList.size() == 0) {
                    getDataFromServer(ROOM_TYPE);
                } else {
                    showRoomTypeDialog();
                }
                break;
            case R.id.tv_package_code:
                if (mPackageList.size() == 0) {
                    getDataFromServer(PACKAGE);
                } else {
                    showPackageCodeDialog();
                }
                break;
            case R.id.tv_price_code:
                if (mRoomRateList.size() == 0) {
                    getDataFromServer(ROOM_RATE);
                } else {
                    showRateCodeDialog();
                }
                break;
            case R.id.tv_company_code:
                if (mCompanyList.size() == 0) {
                    getDataFromServer(COMPANY);
                } else {
                    showCompanyDialog();
                }
                break;
            case R.id.tv_group_code:
                if (mGroupList.size() == 0) {
                    getDataFromServer(GROUP);
                } else {
                    showGroupDialog();
                }
                break;
            case R.id.tv_travel_code:
                if (mTravelList.size() == 0) {
                    getDataFromServer(TRAVEL);
                } else {
                    showTravelDialog();
                }
                break;

            case R.id.btn_query:
                setParamsAndReturn();
                break;
        }
    }

    private void setParamsAndReturn() {
        OrderParamsBean params = new OrderParamsBean();
        params.setMainname(etName.getText().toString().trim());
        params.setPhone(etPhone.getText().toString().trim());
        params.setOrderid(etOrderNo.getText().toString());
        params.setLinkname(etLinkName.getText().toString());
        params.setBegindate(tvStartDate.getText().toString());
        params.setEnddate(tvEndDate.getText().toString());
        if (mRoomTypeCodeList.size()==0){
            params.setRoomtypeidcode("");
        }else {
            params.setRoomtypeidcode(mRoomTypeCodeList.get(mRoomTypeSelect).getCode());
        }

        if (mPackageList.size()==0){
            params.setPackageidcode("");
        }else {
            params.setPackageidcode(mPackageList.get(mPageageSelect).getIDCode());
        }

        if (mRoomRateList.size()==0){
            params.setRateidcode("");
        }else {
            params.setRateidcode(mRoomRateList.get(mRoomRateSelect).getIDCode());
        }

        if (mCompanyList.size()==0){
            params.setCompanyidcode("");
        }else {
            params.setCompanyidcode(mCompanyList.get(mCompanySelect).getIDCode());
        }

        if (mGroupList.size()==0){
            params.setGroupidcode("");
        }else {
            params.setGroupidcode(mGroupList.get(mGroupSelect).getIDCode());
        }

        if (mTravelList.size()==0){
            params.setTravelidcode("");
        }else {
            params.setTravelidcode(mTravelList.get(mTravelSelect).getIDCode());
        }

        Intent intent = new Intent();
        intent.putExtra("data",params);
        setResult(111,intent);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101 & resultCode == 300) {
            Bundle extras = data.getExtras();
            mStartDate = extras.getString("dateIn");
            mEndDate = extras.getString("dateOut");
            setDateText();
        }
    }


    //定义一个接口
    public interface OnItemClickListener {
        public void OnItemClickListener(int position);
    }

    private NewOrderActivity.OnItemClickListener mListener;

    //写一个设置接口监听的方法
    public void setOnItemClickListener(NewOrderActivity.OnItemClickListener listener) {
        mListener = listener;
    }

}
