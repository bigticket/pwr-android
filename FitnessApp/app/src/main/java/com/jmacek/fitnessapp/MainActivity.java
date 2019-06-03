package com.jmacek.fitnessapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    DatabaseHandler db;
    Button btnDBManager, btnGetData, btnGraphics;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DatabaseHandler(this);

        btnGetData = (Button)findViewById(R.id.btnData);
        btnGetData.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int1 = new Intent(getApplicationContext(), DataFetching.class);
                startActivity(int1);
            }
        });
        btnDBManager = (Button) findViewById(R.id.btnDB);
        btnDBManager.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int2 = new Intent(getApplicationContext(), DBManagement.class);
                startActivity(int2);
            }
        });
        btnGraphics = (Button)findViewById(R.id.btnView);
        btnGraphics.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int3 = new Intent(getApplicationContext(), DataDisplay.class);
                startActivity(int3);
            }
        });
    }
}
