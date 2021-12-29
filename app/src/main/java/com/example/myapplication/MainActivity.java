package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Intent samintent;
    private Button startbtn,stopbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        samintent=new Intent(MainActivity.this,samservice.class); //使用Intent，由MainActivity對SamService
        startbtn=(Button)findViewById(R.id.startbtn);
        stopbtn=(Button)findViewById(R.id.stopbtn);
        startbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startService(samintent);
            }
        });
        stopbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                stopService(samintent);
            }
        });
    }
}
