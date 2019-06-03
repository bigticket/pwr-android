package com.jmacek.fitnessapp;

import org.json.JSONException;
import org.json.JSONObject;

public class JSONParser {
    private JSONObject json;

    public JSONParser(JSONObject object) {
        json = object;
    }

    public String getValue(String key) throws JSONException {
        return json.getString(key);
    }
}
