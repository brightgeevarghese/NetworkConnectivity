package com.example.bright.networkdemo;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by bright on 24/02/18.
 */

public class LoadMyPage extends AsyncTask<String, Void, String> {
    MainActivity mainActivity;
    public LoadMyPage(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    protected String doInBackground(String... strings) {
        String s = strings[0];
        //create an URL object
        try {
            URL url = new URL(s);
            //open the connection
            URLConnection urlConnection = url.openConnection();
            //type cast urlconnection to HttpURLConnection
            HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;
            //set the read time out
            httpURLConnection.setReadTimeout(5000);
            //set the request type
            //httpURLConnection.setRequestMethod("GET");
            //do the connect
            httpURLConnection.connect();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String data="";
            StringBuilder stringBuilder = new StringBuilder();
            while ((data = bufferedReader.readLine()) != null){
                stringBuilder.append(data);
            }
            return stringBuilder.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        //load s into WebView
        mainActivity.webView.loadData(
                s,                          //data to load
                "text/html",      //mime type
                null               //data encoding
        );
    }
}
