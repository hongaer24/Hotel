package cn.houno.hotel;

import android.os.Bundle;
import android.util.Log;
import android.widget.SeekBar;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.houno.hotel.ui.activity.BaseActivity;

/**
 * 测试类
 * Created by qzc on 2017-06-19.
 */

public class TestActivity extends BaseActivity {


    @Bind(R.id.sb)
    SeekBar mSb;
    @Bind(R.id.tv_progress)
    TextView tvProgress;

    private TestActivity mActivity;

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);
    }

    @Override
    public void initData() {
        super.initData();
        mActivity = TestActivity.this;
    }

    @Override
    public void initEvent() {
        super.initEvent();

        mSb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            /*
            * SeekBar滚动时的回调函数
            * */
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvProgress.setText("进度：" + progress + "/100");

            }


            /*
            * SeekBar开始滚动的回调函数
            * */
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Log.e("start：",seekBar.getProgress()+"");
            }

            /*
            * SeekBar停止滚动的回调函数
            * */
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.e("end：",seekBar.getProgress()+"");
            }
        });
    }
}
