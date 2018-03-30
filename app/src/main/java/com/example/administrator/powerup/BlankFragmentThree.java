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
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.EditText;
import android.app.AlertDialog;
import android.content.DialogInterface;

public class BlankFragmentThree extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_three, container, false);
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //界面元素关联数据
        TextView p_name_one = (TextView) getView().findViewById(R.id.p_name_one);
        TextView p_name_two = (TextView) getView().findViewById(R.id.p_name_two);
        TextView p_name_three = (TextView) getView().findViewById(R.id.p_name_three);
        TextView p_number_one = (TextView) getView().findViewById(R.id.p_number_one);
        TextView p_number_two = (TextView) getView().findViewById(R.id.p_number_two);
        TextView p_number_three = (TextView) getView().findViewById(R.id.p_number_three);
        TextView i_name_one = (TextView) getView().findViewById(R.id.i_name_one);
        TextView i_name_two = (TextView) getView().findViewById(R.id.i_name_two);
        TextView i_name_three = (TextView) getView().findViewById(R.id.i_name_three);
        TextView i_number_one = (TextView) getView().findViewById(R.id.i_number_one);
        TextView i_number_two = (TextView) getView().findViewById(R.id.i_number_two);
        TextView i_number_three = (TextView) getView().findViewById(R.id.i_number_three);
        TextView c_name_one = (TextView) getView().findViewById(R.id.c_name_one);
        TextView c_name_two = (TextView) getView().findViewById(R.id.c_name_two);
        TextView c_name_three = (TextView) getView().findViewById(R.id.c_name_three);
        TextView c_number_one = (TextView) getView().findViewById(R.id.c_number_one);
        TextView c_number_two = (TextView) getView().findViewById(R.id.c_number_two);
        TextView c_number_three = (TextView) getView().findViewById(R.id.c_number_three);
        if (LogInActivity.myPlayer.getPlayer_powerrank().size() >= 1) {
            p_name_one.setText(LogInActivity.myPlayer.getPlayer_powerrank().get(0).getP_name());
            p_number_one.setText(Integer.toString(LogInActivity.myPlayer.getPlayer_powerrank().get(0).getP_data()));
        } else {
            p_name_one.setText(null);
            p_number_one.setText(null);
        }
        if (LogInActivity.myPlayer.getPlayer_powerrank().size() >= 2) {
            p_name_two.setText(LogInActivity.myPlayer.getPlayer_powerrank().get(1).getP_name());
            p_number_two.setText(Integer.toString(LogInActivity.myPlayer.getPlayer_powerrank().get(1).getP_data()));
        } else {
            p_name_two.setText(null);
            p_number_two.setText(null);
        }
        if (LogInActivity.myPlayer.getPlayer_powerrank().size() >= 3) {
            p_name_three.setText(LogInActivity.myPlayer.getPlayer_powerrank().get(2).getP_name());
            p_number_three.setText(Integer.toString(LogInActivity.myPlayer.getPlayer_powerrank().get(2).getP_data()));
        } else {
            p_name_three.setText(null);
            p_number_three.setText(null);
        }

        if (LogInActivity.myPlayer.getPlayer_intelligencerank().size() >= 1) {
            i_name_one.setText(LogInActivity.myPlayer.getPlayer_intelligencerank().get(0).getP_name());
            i_number_one.setText(Integer.toString(LogInActivity.myPlayer.getPlayer_intelligencerank().get(0).getP_data()));
        } else {
            i_name_one.setText(null);
            i_number_one.setText(null);
        }
        if (LogInActivity.myPlayer.getPlayer_intelligencerank().size() >= 2) {
            i_name_two.setText(LogInActivity.myPlayer.getPlayer_intelligencerank().get(1).getP_name());
            i_number_two.setText(Integer.toString(LogInActivity.myPlayer.getPlayer_intelligencerank().get(1).getP_data()));
        } else {
            i_name_two.setText(null);
            i_number_two.setText(null);
        }
        if (LogInActivity.myPlayer.getPlayer_intelligencerank().size() >= 3) {
            i_name_three.setText(LogInActivity.myPlayer.getPlayer_intelligencerank().get(2).getP_name());
            i_number_three.setText(Integer.toString(LogInActivity.myPlayer.getPlayer_intelligencerank().get(2).getP_data()));
        } else {
            i_name_three.setText(null);
            i_number_three.setText(null);
        }

        if (LogInActivity.myPlayer.getPlayer_charmrank().size() >= 1) {
            c_name_one.setText(LogInActivity.myPlayer.getPlayer_charmrank().get(0).getP_name());
            c_number_one.setText(Integer.toString(LogInActivity.myPlayer.getPlayer_charmrank().get(0).getP_data()));
        } else {
            c_name_one.setText(null);
            c_number_one.setText(null);
        }
        if (LogInActivity.myPlayer.getPlayer_charmrank().size() >= 2) {
            c_name_two.setText(LogInActivity.myPlayer.getPlayer_charmrank().get(1).getP_name());
            c_number_two.setText(Integer.toString(LogInActivity.myPlayer.getPlayer_charmrank().get(1).getP_data()));
        } else {
            c_name_two.setText(null);
            c_number_two.setText(null);
        }
        if (LogInActivity.myPlayer.getPlayer_charmrank().size() >= 3) {
            c_name_three.setText(LogInActivity.myPlayer.getPlayer_charmrank().get(2).getP_name());
            c_number_three.setText(Integer.toString(LogInActivity.myPlayer.getPlayer_charmrank().get(2).getP_data()));
        } else {
            c_name_three.setText(null);
            c_number_three.setText(null);
        }
        Button button = (Button) getView().findViewById(R.id.add_friend);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText edt = new EditText(getContext());
                edt.setMinLines(3);
                new AlertDialog.Builder(getContext())
                        .setTitle("请输入好友名称")
                        .setIcon(android.R.drawable.ic_dialog_info)
                        .setView(edt)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface arg0, int arg1) {
                                Toast t = Toast.makeText(getContext(),"任务已满，请先完成任务！", Toast.LENGTH_LONG);
                                t.show();
                            }
                        })
                        .setNegativeButton("取消", null)
                        .show();
            }
        });
    }

}