package com.example.administrator.powerup;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class TaskActivity extends AppCompatActivity {

    private TextView tname_input;
    private TextView tcontent_input;
    private int style;
    private int duration;
    private Task newTask = new Task();
    private int po;
    private int s_style;
    private Spinner spinner_style;
    private Spinner spinner_duration;
    private Spinner sport_style;

    private static String url = "http://192.168.191.3:8080/ServletTestOne/"; // IP地址请改为你自己的IP

    private static String URL_Register = url + "TaskServlet";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        int position = intent.getIntExtra("data", 0);
        po = position;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        spinner_style = (Spinner) findViewById(R.id.spinner_style);
        spinner_duration = (Spinner) findViewById(R.id.spinner_duration);
        sport_style = (Spinner) findViewById(R.id.sport_style);
        Button add_button = (Button) findViewById(R.id.add_button);
        Button finish_button = (Button) findViewById(R.id.finish_button);
        Button delete_button = (Button) findViewById(R.id.delete_button);
        tname_input = (TextView) findViewById(R.id.tname_input);
        tcontent_input = (TextView) findViewById(R.id.tcontent_input);
        if (position < 11) {
            newTask = LogInActivity.myPlayer.getPlayer_tasks().get(position);
            add_button.setText("更改任务");
            tname_input.setText(newTask.getTask_name());
            tcontent_input.setText(newTask.getTask_content());
            spinner_style.setSelection(newTask.getTask_style());
            spinner_duration.setSelection(newTask.getTask_duration());
        } else {
            finish_button.setVisibility(View.INVISIBLE);
            delete_button.setVisibility(View.INVISIBLE);
        }

    }

    public void addNewTask(View view) {
        if (tname_input.getText().toString().trim().isEmpty()) {
            Toast t = Toast.makeText(this,"请输入任务名称！", Toast.LENGTH_LONG);
            t.show();
        } else if (tcontent_input.getText().toString().trim().isEmpty()) {
            Toast t = Toast.makeText(this,"请输入任务内容！", Toast.LENGTH_LONG);
            t.show();
        } else {
            style = spinner_style.getSelectedItemPosition();
            duration = spinner_duration.getSelectedItemPosition();
            if (po < 11) {
                new AsyncTask<String , Integer, String>(
                ){
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
                            String registerUrlStr = URL_Register + "?operation=4" + "&taskname=" + tname_input.getText().toString() + "&content=" + tcontent_input.getText().toString() + "&style=" + style + "&duration=" + duration + "&playerID=" + LogInActivity.myPlayer.getPlayer_name() + "&ftaskname=" + LogInActivity.myPlayer.getPlayer_tasks().get(po).getTask_name() + "&fcontent=" + LogInActivity.myPlayer.getPlayer_tasks().get(po).getTask_content() + "&fstyle=" + LogInActivity.myPlayer.getPlayer_tasks().get(po).getTask_style() + "&fduration=" + LogInActivity.myPlayer.getPlayer_tasks().get(po).getTask_duration();
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
                     * @param s
                     */
                    @Override
                    protected void onPostExecute(String s) {
                        if (s.equals("200")) {
                            Toast t = Toast.makeText(TaskActivity.this,"更改任务成功", Toast.LENGTH_LONG);
                            t.show();
                            newTask.setTask_name(tname_input.getText().toString());
                            newTask.setTask_content(tcontent_input.getText().toString());
                            newTask.setTask_duration(duration);
                            newTask.setTask_style(style);
                            Intent intent = new Intent(TaskActivity.this, MainActivity.class);
                            startActivity(intent);
                        } else if (s.equals("300")) {
                            Toast t = Toast.makeText(TaskActivity.this,"更改任务失败", Toast.LENGTH_LONG);
                            t.show();
                        }
                    }

                }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            } else {
                new AsyncTask<String , Integer, String>(
                ){
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
                            String registerUrlStr = URL_Register + "?operation=1" + "&taskname=" + tname_input.getText().toString() + "&content=" + tcontent_input.getText().toString() + "&style=" + style + "&duration=" + duration + "&playerID=" + LogInActivity.myPlayer.getPlayer_name();
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
                     * @param s
                     */
                    @Override
                    protected void onPostExecute(String s) {
                        if (s.equals("200")) {
                            Toast t = Toast.makeText(TaskActivity.this,"添加任务成功", Toast.LENGTH_LONG);
                            t.show();
                            newTask.setTask_name(tname_input.getText().toString());
                            newTask.setTask_content(tcontent_input.getText().toString());
                            newTask.setTask_duration(duration);
                            newTask.setTask_style(style);
                            LogInActivity.myPlayer.addTask(newTask);
                            Intent intent = new Intent(TaskActivity.this, MainActivity.class);
                            startActivity(intent);
                        } else if (s.equals("300")) {
                            Toast t = Toast.makeText(TaskActivity.this,"添加任务失败", Toast.LENGTH_LONG);
                            t.show();
                        }
                    }

                }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            }
        }
    }

    public void removeTask(View view) {
        new AsyncTask<String , Integer, String>(
        ){
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
                    String registerUrlStr = URL_Register + "?operation=2" + "&taskname=" + LogInActivity.myPlayer.getPlayer_tasks().get(po).getTask_name() + "&content=" + LogInActivity.myPlayer.getPlayer_tasks().get(po).getTask_content() + "&style=" + LogInActivity.myPlayer.getPlayer_tasks().get(po).getTask_style() + "&duration=" + LogInActivity.myPlayer.getPlayer_tasks().get(po).getTask_duration() + "&playerID=" + LogInActivity.myPlayer.getPlayer_name();
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
             * @param s
             */
            @Override
            protected void onPostExecute(String s) {
                if (s.equals("200")) {
                    Toast t = Toast.makeText(TaskActivity.this,"删除任务成功", Toast.LENGTH_LONG);
                    t.show();
                    LogInActivity.myPlayer.getPlayer_tasks().remove(po);
                    Intent intent = new Intent(TaskActivity.this, MainActivity.class);
                    startActivity(intent);
                } else if (s.equals("300")) {
                    Toast t = Toast.makeText(TaskActivity.this,"删除任务失败", Toast.LENGTH_LONG);
                    t.show();
                }
            }

        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public void finishTask(View view) {
        Intent intent = new Intent(TaskActivity.this, FinishingTaskActivity.class);
        s_style = sport_style.getSelectedItemPosition();
        if(newTask.getTask_style() != 0)
            s_style = 3;
        intent.putExtra("position", po);
        intent.putExtra("taskname", newTask.getTask_name());
        intent.putExtra("content", newTask.getTask_content());
        intent.putExtra("style", newTask.getTask_style());
        intent.putExtra("duration", newTask.getTask_duration());
        intent.putExtra("sport", s_style);
        startActivity(intent);
    }
}
