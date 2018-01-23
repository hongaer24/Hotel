package cn.houno.hotel.application;

import android.app.Application;

import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.footer.LoadingView;
import com.lcodecore.tkrefreshlayout.header.SinaRefreshView;

import org.xutils.x;

/**
 * Created by qzc on 2017/5/8 0008.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        TwinklingRefreshLayout.setDefaultHeader(SinaRefreshView.class.getName());
        TwinklingRefreshLayout.setDefaultFooter(LoadingView.class.getName());
    }
}
