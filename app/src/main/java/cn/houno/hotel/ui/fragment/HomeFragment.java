package cn.houno.hotel.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.houno.hotel.R;
import cn.houno.hotel.adapter.HomeAdapter;

/**
 * Created by Administrator on 2017/5/8 0008.
 */

public class HomeFragment extends BaseFragment {

    @Bind(R.id.rl_top_bar)
    RelativeLayout rlTopBar;
    @Bind(R.id.iv_my_toggle)
    ImageView ivMyToggle;
    @Bind(R.id.gv_room_status)
    GridView gvRoomStatus;
    private String[]  text = {"客房服务", "预定管理", "房间维修", "入账管理", "运营概况", "运营报表"};
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
        homeAdapter= new  HomeAdapter(mActivity,text,image);
        gvRoomStatus.setAdapter(homeAdapter);

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
