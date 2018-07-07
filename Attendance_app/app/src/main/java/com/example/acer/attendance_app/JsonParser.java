package com.example.acer.attendance_app;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.example.acer.attendance_app.Configuration.KEY_COUNTRY;
import static com.example.acer.attendance_app.Configuration.KEY_NAME;
import static com.example.acer.attendance_app.Configuration.KEY_USERS;

public class JsonParser {
    public static String[] name;
    public static String[] country;
    public static String[] id;


    private JSONArray users = null;

    private String json;

    public JsonParser(String json){
        this.json = json;
    }

    protected void parseJSON(){
        JSONObject jsonObject=null;
        try {
            jsonObject = new JSONObject(json);
            users = jsonObject.getJSONArray(KEY_USERS);

            name = new String[users.length()];
            country = new String[users.length()];

            for(int i=0;i<users.length();i++){
                JSONObject jo = users.getJSONObject(i);
                name[i] = jo.getString(KEY_NAME);
                country[i] = jo.getString(KEY_COUNTRY);

            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
