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
import android.widget.TextView;
import android.graphics.Typeface;

public class BlankFragmentOne extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_one, container, false);
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Typeface typeFace1 = Typeface.createFromFile("font/Campus A.tff");
        TextView plaerID = (TextView) getView().findViewById(R.id.playerID);
        plaerID.setText(LogInActivity.myPlayer.getPlayer_name());
        plaerID.setTypeface(typeFace1);
    }

}