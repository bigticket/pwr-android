package com.jmacek.fitnessapp;

import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class RequestHandler extends AsyncTask<Void, Void, Void> {
    private String endpointUrl = "";
    private String data = "";
    private String json_data;

    Map<String,String> trainingData;
    DatabaseHandler db;

    public RequestHandler(String url) {
        endpointUrl = url;
    }

    public void load() throws IOException {
        StringBuilder result = new StringBuilder();

        BufferedReader rd = new BufferedReader(getStream());
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        rd.close();
        data = result.toString();
    }

    public JSONObject getJsonObject() throws JSONException {
        return new JSONObject(data);
    }

    private InputStreamReader getStream() throws IOException {
        URL url;
        url = new URL(endpointUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        return new InputStreamReader(conn.getInputStream());
    }

    public boolean isJSONValid(String test) {
        try {
            new JSONObject(test);
        } catch (JSONException ex) {
            try {
                new JSONArray(test);
            } catch (JSONException ex1) {
                return false;
            }
        }
        return true;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try{
            RequestHandler handler = new RequestHandler(endpointUrl);
            handler.load();
            JSONObject obj = handler.getJsonObject();
            json_data = obj.toString();
            System.out.println(json_data);
            data =  "Sport: " + obj.getString("discipline") + "\n"+
                    "Date: " + obj.getString("date") + "\n"+
                    "Distance(km): " + obj.getString("distance") + "\n"+
                    "Time: " + obj.getString("time") + "\n"+
                    "Max Speed(km/h): " + obj.getString("max_speed") + "\n"+
                    "Heart Rate: " + obj.getString("heart_rate") + "\n"+
                    "Calories(kcal): " + obj.getString("calories") + "\n";
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        DataFetching.txtFetched.setText(this.json_data);
    }
}
