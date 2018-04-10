package com.example.administrator.powerup;

/**
 * Created by Administrator on 2018/3/15 0015.
 */

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class BlankFragmentThree extends Fragment {

    private static String url = "http://192.168.191.3:8080/ServletTestOne/"; // IP地址请改为你自己的IP

    private static String URL_Register = url + "FriendsServlet";
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
                                if (edt.getText().toString().equals(LogInActivity.myPlayer.getPlayer_name())) {
                                    Toast t = Toast.makeText(getContext(),"请勿输入自己的名称！", Toast.LENGTH_LONG);
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
                                                String registerUrlStr = URL_Register + "?operation=1" + "&playerID=" + LogInActivity.myPlayer.getPlayer_name() + "&friendID=" + edt.getText().toString();
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
                                            if (s.equals("100")) {
                                                Toast t = Toast.makeText(getContext(),"添加好友成功！", Toast.LENGTH_LONG);
                                                t.show();
                                            } else if (s.equals("300")) {
                                                Toast t = Toast.makeText(getContext(),"此用户不存在！", Toast.LENGTH_LONG);
                                                t.show();
                                            } else if (s.equals("200")) {
                                                Toast t = Toast.makeText(getContext(),"添加好友失败！", Toast.LENGTH_LONG);
                                                t.show();
                                            }
                                        }

                                    }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                                }
                            }
                        })
                        .setNegativeButton("取消", null)
                        .show();
            }
        });
    }

}