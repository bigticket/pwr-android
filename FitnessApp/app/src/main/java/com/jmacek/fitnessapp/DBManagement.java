package com.jmacek.fitnessapp;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBManagement extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Map<String,String> trainingData;
    DatabaseHandler db;
    Boolean customTraining = false;

    EditText txtSport, txtDate, txtTime, txtDistance, txtMaxSpeed, txtHR, txtCalories;
    Button btnCustom, btnAddCustom, btnLoad, btnDelete;
    Spinner spLoad, spDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dbmanagement);
        db = new DatabaseHandler(this);
        final List<String> dates = db.getDates();

        spLoad = (Spinner)findViewById(R.id.selectToLoad);
        spDelete = (Spinner)findViewById(R.id.selectToDelete);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, dates);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spLoad.setAdapter(dataAdapter);
        spDelete.setAdapter(dataAdapter);

        spLoad.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spDelete.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        txtSport = (EditText)findViewById(R.id.txtSport);
        txtDate = (EditText)findViewById(R.id.txtDate);
        txtTime = (EditText)findViewById(R.id.txtTime);
        txtDistance = (EditText)findViewById(R.id.txtDistance);
        txtMaxSpeed = (EditText)findViewById(R.id.txtMaxSpeed);
        txtHR = (EditText)findViewById(R.id.txtHR);
        txtCalories = (EditText)findViewById(R.id.txtCalories);

        btnCustom = (Button)findViewById(R.id.btnVisible);
        btnCustom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtSport.setFocusable(true); txtSport.setText("");
                txtDate.setFocusable(true); txtDate.setText("");
                txtTime.setFocusable(true); txtTime.setText("");
                txtDistance.setFocusable(true); txtDistance.setText("");
                txtMaxSpeed.setFocusable(true); txtMaxSpeed.setText("");
                txtHR.setFocusable(true); txtHR.setText("");
                txtCalories.setFocusable(true); txtCalories.setText("");
                customTraining = true;
                Toast.makeText(DBManagement.this, "Data fields unlocked!", Toast.LENGTH_LONG).show();
            }
        });

        btnAddCustom = (Button)findViewById(R.id.btnAddData);
        btnAddCustom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (customTraining == true){
                    trainingData = new HashMap<String, String> ();
                    trainingData.put("sport", txtSport.getText().toString());
                    trainingData.put("date", txtDate.getText().toString());
                    trainingData.put("time", txtTime.getText().toString());
                    trainingData.put("distance", txtDistance.getText().toString());
                    trainingData.put("max_speed", txtMaxSpeed.getText().toString());
                    trainingData.put("heart_rate", txtHR.getText().toString());
                    trainingData.put("calories", txtCalories.getText().toString());

                    boolean isInserted = db.insertData(trainingData);
                    if (isInserted == true){
                        Toast.makeText(DBManagement.this, "Training added!", Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(DBManagement.this, "Oops, something went wrong!", Toast.LENGTH_LONG).show();
                    }
                }
                else{
                    ArrayList<String> dates =  db.getDates();
                    System.out.println(dates);
                    Toast.makeText(DBManagement.this,"Enable custom training first!", Toast.LENGTH_LONG).show();
                }

            }
        });

        btnLoad = (Button)findViewById(R.id.btnLoad);
        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = db.getSelectedRow(spLoad.getSelectedItem().toString());
                while(res.moveToNext()){
                    txtSport.setText(res.getString(1));
                    txtDate.setText(res.getString(2));
                    txtDistance.setText(res.getString(3));
                    txtTime.setText(res.getString(4));
                    txtMaxSpeed.setText(res.getString(5));
                    txtHR.setText(res.getString(6));
                    txtCalories.setText(res.getString(7));
                }
            }
        });

        btnDelete = (Button)findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date = spDelete.getSelectedItem().toString();
                Integer delete = db.deleteSelectedRow(date);
                if (delete > 0){
                    Toast.makeText(DBManagement.this, "Training deleted!", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(DBManagement.this, "Ooops, something went wrong!", Toast.LENGTH_LONG).show();
                }
                dates.remove(date);
                System.out.println(dates);

            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
