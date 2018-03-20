package com.example.administrator.powerup;

/**
 * Created by Administrator on 2018/3/15 0015.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

public class BlankFragmentOne extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_one, container, false);
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //界面元素关联数据
        TextView plaerID = (TextView) getView().findViewById(R.id.playerID);
        plaerID.setText(LogInActivity.myPlayer.getPlayer_name());
        TextView level = (TextView) getView().findViewById(R.id.level);
        String lv = "LV " + LogInActivity.myPlayer.getLevel();
        level.setText(lv);
        ProgressBar powerbar = (ProgressBar) getView().findViewById(R.id.powerbar);
        ProgressBar intellbar = (ProgressBar) getView().findViewById(R.id.intellbar);
        ProgressBar charmbar = (ProgressBar) getView().findViewById(R.id.charmbar);
        TextView powerlevel = (TextView) getView().findViewById(R.id.powerlevel);
        TextView intelllevel = (TextView) getView().findViewById(R.id.intelllevel);
        TextView charmlevel = (TextView) getView().findViewById(R.id.charmlevel);
        int powerLevel = LogInActivity.myPlayer.getPlayer_power()/10;
        int powerNumber = LogInActivity.myPlayer.getPlayer_power() - powerLevel*10;
        powerbar.setProgress(powerNumber*10);
        powerlevel.setText(powerLevel);
        int intellLevel = LogInActivity.myPlayer.getPlayer_intelligence()/10;
        int intellNumber = LogInActivity.myPlayer.getPlayer_intelligence() - intellLevel*10;
        intellbar.setProgress(intellNumber*10);
        intelllevel.setText(intellLevel);
        int charmLevel = LogInActivity.myPlayer.getPlayer_charm()/10;
        int charmNumber = LogInActivity.myPlayer.getPlayer_charm() - charmLevel*10;
        charmbar.setProgress(charmNumber*10);
        charmlevel.setText(charmLevel);
    }

}