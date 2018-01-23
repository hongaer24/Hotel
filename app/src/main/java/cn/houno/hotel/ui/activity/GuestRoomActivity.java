package cn.houno.hotel.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.houno.hotel.R;
import cn.houno.hotel.adapter.WorkCategoryAdapter;
import cn.houno.hotel.utils.PrefUtils;
import cn.houno.hotel.view.InnerGridView;

/**
 * 客房
 * Created by qzc on 2017-05-09.
 */

public class GuestRoomActivity extends BaseActivity {

    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.rl_top_bar)
    RelativeLayout rlTopBar;
    @Bind(R.id.gv_category)
    InnerGridView gvCategory;
    @Bind(R.id.tv_hotel_name)
    TextView tvHotelName;
    @Bind(R.id.tv_name)
    TextView tvName;
    @Bind(R.id.tv_date)
    TextView tvDate;
    @Bind(R.id.tv_room_num)
    TextView tvRoomNum;

    private String[] mCategories = new String[]{"客房房态直观图", "个人清洁单据", "单据查询", "房态更改查询"};

    private GuestRoomActivity mActivity;

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_guest_room);
        ButterKnife.bind(this);
    }

    @Override
    public void initData() {
        super.initData();
        mActivity = GuestRoomActivity.this;

        tvHotelName.setText(PrefUtils.getString(mActivity,"shortname",""));
        tvName.setText(PrefUtils.getString(mActivity,"name",""));
        tvDate.setText(PrefUtils.getString(mActivity,"businessday",""));
        gvCategory.setAdapter(new WorkCategoryAdapter(mActivity, mCategories));
    }

    @Override
    public void initEvent() {
        super.initEvent();

        gvCategory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        startActivity(new Intent(mActivity, RoomStateList2Activity.class));
                        break;
                    case 1:
                        startActivity(new Intent(mActivity, PersonalCleanDocActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(mActivity, SearchBillsActivity.class));
                        break;
                    case 3:
                        startActivity(new Intent(mActivity, SearchRoomStateEditActivity.class));
                        break;
                }
            }
        });
    }


    @OnClick(R.id.iv_back)
    public void onClick() {
        finish();
    }

}
