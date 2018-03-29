package com.example.administrator.powerup;

/**
 * Created by Administrator on 2018/3/15 0015.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Button;
import android.widget.Toast;

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
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), TaskActivity.class);
                intent.putExtra("data", i);
                startActivity(intent);
            }
        });
        Button button = (Button) getView().findViewById(R.id.addbutton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (LogInActivity.myPlayer.getPlayer_tasks().size() < 10) {
                    Intent intent = new Intent(getActivity(), TaskActivity.class);
                    intent.putExtra("data", 11);
                    startActivity(intent);
                } else {
                    Toast t = Toast.makeText(getContext(),"任务已满，请先完成任务！", Toast.LENGTH_LONG);
                    t.show();
                }
            }
        });
    }

}