package com.example.administrator.powerup;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class RegisterActivity extends AppCompatActivity {
    private EditText intput_name;
    private EditText input_password;
    private EditText confirm_password;

    private static String url = "http://192.168.191.3:8080/ServletTestOne/"; // IP地址请改为你自己的IP

    private static String URL_Register = url + "RegisterServlet";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        intput_name = (EditText) findViewById(R.id.input_name);
        input_password = (EditText) findViewById(R.id.input_password);
        confirm_password = (EditText) findViewById(R.id.confirm_password);
    }

    public void register_click(View v) {
        if (intput_name.getText().toString().trim().isEmpty()) {
            Toast t = Toast.makeText(this,"请输入用户名！", Toast.LENGTH_LONG);
            t.show();
        } else if (input_password.getText().toString().trim().isEmpty()) {
            Toast t = Toast.makeText(this,"请输入密码！", Toast.LENGTH_LONG);
            t.show();
        } else if (!confirm_password.getText().toString().equals(input_password.getText().toString())) {
            Toast t = Toast.makeText(this,"重复密码错误！", Toast.LENGTH_LONG);
            t.show();
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
                        String registerUrlStr = URL_Register + "?playerID=" + intput_name.getText().toString() + "&password=" + input_password.getText().toString();
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
                    if(s.equals("100")) {
                        Toast t = Toast.makeText(RegisterActivity.this,"用户已存在", Toast.LENGTH_LONG);
                        t.show();
                    } else if (s.equals("200")) {
                        Toast t = Toast.makeText(RegisterActivity.this,"注册成功", Toast.LENGTH_LONG);
                        t.show();
                        Intent intent = new Intent(RegisterActivity.this, LogInActivity.class);
                        startActivity(intent);
                    } else if (s.equals("300")) {
                        Toast t = Toast.makeText(RegisterActivity.this,"注册失败", Toast.LENGTH_LONG);
                        t.show();
                    }
                }

            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }
    }

}
