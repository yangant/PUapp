package com.example.administrator.powerup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.view.*;
import android.content.Intent;
import android.widget.Toast;

public class LogInActivity extends AppCompatActivity {
    private EditText intput_name;
    private EditText input_password;
    private Button loginbutton;
    public static Player myPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        intput_name = (EditText) findViewById(R.id.input_name);
        input_password = (EditText) findViewById(R.id.input_password);
        loginbutton = (Button) findViewById(R.id.loginButton);
    }

    public void login_click(View v) {
        if (intput_name.getText().toString().trim().isEmpty()) {
            Toast t = Toast.makeText(this,"请输入用户名！", Toast.LENGTH_LONG);
            t.show();
        } else if (input_password.getText().toString().trim().isEmpty()) {
            Toast t = Toast.makeText(this,"请输入密码！", Toast.LENGTH_LONG);
            t.show();
        } else {
            myPlayer = new Player();
            myPlayer.setPlayer_name(intput_name.getText().toString());
            myPlayer.setPlayer_password(input_password.getText().toString());
            myPlayer.setPlayer_charm(0);
            myPlayer.setPlayer_power(0);
            myPlayer.setPlayer_intelligence(0);
            miniPlayer myself_p = new miniPlayer(myPlayer.getPlayer_name(), myPlayer.getPlayer_power());
            miniPlayer myself_i = new miniPlayer(myPlayer.getPlayer_name(), myPlayer.getPlayer_intelligence());
            miniPlayer myself_c = new miniPlayer(myPlayer.getPlayer_name(), myPlayer.getPlayer_charm());
            myPlayer.addPowerrank(myself_p);
            myPlayer.addPowerrank(myself_p);
            myPlayer.addIntelligencerank(myself_i);
            myPlayer.addIntelligencerank(myself_i);
            myPlayer.addIntelligencerank(myself_i);
            myPlayer.addCharmrank(myself_c);
            Task task_1 = new Task();
            task_1.setTask_duration(0);
            task_1.setTask_name("读书看报");
            task_1.setTask_content("read and look");
            task_1.setTask_style(1);
            Task task_2 = new Task();
            task_2.setTask_duration(1);
            task_2.setTask_name("打打篮球");
            task_2.setTask_style(0);
            task_2.setTask_content("play basketball");
            myPlayer.addTask(task_1);
            myPlayer.addTask(task_2);
            Intent intent = new Intent(LogInActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }

    public void register_click(View v) {
        Intent intent = new Intent(LogInActivity.this, RegisterActivity.class);
        startActivity(intent);
    }
}
