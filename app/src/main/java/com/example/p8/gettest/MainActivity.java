package com.example.p8.gettest;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button suivButton ;
    TextView textView;
    final String url_dest = "http://tellmedia.herokuapp.com/api/list";
    final String url_src  = "url ala conn";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        suivButton = (Button) findViewById(R.id.suivant);
        textView = (TextView) findViewById(R.id.textView);


    }

    public void getDataVolley(){
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);


        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, this.url_src,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        textView.setText("Response is: "+ response.substring(0,500));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                textView.setText("That didn't work!");
            }
        });
// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }



    public void getData(){
        // Create a new HttpClient and Post Header
        HttpClient httpclient = new DefaultHttpClient();
        HttpGet httpget = new HttpGet(this.url_src);


        // Execute HTTP Post Request
        HttpResponse response = null;
        try {
            response = httpclient.execute(httpget);


        } catch (ClientProtocolException e) {
            e.printStackTrace();}
        catch (IOException e) {
            e.printStackTrace();}


        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

        } catch (IllegalStateException e) {
            e.printStackTrace();}
        catch (IOException e) {
            e.printStackTrace();
        }

        String line;
        StringBuilder str = new StringBuilder();

        try {
            while((line = reader.readLine()) != null) {
                str.append(line);
                Log.d("HTTP Reponse", line);
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.suivant:
                getDataVolley();
        }
    }
}
