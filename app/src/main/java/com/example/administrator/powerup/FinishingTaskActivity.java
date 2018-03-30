package com.example.administrator.powerup;

import android.content.Intent;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.os.Handler;
import android.view.View;

public class FinishingTaskActivity extends AppCompatActivity {
    private int recLen;
    private TextView txtView;
    private int style;
    private int position;
    private int duration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finishing_task);

        Intent intent = getIntent();
        position = intent.getIntExtra("position", 0);
        style = intent.getIntExtra("style", 0);
        duration = intent.getIntExtra("duration", 0);
        recLen = (duration + 1) * 1800;
        LinearLayout mainlayout = (LinearLayout) findViewById(R.id.mainlayout);
        if (style == 0)
            mainlayout.setBackgroundResource(R.mipmap.wulixiulian);
        else if (style == 1)
            mainlayout.setBackgroundResource(R.mipmap.zhilixiulian);
        else
            mainlayout.setBackgroundResource(R.mipmap.meilixiulian);
        txtView = (TextView)findViewById(R.id.countdowntext);

        Message message = handler.obtainMessage(1);     // Message
        handler.sendMessageDelayed(message, 0);
    }

    final Handler handler = new Handler(){

        public void handleMessage(Message msg){         // handle message
            switch (msg.what) {
                case 1:
                    recLen--;

                    if(recLen > 60){
                        Message message = handler.obtainMessage(1);
                        handler.sendMessageDelayed(message, 1000);      // send message
                        txtView.setText("距离完成任务还有" + recLen/60 + "分钟");
                    } else if(recLen <= 60 && recLen > 0) {
                        Message message = handler.obtainMessage(1);
                        handler.sendMessageDelayed(message, 1000);      // send message
                        txtView.setText("距离完成任务还有" + recLen+ "秒");
                    }else{
                        if (style == 0) {
                            int mark =  LogInActivity.myPlayer.getPlayer_power() + duration + 1;
                            LogInActivity.myPlayer.setPlayer_power(mark);
                        } else if (style == 1) {
                            int mark =  LogInActivity.myPlayer.getPlayer_intelligence() + duration + 1;
                            LogInActivity.myPlayer.setPlayer_intelligence(mark);
                        } else if (style == 2) {
                            int mark =  LogInActivity.myPlayer.getPlayer_charm() + duration + 1;
                            LogInActivity.myPlayer.setPlayer_charm(mark);
                        }
                        LogInActivity.myPlayer.getPlayer_tasks().remove(position);
                        Intent intent = new Intent(FinishingTaskActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
            }

            super.handleMessage(msg);
        }
    };
}
