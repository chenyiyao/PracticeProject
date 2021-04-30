package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity1 extends AppCompatActivity {

    private Button mBtn_send;
    private EditText mEt_input;
    private TextView mTextview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        mBtn_send = findViewById(R.id.btn_send);
        mEt_input = findViewById(R.id.et_input);
        mTextview = findViewById(R.id.textview);

        onEvent();
    }

    private void onEvent() {
        //点击事件
        mBtn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //发送请求
                sendRequest();
            }
        });
//        supportFragmentManager.beginTransaction()
//                .replace(R.id.ll_fragment_navigation, finalHost)
//                .setPrimaryNavigationFragment(finalHost)
//                .commit()
    }

    private void sendRequest() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection urlConnection = null;
                try {
                    URL url = new URL("http://www.zyrta.cn/api.php?op=Test_APP");
                    urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setDoInput(true);
                    urlConnection.setRequestProperty("Accept-Charset", "utf-8");
                    urlConnection.setRequestProperty("Content-type", "application/text;charset=UTF-8");
                    //请求方式
                    urlConnection.setRequestMethod("POST");
                    //写入参数
                    writeBody(urlConnection);
                    //连接
                    urlConnection.connect();
                    int responseCode = urlConnection.getResponseCode();
                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        //读取结果
                        getResponResult(urlConnection);
                    } else {
                        Toast.makeText(MainActivity1.this, "请求失败", Toast.LENGTH_LONG);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();


    }

    private void writeBody(HttpURLConnection urlConnection) throws IOException {
        String body = "data=" + mEt_input.getText().toString();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(urlConnection.getOutputStream(),"utf-8"));
        writer.write(body);
        writer.close();
    }

    private void getResponResult(HttpURLConnection urlConnection) throws IOException {
        InputStream in = urlConnection.getInputStream();
        BufferedReader buff = new BufferedReader(new InputStreamReader(in, "utf-8"));
        StringBuilder stringBuilder = new StringBuilder();
        String line = "";
        while ((line = buff.readLine()) != null) {
            stringBuilder.append(line);
        }
        buff.close();
        //取得结果
        final String result = stringBuilder.toString();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //切回主线程,设置结果到文本框
                setResult(result);
            }
        });
    }

    private void setResult(final String s) {
        mTextview.setText(s);
    }
}