package com.example.administrator.powerup;

/**
 * Created by Administrator on 2018/3/17 0017.
 */

public class miniPlayer {
    private String p_name;
    private int p_data;

    miniPlayer(String name, int data) {

    }

    public  void setP_name(String name) {
        p_name = name;
    }

    public void setP_data(int data) {
        p_data = data;
    }

    public String getP_name() {
        return p_name;
    }

    public int getP_data() {
        return  p_data;
    }
}
