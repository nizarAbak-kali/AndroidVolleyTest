package com.example.p8.colorconverter;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void convert(){
        double h,s,v ;
        EditText hueS =(EditText) findViewById(R.id.editTextHue);
        EditText satS =(EditText) findViewById(R.id.editTextSaturation);
        EditText valS =(EditText) findViewById(R.id.editTextValue);
        h = Integer.parseInt(hueS.getText().toString());
        s = Integer.parseInt(satS.getText().toString());
        v = Integer.parseInt(valS.getText().toString());

        h = h % 360;
        s = s/100;
        v = v/100;

        Log.d("Convert hsv"," "+h+" "+s+" "+v);


        double c , x , m ;

        c = v * s ;
        x = (c * ( 1 - (Math.abs( (h/60) % 2 ) - 1 ) ));
        m = v - c ;
        Log.d("Convert cxm"," "+c+" "+x+" "+m);

        double r_prime = 0 ,g_prime= 0,b_prime = 0;

        if( 0 <= h && h <60 ){
            Log.d("Convert","1");
            r_prime = c ;
            g_prime = x ;
            b_prime = 0 ;
        }
        if( 60 <= h && h <120 ){
            Log.d("Convert","2");
            r_prime = x ;
            g_prime = c ;
            b_prime = 0 ;
        }if( 120 <= h && h <180 ){
            Log.d("Convert","3");
            r_prime = 0 ;
            g_prime = c ;
            b_prime = x ;
        }if( 180 <= h && h <240 ){
            Log.d("Convert","4");
            r_prime = 0 ;
            g_prime = x ;
            b_prime = c ;
        }if( 240 <= h && h <300 ){
            Log.d("Convert","5");
            r_prime = x ;
            g_prime = 0 ;
            b_prime = c ;
        }if( 300 <= h && h <360 ){
            Log.d("Convert","6");
            r_prime = c ;
            g_prime = 0 ;
            b_prime = x ;
        }

        TextView rgbtext = (TextView) findViewById(R.id.textViewRGB);
        Log.d("Convert rgb prime"," "+r_prime+" "+g_prime+" "+b_prime);
        rgbtext.setText("R : " + (r_prime + m) * 255 + " ; G : " + (g_prime + m) * 255 + " ; B : " + (b_prime + m) * 255);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonConvert:
                convert();

        }
    }
}
