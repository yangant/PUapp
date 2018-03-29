package com.example.administrator.powerup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
    private int po;
    private Spinner spinner_style;
    private Spinner spinner_duration;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        int position = intent.getIntExtra("data", 0);
        po = position;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        spinner_style = (Spinner) findViewById(R.id.spinner_style);
        spinner_duration = (Spinner) findViewById(R.id.spinner_duration);
        Button add_button = (Button) findViewById(R.id.add_button);
        Button finish_button = (Button) findViewById(R.id.finish_button);
        Button delete_button = (Button) findViewById(R.id.delete_button);
        tname_input = (TextView) findViewById(R.id.tname_input);
        tcontent_input = (TextView) findViewById(R.id.tcontent_input);
        if (position < 11) {
            newTask = LogInActivity.myPlayer.getPlayer_tasks().get(position);
            add_button.setText("更改任务");
            tname_input.setText(newTask.getTask_name());
            tcontent_input.setText(newTask.getTask_content());
            spinner_style.setSelection(newTask.getTask_style());
            spinner_duration.setSelection(newTask.getTask_duration());
        } else {
            finish_button.setVisibility(View.INVISIBLE);
            delete_button.setVisibility(View.INVISIBLE);
        }

    }

    public void addNewTask(View view) {
        style = spinner_style.getSelectedItemPosition();
        duration = spinner_duration.getSelectedItemPosition();
        newTask.setTask_name(tname_input.getText().toString());
        newTask.setTask_content(tcontent_input.getText().toString());
        newTask.setTask_duration(duration);
        newTask.setTask_style(style);
        if (po < 11) {
        } else {
            LogInActivity.myPlayer.addTask(newTask);
        }
        Intent intent = new Intent(TaskActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void removeTask(View view) {
        LogInActivity.myPlayer.getPlayer_tasks().remove(po);
        Intent intent = new Intent(TaskActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void finishTask(View view) {
        if (newTask.getTask_style() == 0) {
            int mark =  LogInActivity.myPlayer.getPlayer_power() + newTask.getTask_duration() + 1;
            LogInActivity.myPlayer.setPlayer_power(mark);
        } else if (newTask.getTask_style() == 1) {
            int mark =  LogInActivity.myPlayer.getPlayer_intelligence() + newTask.getTask_duration() + 1;
            LogInActivity.myPlayer.setPlayer_intelligence(mark);
        } else if (newTask.getTask_style() == 2) {
            int mark =  LogInActivity.myPlayer.getPlayer_charm() + newTask.getTask_duration() + 1;
            LogInActivity.myPlayer.setPlayer_charm(mark);
        }
        LogInActivity.myPlayer.getPlayer_tasks().remove(po);
        Intent intent = new Intent(TaskActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
