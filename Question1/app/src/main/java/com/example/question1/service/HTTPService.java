package com.example.question1.service;

import android.os.AsyncTask;
import android.util.Log;

import com.example.question1.model.Fruit;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HTTPService extends AsyncTask<Void,Void, List<Fruit>> {

    private String fruitName;
    private String URLBase;

    public HTTPService(String fruitName) {
        this.setFruitName(fruitName);
        this.setURLBase("http://api.tropicalfruitandveg.com/tfvjsonapi.php?tfvitem="+ fruitName.toLowerCase());
    }

    public HTTPService() {
        this.setFruitName("");
        this.setURLBase("http://tropicalfruitandveg.com/api/tfvjsonapi.php?search=all");
    }



    @Override
    protected List<Fruit> doInBackground(Void... voids) {
        StringBuilder answer = new StringBuilder();
        try {
            URL url = new URL(getURLBase());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept","application/json");
            connection.setConnectTimeout(10000);
            connection.connect();

            Scanner scanner = new Scanner(url.openStream());
            while (scanner.hasNext()){
                answer.append(scanner.next());
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Type itemListType = new TypeToken<ArrayList<Fruit>>(){}.getType();


        JSONObject obj = new JSONObject();
        List<Fruit> list = new ArrayList<Fruit>();

        try {
            obj = new JSONObject(answer.toString());
            list = new Gson().fromJson(obj.get("results").toString(),itemListType);
        }catch(Throwable tx){
            Log.e("My App", "Could not parse malformed JSON: \"" + answer + "\"");
        }
        return list;

    }

    public String getFruitName() {
        return fruitName;
    }

    public void setFruitName(String fruitName) {
        this.fruitName = fruitName;
    }

    public String getURLBase() {
        return URLBase;
    }

    public void setURLBase(String URLBase) {
        this.URLBase = URLBase;
    }
}
