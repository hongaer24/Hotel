package cn.houno.hotel.home.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.houno.hotel.R;
import cn.houno.hotel.adapter.RommTypeAdapter;
import cn.houno.hotel.adapter.WorkCategoryAdapter;
import cn.houno.hotel.utils.StatusBarUtils;

public class OpreationContitionActivity extends AppCompatActivity {

    @Bind(R.id.rl_top_bar)
    RelativeLayout rlTopBar;
    @Bind(R.id.tv_opentime)
    TextView tvOpentime;
    @Bind(R.id.tv_time)
    TextView tvTime;
    @Bind(R.id.tv_today)
    TextView tvToday;
    @Bind(R.id.tv_month)
    TextView tvMonth;
    @Bind(R.id.tv_year)
    TextView tvYear;
    @Bind(R.id.gv_room_status)
    GridView gvRoomStatus;
    @Bind(R.id.gv_room_type)
    GridView gvRoomType;

    private String[] categorTitle = {"总房数", "在住房", "预离房", "停售房", "可售房", "预定房", "出租率", "平均房价"};
    private String[] roomType = {"行政大床房", "雅致大床房", "行政套房", "高级大床房", "高级标准房", "高级大床房","行政大床房", "雅致大床房", "行政套房",};

    private String[] categoryName = {"201", "200", "100", "201", "201", "201", "20%", "419"};
    private String[] roomTypeName = {"201", "200", "100", "201", "201", "201", "201", "201", "201"};
    private OpreationContitionActivity mActivity;
    private WorkCategoryAdapter categoryAdapter;
    private RommTypeAdapter rommTypeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opreation_contition);
        ButterKnife.bind(this);
        mActivity=OpreationContitionActivity.this;
        StatusBarUtils.setWindowStatusBarColor(mActivity, R.color.app_theme_light_black);
        initData();
    }

    private void initData() {

        categoryAdapter = new WorkCategoryAdapter(mActivity, categorTitle, categoryName);
        gvRoomStatus.setAdapter(categoryAdapter);

        rommTypeAdapter = new RommTypeAdapter(mActivity, roomType, roomTypeName);
        gvRoomType.setAdapter(rommTypeAdapter);


    }
}
