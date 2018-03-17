package com.example.administrator.powerup;

/**
 * Created by Administrator on 2018/3/17 0017.
 */

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String player_name;
    private String player_password;
    private int player_power;
    private int player_intelligence;
    private int player_charm;
    private List<Task> player_tasks = new ArrayList<Task>();
    private List<miniPlayer> player_powerrank = new ArrayList<miniPlayer>();
    private List<miniPlayer> player_intelligencerank = new ArrayList<miniPlayer>();
    private List<miniPlayer> player_charmrank = new ArrayList<miniPlayer>();

    public void setPlayer_name(String name) {
        player_name = name;
    }

    public void setPlayer_password(String password) {
        player_password = password;
    }

    public void setPlayer_power(int power) {
        player_power = power;
    }

    public void setPlayer_intelligence(int intelligence) {
        player_intelligence = intelligence;
    }

    public void setPlayer_charm(int charm) {
        player_charm = charm;
    }

    public void addTask(Task new_task) {
        player_tasks.add(new_task);
    }

    public void addPowerrank(miniPlayer new_player) {
        player_powerrank.add(new_player);
    }

    public void addIntelligencerank(miniPlayer new_player) {
        player_intelligencerank.add(new_player);
    }

    public void addCharmrank(miniPlayer new_player) {
        player_charmrank.add(new_player);
    }

    public void deleteTask(int n) {
        player_tasks.remove(n);
    }

    public void cleanPowerrank() {
        player_powerrank.clear();
    }

    public void cleanIntelligencerank() {
        player_intelligencerank.clear();
    }

    public void cleanCharmrank() {
        player_charmrank.clear();
    }

    public String getPlayer_name() {
        return player_name;
    }

    public String getPlayer_password() {
        return  player_password;
    }

    public  int getPlayer_power() {
        return player_power;
    }

    public int getPlayer_intelligence() {
        return player_intelligence;
    }

    public int getPlayer_charm() {
        return player_charm;
    }

    public List<Task> getPlayer_tasks() {
        return player_tasks;
    }

    public List<miniPlayer> getPlayer_powerrank() {
        return player_powerrank;
    }

    public List<miniPlayer> getPlayer_intelligencerank() {
        return  player_intelligencerank;
    }

    public List<miniPlayer> getPlayer_charmrank() {
        return player_charmrank;
    }
}
