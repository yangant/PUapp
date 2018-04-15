package com.example.administrator.powerup;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class FinishingTaskActivity extends AppCompatActivity implements stepCallBack{
    private int recLen;
    private TextView txtView;
    private String taskname;
    private String content;
    private int style;
    private int position;
    private int duration;
    private TextView stepText;
    private int step;
    private int sport;
    private String loc;

    private static String url = "http://192.168.191.3:8080/ServletTestOne/"; // IP地址请改为你自己的IP

    private static String URL_Register = url + "TaskServlet";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finishing_task);

        Intent intent = getIntent();
        position = intent.getIntExtra("position", 0);
        style = intent.getIntExtra("style", 0);
        duration = intent.getIntExtra("duration", 0);
        sport = intent.getIntExtra("sport", 0);
        taskname = intent.getStringExtra(taskname);
        content = intent.getStringExtra(content);
        recLen = (duration + 1) * 1800;
        LinearLayout mainlayout = (LinearLayout) findViewById(R.id.mainlayout);
        //if (style == 0)
          //  mainlayout.setBackgroundResource(R.mipmap.wulixiulian);
        //else if (style == 1)
          //  mainlayout.setBackgroundResource(R.mipmap.zhilixiulian);
        //else
          //  mainlayout.setBackgroundResource(R.mipmap.meilixiulian);
        txtView = (TextView)findViewById(R.id.countdowntext);
        stepText = (TextView)findViewById(R.id.steptext);

        Message message = handler.obtainMessage(1);     // Message
        handler.sendMessageDelayed(message, 0);

        // 开启计步监听, 分为加速度传感器、或计步传感器
        StepBase stepSensor = new StepSensorPedometer(this, this);
        if (!stepSensor.registerStep()) {
            Toast.makeText(this, "计步传传感器不可用！", Toast.LENGTH_SHORT).show();
            stepSensor = new StepSensorAcceleration(this, this);
            if (!stepSensor.registerStep()) {
                Toast.makeText(this, "加速度传感器不可用！", Toast.LENGTH_SHORT).show();
            }
        }

        if(sport == 1) {
            double latitude = 0.0;
            double longitude = 0.0;
            LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                        || checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {  //从gps获取经纬度
                    Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    if (location != null) {
                        latitude = location.getLatitude();
                        longitude = location.getLongitude();
                    }
                }
            }
            final String url_location = "http://api.map.baidu.com/geocoder/v2/?callback=renderReverse&location=23.064473,113.399455&output=json&pois=1&ak=EgEENzGLwjIh52bHtn7HvbZ7AlSv54kl";
            new AsyncTask<String, Integer, String>(
            ) {
                @Override
                protected void onPreExecute() {
                    Log.w("WangJ", "task onPreExecute()");
                }

                /**
                 * @param params 这里的params是一个数组，即AsyncTask在激活运行是调用execute()方法传入的参数
                 */
                @Override
                protected String doInBackground(String... params) {
                    Log.w("WangJ", "task doInBackground()");
                    HttpURLConnection connection = null;
                    StringBuilder response = new StringBuilder();
                    try {
                        URL url = new URL(url_location); // 声明一个URL,注意如果用百度首页实验，请使用https开头，否则获取不到返回报文
                        connection = (HttpURLConnection) url.openConnection(); // 打开该URL连接
                        connection.setRequestMethod("GET"); // 设置请求方法，“POST或GET”，我们这里用GET，在说到POST的时候再用POST
                        connection.setConnectTimeout(80000); // 设置连接建立的超时时间
                        connection.setReadTimeout(80000); // 设置网络报文收发超时时间
                        InputStream in = connection.getInputStream();  // 通过连接的输入流获取下发报文，然后就是Java的流处理
                        JsonReader reader = new JsonReader(new InputStreamReader(in,"utf-8"));
                        reader.beginObject();
                        while (reader.hasNext()) {
                            String name = reader.nextName();
                           if (name.equals("formatted_address")) {
                                loc = reader.nextString();
                            } else {
                                reader.skipValue();
                            }
                        }
                        reader.endObject();
                        BufferedReader Breader = new BufferedReader(new InputStreamReader(in));
                        String line;
                        while ((line = Breader.readLine()) != null) {
                            response.append(line);
                        }
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return response.toString(); // 这里返回的结果就作为onPostExecute方法的入参
                }

                @Override
                protected void onProgressUpdate(Integer... values) {
                    // 如果在doInBackground方法，那么就会立刻执行本方法
                    // 本方法在UI线程中执行，可以更新UI元素，典型的就是更新进度条进度，一般是在下载时候使用
                }

                /**
                 * 运行在UI线程中，所以可以直接操作UI元素
                 *
                 * @param s
                 */
                @Override
                protected void onPostExecute(String s) {
                    stepText.setText(loc);
                    Toast t = Toast.makeText(FinishingTaskActivity.this, s, Toast.LENGTH_LONG);
                    t.show();
                }

            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }

    }

    final Handler handler = new Handler(){

        public void handleMessage(Message msg){         // handle message
            switch (msg.what) {
                case 1:
                    recLen--;

                    if(recLen > 60){
                        Message message = handler.obtainMessage(1);
                        handler.sendMessageDelayed(message, 1000);      // send message
                        txtView.setText("距离完成任务还有" + recLen/60 + "分钟");
                    } else if(recLen <= 60 && recLen > 0) {
                        Message message = handler.obtainMessage(1);
                        handler.sendMessageDelayed(message, 1000);      // send message
                        txtView.setText("距离完成任务还有" + recLen+ "秒");
                    }else{
                        if (sport == 0) {//跑步
                            if (step >= ((duration + 1) * 3600)) {//步数达标
                                new AsyncTask<String, Integer, String>(
                                ) {
                                    @Override
                                    protected void onPreExecute() {
                                        Log.w("WangJ", "task onPreExecute()");
                                    }

                                    /**
                                     * @param params 这里的params是一个数组，即AsyncTask在激活运行是调用execute()方法传入的参数
                                     */
                                    @Override
                                    protected String doInBackground(String... params) {
                                        Log.w("WangJ", "task doInBackground()");
                                        HttpURLConnection connection = null;
                                        StringBuilder response = new StringBuilder();
                                        try {
                                            String registerUrlStr = URL_Register + "?operation=3" + "&taskname=" + LogInActivity.myPlayer.getPlayer_tasks().get(position).getTask_name() + "&content=" + LogInActivity.myPlayer.getPlayer_tasks().get(position).getTask_content() + "&style=" + LogInActivity.myPlayer.getPlayer_tasks().get(position).getTask_style() + "&duration=" + LogInActivity.myPlayer.getPlayer_tasks().get(position).getTask_duration() + "&playerID=" + LogInActivity.myPlayer.getPlayer_name();
                                            URL url = new URL(registerUrlStr); // 声明一个URL,注意如果用百度首页实验，请使用https开头，否则获取不到返回报文
                                            connection = (HttpURLConnection) url.openConnection(); // 打开该URL连接
                                            connection.setRequestMethod("GET"); // 设置请求方法，“POST或GET”，我们这里用GET，在说到POST的时候再用POST
                                            connection.setConnectTimeout(80000); // 设置连接建立的超时时间
                                            connection.setReadTimeout(80000); // 设置网络报文收发超时时间
                                            InputStream in = connection.getInputStream();  // 通过连接的输入流获取下发报文，然后就是Java的流处理
                                            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                                            String line;
                                            while ((line = reader.readLine()) != null) {
                                                response.append(line);
                                            }
                                        } catch (MalformedURLException e) {
                                            e.printStackTrace();
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                        return response.toString(); // 这里返回的结果就作为onPostExecute方法的入参
                                    }

                                    @Override
                                    protected void onProgressUpdate(Integer... values) {
                                        // 如果在doInBackground方法，那么就会立刻执行本方法
                                        // 本方法在UI线程中执行，可以更新UI元素，典型的就是更新进度条进度，一般是在下载时候使用
                                    }

                                    /**
                                     * 运行在UI线程中，所以可以直接操作UI元素
                                     *
                                     * @param s
                                     */
                                    @Override
                                    protected void onPostExecute(String s) {
                                        if (s.equals("200")) {
                                            Toast t = Toast.makeText(FinishingTaskActivity.this, "完成任务成功", Toast.LENGTH_LONG);
                                            t.show();
                                            if (style == 0) {
                                                int mark = LogInActivity.myPlayer.getPlayer_power() + duration + 1;
                                                LogInActivity.myPlayer.setPlayer_power(mark);
                                            } else if (style == 1) {
                                                int mark = LogInActivity.myPlayer.getPlayer_intelligence() + duration + 1;
                                                LogInActivity.myPlayer.setPlayer_intelligence(mark);
                                            } else if (style == 2) {
                                                int mark = LogInActivity.myPlayer.getPlayer_charm() + duration + 1;
                                                LogInActivity.myPlayer.setPlayer_charm(mark);
                                            }
                                            LogInActivity.myPlayer.getPlayer_tasks().remove(position);
                                        } else if (s.equals("300")) {
                                            Toast t = Toast.makeText(FinishingTaskActivity.this, "完成任务失败", Toast.LENGTH_LONG);
                                            t.show();
                                        }
                                        Intent intent = new Intent(FinishingTaskActivity.this, MainActivity.class);
                                        startActivity(intent);
                                    }

                                }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                            } else {
                                Toast t = Toast.makeText(FinishingTaskActivity.this, "步数不足，完成任务失败！", Toast.LENGTH_LONG);
                                t.show();
                                Intent intent = new Intent(FinishingTaskActivity.this, MainActivity.class);
                                startActivity(intent);
                            }
                        }
                    }
            }

            super.handleMessage(msg);
        }
    };

    @Override
    public void Step(int stepNum) {
        // 计步回调
        if (sport == 0) {
            stepText.setText("步数:" + stepNum);
            step = stepNum;
        }
    }
}
