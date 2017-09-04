package com.bwie.wangbingyang20170904;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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
    private int nub = 1;
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            progress++;
            mCnpCitcleNumberProgress.setProgress(progress);
            /*设置加速减速的效果*/
            if (nub == 1) {//nub==1 匀速
                handler.sendEmptyMessageDelayed(WHAT_INCREASE, getRadomNumber(50, 100));
            }
            if (nub == 2) {//nub==2 加速
                handler.sendEmptyMessageDelayed(WHAT_INCREASE, getRadomNumber(50, 0));
            }
            if (nub == 3) {//nub==3 减速
                handler.sendEmptyMessageDelayed(WHAT_INCREASE, getRadomNumber(50, 1500));
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
        /*初始化数据*/
        mCnpCitcleNumberProgress = (CircleNumberProgress) findViewById(R.id.cnp_citcleNumberProgress);
        mBtnNumberProgressBar = (Button) findViewById(R.id.btn_numberProgressBar);
        mJiasu = (Button) findViewById(R.id.jiasu);
        mJiansu = (Button) findViewById(R.id.jiansu);
        mBtnNumberProgressBar.setOnClickListener(this);
        mJiasu.setOnClickListener(this);
        mJiansu.setOnClickListener(this);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_numberProgressBar:
                increase();
                nub = 1;
                break;
            case R.id.jiasu:
                nub = 2;
                Toast.makeText(MainActivity.this, "快如闪电了", Toast.LENGTH_SHORT).show();
                break;
            case R.id.jiansu:
                nub = 3;
                Toast.makeText(MainActivity.this, "不能再慢了", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}