package com.example.question2_linkedlist.service;

import android.os.AsyncTask;
import android.util.Log;

import com.example.question2_linkedlist.model.LinkedList;
import com.example.question2_linkedlist.model.Node;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class HTTPService  extends AsyncTask<Void,Void, List<Node>> {

    private String URL = "https://randommer.io/api/Name?nameType=firstname&quantity=100";
    private String API_KEY = "3cbe4de45d3947c0b49d52a2ebabda31";

    @Override
    protected List<Node> doInBackground(Void... voids) {
        StringBuilder answer = new StringBuilder();
        try {
            java.net.URL url = new URL(URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("x-api-key",API_KEY);
            connection.setConnectTimeout(10000);
            connection.connect();
            InputStream inputStream;
            int status = connection.getResponseCode();
            String response = connection.getResponseMessage();
            Object content = connection.getContent();

            if (status != HttpURLConnection.HTTP_OK)  {
                inputStream = connection.getErrorStream();
            }
            else  {
                inputStream = connection.getInputStream();
                Scanner scanner = new Scanner(inputStream);
                while (scanner.hasNext()) {
                    answer.append(scanner.next());
                }
            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Type itemListType = new TypeToken<ArrayList<Node>>() {
        }.getType();


        String filteredAnswer = answer.toString().replaceAll("[\\[\\](){}\"]", "");
        List<String> myList = new ArrayList<String>(Arrays.asList(filteredAnswer.split(",")));
        System.out.println("myList: "+myList);

        return convertList(myList);
    }

    List<Node> convertList(List<String> stringList) {
        List<Node> nodeList = new ArrayList<Node>();
        int listSize = stringList.size();
        for (int i = 0; i < listSize; i++) {
            nodeList.add(new Node(stringList.get(i),null));
        }
        return nodeList;
    }
}
