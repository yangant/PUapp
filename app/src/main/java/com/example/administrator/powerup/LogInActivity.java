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
            Connector con = new Connector();
            con.register(intput_name.getText().toString(), input_password.getText().toString());
            if(con.result == "100") {
                Toast t = Toast.makeText(this,"用户已存在", Toast.LENGTH_LONG);
                t.show();
            } else if (con.result == "200") {
                Toast t = Toast.makeText(this,"注册成功", Toast.LENGTH_LONG);
                t.show();
            } else if (con.result == "200") {
                Toast t = Toast.makeText(this,"注册失败", Toast.LENGTH_LONG);
                t.show();
            }
            Intent intent = new Intent(LogInActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }

    public void register_click(View v) {
        Intent intent = new Intent(LogInActivity.this, RegisterActivity.class);
        startActivity(intent);
    }
}
