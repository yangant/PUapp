package com.example.administrator.powerup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.view.*;
import android.content.Intent;

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
        myPlayer = new Player();
        myPlayer.setPlayer_name(intput_name.getText().toString());
        myPlayer.setPlayer_password(input_password.getText().toString());
        Intent intent = new Intent(LogInActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
