package com.example.bright.networkdemo;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    WebView webView;
    LinearLayout linearLayout;
    ConnectivityManager connectivityManager;
    NetworkInfo networkInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //find webview
        webView = findViewById(R.id.webId);
        //find linearlayout
        linearLayout = findViewById(R.id.linearId);
        //get connectivity manager
        connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
    }

    public void doProcess(View view) {
        //get the active network
        networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo == null){
            //display - no net
            Snackbar.make(linearLayout, "no connection", Snackbar.LENGTH_SHORT).show();
        }
        else{
            //is connected or not?
            if (networkInfo.isConnected()){
                Snackbar.make(linearLayout, "connection is available", Snackbar.LENGTH_SHORT).show();
                //load web page
                //1. create an object of type LoadMyPage
                LoadMyPage loadMyPage = new LoadMyPage(this);
                //2.
                loadMyPage.execute("http://www.google.com");
            }
        }
    }
}
