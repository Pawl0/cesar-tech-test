package com.example.question1.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.question1.R;
import com.example.question1.model.Fruit;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.List;

public class Adapter extends BaseAdapter {
    private final List<Fruit> fruits;
    private final Activity act;

    public Adapter(List<Fruit> fruits, Activity act) {
        this.fruits = fruits;
        this.act = act;
    }

    @Override
    public int getCount() {
        return fruits.size();
    }

    @Override
    public Object getItem(int position) { return fruits.get(position); }

    @Override
    public long getItemId(int position) { return 0; }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        System.out.println("Adapter View start");
        View view = act.getLayoutInflater().inflate(R.layout.item_fruit,parent,false);
        Fruit fruit = fruits.get(position);

        TextView name = (TextView)view.findViewById(R.id.name_item);

        name.setText(fruit.getTfvname());

//        Picasso.get().load(fruit.getImage()).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher)
//                .into(image,new com.squareup.picasso.Callback(){@Override public void onSuccess(){} @Override public void onError(Exception e) {}});

        return view;
    }
}
