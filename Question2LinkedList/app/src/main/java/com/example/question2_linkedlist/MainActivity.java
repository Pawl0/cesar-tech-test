package com.example.question2_linkedlist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.question2_linkedlist.adapter.Adapter;
import com.example.question2_linkedlist.model.LinkedList;
import com.example.question2_linkedlist.model.Node;
import com.example.question2_linkedlist.service.HTTPService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final List<Node> listNodes = listAllNodes();
        final ListView nodeListView = (ListView) findViewById(R.id.node_list);
        final Adapter adapter = new Adapter(convertList(listNodes), this);
        nodeListView.setAdapter(adapter);

    }

    private List<Node> listAllNodes() {
        HTTPService service = new HTTPService();
        List<Node> aux = new ArrayList();
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

    private LinkedList convertList(List<Node> list) {
        LinkedList linkedList = new LinkedList();
        int listSize = list.size();
        for (int i = 0; i < listSize; i++) {
            linkedList.insert(list.get(i).getData());
        }
        return linkedList;
    }
}