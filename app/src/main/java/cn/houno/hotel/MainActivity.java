package cn.houno.hotel;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.houno.hotel.ui.fragment.HomeFragment;
import cn.houno.hotel.ui.fragment.MessageFragment;
import cn.houno.hotel.ui.fragment.MyFragment;
import cn.houno.hotel.ui.fragment.RoomFragment;
import cn.houno.hotel.ui.fragment.WorkFragment;
import cn.houno.hotel.utils.QStatusBarUtil;

public class MainActivity extends AppCompatActivity {


    WorkFragment workFragment;
    RoomFragment roomFragment;
    MyFragment myFragment;
    MessageFragment messageFragment;
    @Bind(R.id.fl_content)
    FrameLayout flContent;
    @Bind(R.id.rb_home)
    RadioButton rbHome;
    @Bind(R.id.rb_message)
    RadioButton rbMessage;
    @Bind(R.id.rb_my)
    RadioButton rbMy;
    @Bind(R.id.rg_tab_bar)
    RadioGroup rgTabBar;
    private MainActivity mActivity;
    private HomeFragment homeFragment;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mActivity = MainActivity.this;
        // StatusBarUtils.setWindowStatusBarColor(mActivity, R.color.app_theme_green);
        initData();
        initEvent();
    }


    public void initData() {

        QStatusBarUtil.setWindowStatusBarColor(MainActivity.this, R.color.app_theme_light_black);

        select(0);
    }


    public void initEvent() {

        rgTabBar.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rb_home:
                        select(0);
                        break;
                    case R.id.rb_message:
                        select(1);
                        break;
                   /* case R.id.rb_room:
                        select(2);
                        break;*/
                    case R.id.rb_my:
                        select(2);
                        break;
                }
            }
        });
    }

    private void select(int i) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        hideFragment(ft);

        switch (i) {
            case 0:
                if (homeFragment == null) {
                    homeFragment = new HomeFragment();
                    ft.add(R.id.fl_content, homeFragment);
                } else {
                    ft.show(homeFragment);
                }
                break;
            case 1:
                if (messageFragment == null) {
                    messageFragment = new MessageFragment();
                    ft.add(R.id.fl_content, messageFragment);
                } else {
                    ft.show(messageFragment);
                }
                break;
         /*   case 2:
                if (roomFragment == null) {
                    roomFragment = new RoomFragment();
                    ft.add(R.id.fl_content, roomFragment);
                } else {
                    ft.show(roomFragment);
                }
                break;*/
            case 2:
                if (myFragment == null) {
                    myFragment = new MyFragment();
                    ft.add(R.id.fl_content, myFragment);
                } else {
                    ft.show(myFragment);
                }
                break;
        }
        ft.commit();
    }

    //隐藏所有Fragment
    private void hideFragment(FragmentTransaction fragmentTransaction) {
        if (homeFragment != null) {
            fragmentTransaction.hide(homeFragment);
        }
        if (messageFragment != null) {
            fragmentTransaction.hide(messageFragment);
        }
       /* if (roomFragment != null) {
            fragmentTransaction.hide(roomFragment);
        }*/
        if (myFragment != null) {
            fragmentTransaction.hide(myFragment);
        }

    }

}
