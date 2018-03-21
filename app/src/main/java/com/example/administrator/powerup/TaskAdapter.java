package com.example.administrator.powerup;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class TaskAdapter extends ArrayAdapter{
    private final int resourceId;

    public TaskAdapter(Context context, int textViewResourceId, ArrayList<Task> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Task task = (Task) getItem(position); // 获取当前项的Fruit实例
        View view = LayoutInflater.from(getContext()).inflate(resourceId, null);//实例化一个对象
        ImageView taskImage = (ImageView) view.findViewById(R.id.task_icon);//获取该布局内的图片视图
        TextView taskName = (TextView) view.findViewById(R.id.task_name);//获取该布局内的文本视图
        TextView taskDuration = (TextView) view.findViewById(R.id.task_duration);//获取该布局内的文本视图
        taskName.setText(task.getTask_name());//为文本视图设置文本内容
        String duration = task.getTask_duration() + "小时";
        taskDuration.setText(duration);
        return view;
    }
}
