package cn.houno.hotel.ui.fragment;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.houno.hotel.R;

/**
 * Created by Administrator on 2017/5/8 0008.
 */

public class RoomFragment extends BaseFragment {


    @Bind(R.id.siv_home_hs)
    com.shizhefei.view.indicator.ScrollIndicatorView sivHomeHs;
    @Bind(R.id.vp_home_hs)
    ViewPager vpHomeHs;
    @Bind(R.id.ll_main_content)
    LinearLayout llMainContent;
    @Bind(R.id.xsv_main)
    com.andview.refreshview.XScrollView xsvMain;
    @Bind(R.id.rfv_home)
    com.andview.refreshview.XRefreshView rfvHome;

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_room, container, false);
        return view;
    }

    @Override
    protected void initFindViewById(View view) {

    }

    @Override
    public void initData() {

    }

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
