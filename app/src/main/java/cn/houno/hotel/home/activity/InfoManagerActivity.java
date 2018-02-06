package cn.houno.hotel.home.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.houno.hotel.R;
import cn.houno.hotel.global.Api;
import cn.houno.hotel.home.adapter.BookQueryEXpAdapter;
import cn.houno.hotel.home.adapter.HomeAdapter;
import cn.houno.hotel.home.bean.BookQueryListBean;
import cn.houno.hotel.ui.activity.BaseActivity;
import cn.houno.hotel.utils.DateUtil;
import cn.houno.hotel.utils.QUtil;
import cn.houno.hotel.utils.StatusBarUtils;

public class InfoManagerActivity extends BaseActivity {

    @Bind(R.id.rl_top_bar)
    RelativeLayout rlTopBar;
    @Bind(R.id.et_scenic_search)
    EditText etScenicSearch;
    @Bind(R.id.gv_room_status)
    GridView gvRoomStatus;
    @Bind(R.id.tv_today)
    TextView tvToday;
    @Bind(R.id.lv_book_query)
    ExpandableListView lvBookQuery;

    private String mStartDate = "";
    private String mEndDate = "";
    private String mRoomPriceCode = "";
    private String mRoomTypeCode = "";
    private int mRoomCount = 1;
    private String mPackageCode = "";
    private String mCompanyCode = "";
    private String mTravelCode = "";

    private String[] text = {"创建订单", "预定询价", "订单查询"};
    private int[] image = {R.drawable.ic_home_waiter, R.drawable.ic_home_info, R.drawable.ic_home_repair};
    private InfoManagerActivity mActivity;
    private HomeAdapter homeAdapter;
    private List<BookQueryListBean.InfoBean> mList=new ArrayList<>();
    private ArrayList<String> arrayList;
    private Set<String> keyset;
    private HashMap<String, List<BookQueryListBean.InfoBean>> children=new HashMap<>();
    private BookQueryEXpAdapter mAdapter;

/*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_manager);
        ButterKnife.bind(this);
        mActivity=InfoManagerActivity.this;
        StatusBarUtils.setWindowStatusBarColor(mActivity, R.color.app_theme_light_black);
        initData();
        initEvent();
    }*/

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_info_manager);
        ButterKnife.bind(this);
    }
    @Override
    public void initData() {
        super.initData();
        mActivity = InfoManagerActivity.this;
        mStartDate = bBusinessday;
        mEndDate = DateUtil.getTheDate(bBusinessday, 1);
        StatusBarUtils.setWindowStatusBarColor(mActivity, R.color.app_theme_light_black);
        homeAdapter = new HomeAdapter(mActivity, text, image);
        gvRoomStatus.setAdapter(homeAdapter);
        getDataFromServer();

    }
    @Override
    public void initEvent() {
        gvRoomStatus.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                switch (position) {
                    case 0: //创建订单
                       /* intent.putExtra("cityId", mCityId);
                        intent.putExtra("cityName", mCityName);*/
                        intent.setClass(mActivity, NewOrderActivity.class);
                        startActivity(intent);
                        break;
                    case 1://预定询价
                        intent.setClass(mActivity, InfoManagerActivity.class);
                        startActivity(intent);
                        break;
                    case 2: //订单查询
                        intent.setClass(mActivity,RoomStateListActivity.class);
                        startActivity(intent);
                        break;

                }
            }
        });
        lvBookQuery.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return false;
            }
        });
        lvBookQuery.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                return true;
            }
        });


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == 200 && requestCode == 100) {

            mStartDate = data.getStringExtra("start_date");
            mEndDate = data.getStringExtra("end_date");
            mRoomCount = data.getIntExtra("count", 1);
            mRoomPriceCode = data.getStringExtra("price");
            mRoomTypeCode = data.getStringExtra("type");
            mPackageCode = data.getStringExtra("package");
            mCompanyCode = data.getStringExtra("company");
            mTravelCode = data.getStringExtra("travel");

            getDataFromServer();
        }

    }

    private void getDataFromServer() {
        final Dialog sDialog = QUtil.createLoadingDialog(mActivity, "正在查询");
        sDialog.show();
        RequestParams params = new RequestParams(Api.BOOK_QUERY);
        params.addBodyParameter("hotelid", bHotelId);
        params.addBodyParameter("companyid", bCompanyId);
        params.addBodyParameter("startdate", mStartDate);
        params.addBodyParameter("enddate", mEndDate);
        params.addBodyParameter("roomratecode", mRoomPriceCode);
        params.addBodyParameter("roomtypecode", mRoomTypeCode);
        params.addBodyParameter("roomcount", mRoomCount + "");
        params.addBodyParameter("packagecode", mPackageCode);
        params.addBodyParameter("travelcode", mTravelCode);
        params.addBodyParameter("companycode", mCompanyCode);
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
                if (sDialog.isShowing()) {
                    sDialog.dismiss();
                }
            }
        });
    }

    private void parseData(String result) {
        try {
            JSONObject object = new JSONObject(result);
            mList.clear();
            if (object.getInt("status") == 2) {
              BookQueryListBean bookQueryListBean = new Gson().fromJson(result,BookQueryListBean.class);
                mList = bookQueryListBean.getInfo();
                List<Map> list = new ArrayList<>();

                for (int i = 0; i < mList.size(); i++) {
                    Map map = new HashMap();
                    BookQueryListBean.InfoBean infoBean = mList.get(i);
                    map.put(infoBean.getRoomratemainidcode(), infoBean);
                    list.add(map);
                }
                Map<String, List<BookQueryListBean.InfoBean>> m = mapCombine(list);
                // Log.i("888", "请求数据成功=======" + newList);
                arrayList = new ArrayList<>();
                keyset = m.keySet();
                for (String key : keyset) {
                    children.put(key, m.get(key));
                    arrayList.add(key);
                }
                if(mAdapter==null){
                    mAdapter = new BookQueryEXpAdapter(mActivity, arrayList,children);
                    lvBookQuery.setAdapter(mAdapter);
                    //mAdapter.setData(arrayList,children);
                    //mExpandableListView.setSelection(0);
                }
                else {
                    mAdapter.setData(arrayList,children);
                }

            } else {
                showToast(object.getString("msg"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public Map mapCombine(List<Map> list) {
        Map<Object, List> map = new HashMap<>();
        for (Map m : list) {
            Iterator it = m.keySet().iterator();
            while (it.hasNext()) {
                Object key = it.next();
                if (!map.containsKey(key)) {
                    List newList = new ArrayList<>();
                    newList.add(m.get(key));
                    map.put(key, newList);
                } else {
                    map.get(key).add(m.get(key));
                }
            }

        }
        return map;

    }
}
