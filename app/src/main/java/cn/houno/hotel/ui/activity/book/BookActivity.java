package cn.houno.hotel.ui.activity.book;

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
import cn.houno.hotel.ui.activity.BaseActivity;
import cn.houno.hotel.ui.activity.SearchRoomNumberActivity;
import cn.houno.hotel.utils.PrefUtils;
import cn.houno.hotel.view.InnerGridView;

/**
 * 预订酒店
 * Created by qzc on 2017-05-15.
 */

public class BookActivity extends BaseActivity {
    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.tv_top_title)
    TextView tvTopTitle;
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

    private String[] mCategories = new String[]{"可用房间量查询", "预订询价", "创建订单", "订单查询"};

    private BookActivity mActivity;

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_guest_room);
        ButterKnife.bind(this);
    }

    @Override
    public void initData() {
        super.initData();
        mActivity = BookActivity.this;
        tvTopTitle.setText("预订");
        tvHotelName.setText(PrefUtils.getString(mActivity, "shortname", ""));
        tvName.setText(PrefUtils.getString(mActivity, "name", ""));
        tvDate.setText(PrefUtils.getString(mActivity, "businessday", ""));
        //gvCategory.setAdapter(new WorkCategoryAdapter(mActivity, mCategories));
    }

    @Override
    public void initEvent() {
        super.initEvent();

        gvCategory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        startActivity(new Intent(mActivity, SearchRoomNumberActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(mActivity, BookQueryActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(mActivity, NewOrderActivity.class));
                        break;
                    case 3:
                        startActivity(new Intent(mActivity, OrderListActivity.class));
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
