package com.example.question1;

import android.content.Intent;
import android.os.Bundle;

import com.example.question1.model.Fruit;
import com.example.question1.service.HTTPService;
import com.squareup.picasso.Picasso;

import androidx.appcompat.app.AppCompatActivity;

import android.text.Html;
import android.text.Spanned;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent i = getIntent();
        Fruit fruit =(Fruit) i.getSerializableExtra("fruit");

        ImageView imageFruitDetail = (ImageView)findViewById(R.id.image_fruit_detail);
        TextView nameFruitDetail = (TextView)findViewById(R.id.name_fruit_detail);
        TextView botNameFruitDetail =(TextView)findViewById(R.id.botname_fruit_detail);
        TextView priceFruitDetail =(TextView)findViewById(R.id.othernames_fruit_detail);
        TextView descriptionDetail = (TextView)findViewById(R.id.description_fruit_detail);

        Spanned texto = Html.fromHtml("<small>"+fruit.getDescription()+"</small><br/>");
        descriptionDetail.setText(texto);

        Picasso.get().load(fruit.getImageurl()).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(imageFruitDetail,new com.squareup.picasso.Callback(){@Override public void onSuccess(){} @Override public void onError(Exception e) {}});

        List<Fruit> fruitListByName = getFruitByName(fruit.getTfvname());
        System.out.println("fruitListByName: "+fruitListByName.get(0));
        nameFruitDetail.setText(fruit.getTfvname());
        botNameFruitDetail.setText(fruit.getBotname());
        priceFruitDetail.setText(fruit.getOthname());
        descriptionDetail.setText(fruitListByName.get(0).getDescription());
    }


    private List<Fruit> getFruitByName(String fruitName) {
        HTTPService service = new HTTPService(fruitName);
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
}
