package com.bwie.wangbingyang20170904;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

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

    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            progress++;
            cnp_citcleNumberProgress.setProgress(progress);
            handler.sendEmptyMessageDelayed(WHAT_INCREASE, getRadomNumber(50, 300));
            if (progress >= 100) {
                handler.removeMessages(WHAT_INCREASE);
            }
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cnp_citcleNumberProgress = (CircleNumberProgress) findViewById(R.id.cnp_citcleNumberProgress);
        Button btn_numberProgressBar = (Button) findViewById(R.id.btn_numberProgressBar);
        btn_numberProgressBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                increase();
//                cnp_citcleNumberProgress.
            }
        });
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
}
