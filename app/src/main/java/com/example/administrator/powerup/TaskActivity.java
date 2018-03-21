package com.example.administrator.powerup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

public class TaskActivity extends AppCompatActivity {

    private TextView tname_input;
    private TextView tcontent_input;
    private int style;
    private int duration;
    private Task newTask = new Task();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        Spinner spinner_style = (Spinner) findViewById(R.id.spinner_style);
        Spinner spinner_duration = (Spinner) findViewById(R.id.spinner_duration);
        tname_input = (TextView) findViewById(R.id.tname_input);
        tcontent_input = (TextView) findViewById(R.id.tcontent_input);
    }

    public void addNewTask(View view) {
        newTask.setTask_name(tname_input.getText().toString());
        newTask.setTask_content(tcontent_input.getText().toString());
        newTask.setTask_duration(duration);
        newTask.setTask_style(style);
        LogInActivity.myPlayer.addTask(newTask);
        Intent intent = new Intent(TaskActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
