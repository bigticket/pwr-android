package com.jmacek.fitnessapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonReader;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

public class DataFetching extends AppCompatActivity {

    public static Button btnGet;
    public static Button btnSave;
    public static EditText txtURL;
    public static TextView txtFetched;

    Map<String,String> trainingData;
    DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_fetching);
        db = new DatabaseHandler(this);

        txtFetched = (TextView)findViewById(R.id.fetchedData);
        txtURL = (EditText)findViewById(R.id.txtUrl);
        txtURL.setText("http://www.json-generator.com/api/json/get/");
        btnGet = (Button)findViewById(R.id.btnGetData);
        btnGet.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtURL.getText().toString().length() != 0){
                    String url = txtURL.getText().toString();
                    RequestHandler handler = new RequestHandler(url);
                    handler.execute();
                }
                else{
                    Toast.makeText(DataFetching.this,"No URL provided!",Toast.LENGTH_LONG).show();
                    return;
                }
            }
        });
        btnSave = (Button)findViewById(R.id.btnSaveData);
        btnSave.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtFetched.getText().toString().length() != 0){
                    try {
                        String data = txtFetched.getText().toString();
                        JSONObject obj = new JSONObject(data);
                        System.out.println(data);
                        trainingData = new HashMap<String, String> ();
                        trainingData.put("sport", obj.getString("discipline"));
                        trainingData.put("date", obj.getString("date"));
                        trainingData.put("time", obj.getString("time"));
                        trainingData.put("distance", obj.getString("distance"));
                        trainingData.put("max_speed", obj.getString("max_speed"));
                        trainingData.put("heart_rate", obj.getString("heart_rate"));
                        trainingData.put("calories", obj.getString("calories"));

                        boolean isInserted = db.insertData(trainingData);
                        if (isInserted == true){
                            Toast.makeText(DataFetching.this, "Training added!", Toast.LENGTH_LONG).show();
                        }
                        else{
                            Toast.makeText(DataFetching.this, "Oops, something went wrong!", Toast.LENGTH_LONG).show();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
                else{
                    Toast.makeText(DataFetching.this, "No data to save!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
