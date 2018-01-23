package cn.houno.hotel;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.houno.hotel.ui.fragment.MessageFragment;
import cn.houno.hotel.ui.fragment.MyFragment;
import cn.houno.hotel.ui.fragment.RoomFragment;
import cn.houno.hotel.ui.fragment.WorkFragment;
import cn.houno.hotel.ui.activity.BaseActivity;
import cn.houno.hotel.utils.QStatusBarUtil;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.fl_content)
    FrameLayout flContent;
    @Bind(R.id.rb_work)
    RadioButton rbWork;
    @Bind(R.id.rb_room)
    RadioButton rbRoom;
    @Bind(R.id.rb_my)
    RadioButton rbMy;
    @Bind(R.id.rg_tab_bar)
    RadioGroup rgTabBar;


    WorkFragment workFragment;
    RoomFragment roomFragment;
    MyFragment myFragment;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initData();
        initEvent();
    }


    public void initData() {

        QStatusBarUtil.setWindowStatusBarColor(MainActivity.this, R.color.app_theme_org_press);

        select(0);
    }


    public void initEvent() {

        rgTabBar.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rb_work:
                        select(0);
                        break;
                    case R.id.rb_room:
                        select(1);
                        break;
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
                if (workFragment == null) {
                    workFragment = new WorkFragment();
                    ft.add(R.id.fl_content, workFragment);
                } else {
                    ft.show(workFragment);
                }
                break;
            case 1:
                if (roomFragment == null) {
                    roomFragment = new RoomFragment();
                    ft.add(R.id.fl_content, roomFragment);
                } else {
                    ft.show(roomFragment);
                }
                break;
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
        if (workFragment != null) {
            fragmentTransaction.hide(workFragment);
        }
        if (roomFragment != null) {
            fragmentTransaction.hide(roomFragment);
        }
        if (myFragment != null) {
            fragmentTransaction.hide(myFragment);
        }
    }

}
