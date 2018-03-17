package com.example.administrator.powerup;

/**
 * Created by Administrator on 2018/3/17 0017.
 */
import java.util.*;

public class Task {
    private String task_name;
    private String task_content;
    private int task_style;
    private int task_duration;

    public void setTask_name(String t_name) {
        task_name = t_name;
    }

    public void setTask_content(String t_content) {
        task_content = t_content;
    }

    public void setTask_style(int t_style) {
        task_style = t_style;
    }

    public void setTask_duration(int t_duration) {
        task_duration = t_duration;
    }

    public String getTask_name()  {
        return task_name;
    }

    public String getTask_content()  {
        return task_content;
    }

    public int getTask_style()  {
        return task_style;
    }

    public int getTask_duration()  {
        return task_duration;
    }

}
