package cn.houno.hotel.ui.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.houno.hotel.R;
import cn.houno.hotel.TestActivity;
import cn.houno.hotel.adapter.WorkCategoryAdapter;
import cn.houno.hotel.global.Api;
import cn.houno.hotel.ui.activity.SearchAllotRoomActivity;
import cn.houno.hotel.ui.activity.book.BookActivity;
import cn.houno.hotel.ui.activity.GuestRoomActivity;
import cn.houno.hotel.ui.activity.RoomStateListActivity;
import cn.houno.hotel.utils.PrefUtils;
import cn.houno.hotel.utils.QUtil;
import cn.houno.hotel.view.InnerGridView;

/**
 * Created by qzc on 2017/5/8 0008.
 */

public class WorkFragment extends BaseFragment {

    @Bind(R.id.tv_hotel_name)
    TextView tvHotelName;
    @Bind(R.id.tv_name)
    TextView tvName;
    @Bind(R.id.tv_date)
    TextView tvDate;
    @Bind(R.id.tv_room_num)
    TextView tvRoomNum;
    @Bind(R.id.gv_category)
    InnerGridView gvCategory;

    private String[] mCategories = new String[]{"房态图", "预　订", "客　房", "报　表", "入　住"};

    private WorkCategoryAdapter categoryAdapter;

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_work, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected void initFindViewById(View view) {

    }

    @Override
    public void initData() {
        //categoryAdapter = new WorkCategoryAdapter(mActivity, mCategories);
        gvCategory.setAdapter(categoryAdapter);

        tvHotelName.setText(PrefUtils.getString(mActivity, "shortname", ""));
        tvDate.setText(PrefUtils.getString(mActivity, "businessday", ""));
        tvName.setText(PrefUtils.getString(mActivity, "name", ""));

        getUserInfo();
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        gvCategory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        startActivity(new Intent(mActivity, RoomStateListActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(mActivity, BookActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(mActivity, GuestRoomActivity.class));
                        break;

                    case 4:
//                        Intent intent = new Intent(mActivity, DatePickerActivity.class);
//                        intent.putExtra("date", PrefUtils.getString(mActivity, "businessday", ""));
//                        startActivity(intent);

//                        Intent intent = new Intent(mActivity, TestActivity.class);
//                        startActivity(intent);
                        break;
                }
            }
        });
    }

    private void getUserInfo() {
        final Dialog lDialog = QUtil.createLoadingDialog(mActivity, "获取信息");
        lDialog.show();
        RequestParams params = new RequestParams(Api.GET_HOTEL_INFO);
        params.addBodyParameter("hotelid", PrefUtils.getString(mActivity, "hotelidcode", ""));
        params.addBodyParameter("companyid", PrefUtils.getString(mActivity, "companyinfoidcode", ""));
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("GetHotelInfo", result);
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
                if (lDialog.isShowing()) {
                    lDialog.dismiss();
                }
            }
        });
    }

    private void parseData2(String result) {
        try {
            JSONObject object = new JSONObject(result);
            if (object.getInt("status") == 0) {
                JSONObject info = object.getJSONObject("info");
                String shortname = info.getString("shortname");
                String businessday = info.getString("businessday");
                PrefUtils.setString(mActivity, "shortname", shortname);
                PrefUtils.setString(mActivity, "businessday", businessday);

                tvHotelName.setText(PrefUtils.getString(mActivity, "shortname", ""));
                tvDate.setText(PrefUtils.getString(mActivity, "businessday", ""));
                tvName.setText(PrefUtils.getString(mActivity, "name", ""));

            } else {

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
