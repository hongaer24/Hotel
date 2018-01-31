package cn.houno.hotel.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.shizhefei.view.indicator.IndicatorViewPager;
import com.shizhefei.view.indicator.ScrollIndicatorView;
import com.shizhefei.view.indicator.slidebar.ColorBar;
import com.shizhefei.view.indicator.transition.OnTransitionTextListener;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.houno.hotel.R;
import cn.houno.hotel.adapter.RoomHSAdapter;
import cn.houno.hotel.ui.fragment.room.HotelBulkPage;
import cn.houno.hotel.ui.fragment.room.ScenicTicketPage;

/**
 * Created by Administrator on 2017/5/8 0008.
 */

public class RoomFragment extends BaseFragment {


    TwinklingRefreshLayout rfvHome;
    @Bind(R.id.siv_home_hs)
    ScrollIndicatorView sivHomeHs;
    @Bind(R.id.vp_home_hs)
    ViewPager vpHomeHs;
    @Bind(R.id.ll_main_content)
    LinearLayout llMainContent;
   /* @Bind(R.id.xsv_main)
    ScrollView xsvMain;*/

    private ArrayList<Fragment> hsFragments;
    private HotelBulkPage hotelBulkPage;
    private ScenicTicketPage scenicTicketPage;
    private ScrollIndicatorView sivHs;
    private IndicatorViewPager ivpHs;
    private RoomHSAdapter mHsAdapter;

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
        hsFragments = new ArrayList<>();
        hotelBulkPage = new HotelBulkPage();
        scenicTicketPage = new ScenicTicketPage();
        hsFragments.add(hotelBulkPage);
        hsFragments.add(scenicTicketPage);

        float unSelectSize = 18;
        //float selectSize = unSelectSize * 1.2f;
        float selectSize = unSelectSize;
        int selectColor = getResources().getColor(R.color.white);
        int unSelectColor = getResources().getColor(R.color.white);
        sivHomeHs.setOnTransitionListener(new OnTransitionTextListener()
                .setColor(selectColor, unSelectColor).setSize(selectSize, unSelectSize));
        //设置下滑条的颜色和高度
        sivHomeHs.setScrollBar(new ColorBar(mActivity, getResources().getColor(R.color.white), 5));

        //sivHomeHs.setScrollBar(new ColorBar(mActivity,  0xFFFFFFF, 10));
        //        vpHs.setOffscreenPageLimit(2);
        ivpHs = new IndicatorViewPager(sivHomeHs, vpHomeHs);
        mHsAdapter = new RoomHSAdapter(mActivity, getChildFragmentManager(), hsFragments);
        ivpHs.setAdapter(mHsAdapter);

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }
}
