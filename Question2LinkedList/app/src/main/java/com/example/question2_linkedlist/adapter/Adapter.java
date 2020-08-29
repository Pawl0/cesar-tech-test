package com.example.question2_linkedlist.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.question2_linkedlist.model.LinkedList;
import com.example.question2_linkedlist.model.Node;
import com.example.question2_linkedlist.R;

import java.util.List;

public class Adapter extends BaseAdapter {
    private final LinkedList nodes;
    private final Activity act;

    public Adapter(LinkedList nodes, Activity act) {
        this.nodes = nodes;
        this.act = act;
    }

    @Override
    public int getCount() {
        return nodes.getSize();
    }

    @Override
    public Object getItem(int position) { return nodes.getNode(position); }

    @Override
    public long getItemId(int position) { return 0; }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = act.getLayoutInflater().inflate(R.layout.item_node,parent,false);
        Node node = nodes.getNode(position);

        TextView name = (TextView)view.findViewById(R.id.name_item);

        name.setText((String)node.getData());

        return view;
    }
}
