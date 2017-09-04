package com.bwie.wangbingyang20170904;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * handler消息标识
     */
    protected static final int WHAT_INCREASE = 1;

    /**
     * 圆形带数字的进度条
     */
    private CircleNumberProgress cnp_citcleNumberProgress;

    /**
     * 指定给进度条的进程
     */
    private int progress;
    private CircleNumberProgress mCnpCitcleNumberProgress;
    private Button mBtnNumberProgressBar;
    private Button mJiasu;
    private Button mJiansu;
    int nub = 1;
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            progress++;
            cnp_citcleNumberProgress.setProgress(progress);
            /*设置加速减速的效果*/
            if (nub == 1) {//nub==1 匀速
                handler.sendEmptyMessageDelayed(WHAT_INCREASE, getRadomNumber(50, 300));
            }
            if (nub == 2) {//nub==2 减速
                handler.sendEmptyMessageDelayed(WHAT_INCREASE, getRadomNumber(50, 1500));
            }
            if (nub == 3) {//nub==3 加速
                handler.sendEmptyMessageDelayed(WHAT_INCREASE, getRadomNumber(50, 30));
            }
            if (progress >= 100) {
                handler.removeMessages(WHAT_INCREASE);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        cnp_citcleNumberProgress = (CircleNumberProgress) findViewById(R.id.cnp_citcleNumberProgress);
        Button btn_numberProgressBar = (Button) findViewById(R.id.btn_numberProgressBar);
    }

    private void increase() {
        progress = 0;
        handler.removeMessages(WHAT_INCREASE);
        handler.sendEmptyMessage(WHAT_INCREASE);
    }

    /**
     * 得到两个整数之间的一个随机数
     *
     * @param start 较小的数
     * @param end   较大的数
     * @return 随机整数
     */
    public int getRadomNumber(int start, int end) {
        return (int) (start + Math.random() * (end - start));
    }

    private void initView() {
        mCnpCitcleNumberProgress = (CircleNumberProgress) findViewById(R.id.cnp_citcleNumberProgress);
        mBtnNumberProgressBar = (Button) findViewById(R.id.btn_numberProgressBar);
        mJiasu = (Button) findViewById(R.id.jiasu);
        mJiansu = (Button) findViewById(R.id.jiansu);
        mBtnNumberProgressBar.setOnClickListener(this);
        mJiasu.setOnClickListener(this);
        mJiansu.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_numberProgressBar:
                increase();
                nub = 1;
                break;
            case R.id.jiasu:
                nub = 3;
                break;
            case R.id.jiansu:
                nub = 2;
                break;
        }
    }
}
