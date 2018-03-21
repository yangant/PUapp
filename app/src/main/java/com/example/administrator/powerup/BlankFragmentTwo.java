package com.example.administrator.powerup;

/**
 * Created by Administrator on 2018/3/15 0015.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class BlankFragmentTwo extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_two, container, false);
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        TaskAdapter adapter = new TaskAdapter(this.getActivity(), R.layout.task_item, LogInActivity.myPlayer.getPlayer_tasks());
        ListView listView = (ListView) getView().findViewById(R.id.listview);
        listView.setAdapter(adapter);
    }

    public void addTask(View view) {
        Intent intent = new Intent(this.getActivity(), TaskActivity.class);
        startActivity(intent);
    }

}