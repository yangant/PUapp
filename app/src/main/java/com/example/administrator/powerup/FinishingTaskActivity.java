package com.example.administrator.powerup;

import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.os.Handler;
import android.view.View;

public class FinishingTaskActivity extends AppCompatActivity {
    private int recLen = 11;
    private TextView txtView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finishing_task);

        txtView = (TextView)findViewById(R.id.countdowntext);

        Message message = handler.obtainMessage(1);     // Message
        handler.sendMessageDelayed(message, 1000);
    }

    final Handler handler = new Handler(){

        public void handleMessage(Message msg){         // handle message
            switch (msg.what) {
                case 1:
                    recLen--;
                    txtView.setText("" + recLen);

                    if(recLen > 0){
                        Message message = handler.obtainMessage(1);
                        handler.sendMessageDelayed(message, 1000);      // send message
                    }else{
                        txtView.setVisibility(View.GONE);
                    }
            }

            super.handleMessage(msg);
        }
    };
}
