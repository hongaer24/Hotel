package cn.houno.hotel.home.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.houno.hotel.R;
import cn.houno.hotel.home.activity.InfoManagerActivity;
import cn.houno.hotel.home.activity.OpreationContitionActivity;
import cn.houno.hotel.home.activity.RoomStateListActivity;
import cn.houno.hotel.home.adapter.HomeAdapter;
import cn.houno.hotel.ui.fragment.BaseFragment;


/**
 * Created by Administrator on 2017/5/8 0008.
 */

public class HomeFragment extends BaseFragment {


    @Bind(R.id.rl_top_bar)
    RelativeLayout rlTopBar;
    @Bind(R.id.civ_headview)
    ImageView civHeadview;
    @Bind(R.id.tv_my_nick)
    TextView tvMyNick;
    @Bind(R.id.rl_my_haed)
    RelativeLayout rlMyHaed;
    @Bind(R.id.gv_room_status)
    GridView gvRoomStatus;
    private String[] text = {"客房服务", "预定管理", "房态", "入账管理", "运营概况", "运营报表"};
    private int[] image = {R.drawable.ic_home_waiter, R.drawable.ic_home_info, R.drawable.ic_home_repair, R.drawable.ic_home_count, R.drawable.ic_home_total, R.drawable.ic_home_table};
    private List<Map<String, Object>> categoryList = new ArrayList<>();
    private SimpleAdapter simpleAdapter;
    private HomeAdapter homeAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        return view;
    }

    @Override
    protected void initFindViewById(View view) {

    }

    @Override
    public void initData() {
       /* categoryList = getCategoryData();
        String[] from = {"image", "text"};
        int[] to = {R.id.tv_grid_title, R.id.tv_grid_txt};
        simpleAdapter = new SimpleAdapter(mActivity, categoryList, R.layout.griditem_category, from, to);*/
        homeAdapter = new HomeAdapter(mActivity, text, image);
        gvRoomStatus.setAdapter(homeAdapter);

    }

    @Override
    protected void initEvent() {
        gvRoomStatus.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                switch (position) {
                    case 0: //客房服务
                       /* intent.putExtra("cityId", mCityId);
                        intent.putExtra("cityName", mCityName);*/
                       // intent.setClass(mActivity, SearchHotelActivity.class);
                        startActivity(intent);
                        break;
                    case 1://预定管理
                        intent.setClass(mActivity, InfoManagerActivity.class);
                        startActivity(intent);
                        break;
                    case 2: //房态
                        intent.setClass(mActivity,RoomStateListActivity.class);
                        startActivity(intent);
                        break;
                    case 3: //入账管理
                        //intent.setClass(mActivity, YdIndexActivity.class);
                        startActivity(intent);
                        break;
                    case 4: //运营概况
                        intent.setClass(mActivity, OpreationContitionActivity.class);
                        startActivity(intent);
                        break;
                    case 5: //运营报表
                        //intent.setClass(mActivity, TrainInquiryActivity.class);
                        startActivity(intent);
                        break;
                    }
                }
            });


    }

    /*  private List<Map<String, Object>> getCategoryData() {
        //icon和iconName的长度是相同的，这里任选其一都可以
        for (int i = 0; i < image.length; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("image", image[i]);
            map.put("text", text[i]);
            categoryList.add(map);
        }

        return categoryList;
    }*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
