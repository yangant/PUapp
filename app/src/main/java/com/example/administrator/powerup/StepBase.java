package com.example.administrator.powerup;

/**
 * Created by Administrator on 2018/4/15 0015.
 */

import android.content.Context;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

/**
 * 计步传感器抽象类, 子类分为加速度传感器、或计步传感器
 */
public abstract class StepBase implements SensorEventListener {
    private Context context;
    public stepCallBack stepCallBack;
    public SensorManager sensorManager;
    public static int CURRENT_SETP = 0;
    public boolean isAvailable = false;

    public StepBase(Context context, stepCallBack stepCallBack) {
        this.context = context;
        this.stepCallBack = stepCallBack;
    }

    /**
     * 开启计步
     */
    public boolean registerStep() {
        if (sensorManager != null) {
            sensorManager.unregisterListener(this);
            sensorManager = null;
        }
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        registerStepListener();
        return isAvailable;
    }

    /**
     * 注册计步监听器
     */
    protected abstract void registerStepListener();

    /**
     * 注销计步监听器
     */
    public abstract void unregisterStep();
}

