package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.hopewaytech.HiConsolePrinter;
import com.hopewaytech.HiLog;
import com.hopewaytech.HiLogConfig;
import com.hopewaytech.HiLogManager;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {

    private NavController mNavController;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        HiConsolePrinter hiP = new HiConsolePrinter();
        HiLogManager.init(new HiLogConfig() {
            @Override
            public JsonParser injectJsonParser() {
                return super.injectJsonParser();
            }

            @Override
            public boolean includeThread() {
                return true;
            }


        }, hiP);
    }

    private void onEvent() {

    }

    public void show(View view) {
        HiLog.v("我输出了信息没有啊");
    }


}