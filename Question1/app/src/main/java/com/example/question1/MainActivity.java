package com.example.question1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;


import com.example.question1.adapter.Adapter;
import com.example.question1.model.Fruit;
import com.example.question1.service.HTTPService;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final List<Fruit> listFruits = listAllFruits();
        final ListView fruitListView = (ListView) findViewById(R.id.quantity_card_fruits);
        final SearchView searchView = (SearchView) findViewById((R.id.searchView));
        final Adapter adapter = new Adapter(listFruits, this);
        final Activity main = this;
        fruitListView.setAdapter(adapter);
        
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                List<Fruit> filteredFruitList = findFruit(newText, listFruits);
                if (newText.length() == 0) {
                    fruitListView.setAdapter(adapter);
                    return false;
                }
                fruitListView.setAdapter(new Adapter(filteredFruitList, main));
                return true;
            }

        });

        fruitListView.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Fruit fruit = listFruits.get(position);
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("fruit", fruit);
                startActivity(intent);
            }
        });
    }


    private List<Fruit> listAllFruits() {
        HTTPService service = new HTTPService();
        List<Fruit> aux = new ArrayList();
        try {
            aux = service.execute().get();
            System.out.println("aux: "+aux);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return aux;
    }

    List<Fruit> findFruit(String fruitName, List<Fruit> listFruits) {
        List<Fruit> filteredFruitList = new ArrayList();
        if(fruitName.length() > 0) {
            for(Fruit fruit : listFruits) {
                if(fruit.getTfvname().contains(fruitName)) {
                    filteredFruitList.add(fruit);
                }
            }
        }
        return filteredFruitList;
    }
}
