package com.example.question2_linkedlist.model;

import java.io.Serializable;

public class LinkedList {
    Node head;
    private int listSize = 0;
    
    public void insert(Object data)
    {
        Node node=new Node(data,null);
        if(head==null)
        {
            head=node;
        }
        else
        {
            Node n=head;
            while(n.getNext()!=null)
            {
                n=n.getNext();
            }
            n.setNext(node);
        }
        this.listSize++;
    }

    public void insertAtStart(Object data)
    {
        Node node =new Node(data,null);
        node.setNext(head);
        head=node;
        this.listSize++;
    }

    public void insertAt(int index,Object data)
    {
        if (index==0)
        {
            insertAtStart(data);
        }
        else
        {
            Node node=new Node(data,null);
            Node n=head;
            for(int i=0;i<index-1;i++)
            {
                n=n.getNext();
            }
            node.setNext(n.getNext());
            n.setNext(node);
        }
        this.listSize++;
    }

    public void deleteAt(int index)
    {
        if (index==0)
        {
            head=head.getNext();
        }
        else
        {
            Node n=head;
            Node n1=null;
            for(int i=0;i<index-1;i++)
            {
                n=n.getNext();
            }
            n1=n.getNext();
            n.setNext(n1.getNext());
        }
        this.listSize--;
    }

    public Node getNode(int index)
    {
        Node n=head;
        if (index>0)
        {
            for(int i=0;i<index;i++)
            {
                n=n.getNext();
            }
        }

        return n;
    }

    public void show()
    {
        Node n=head;
        while(n.getNext()!=null)
        {
            System.out.println(n.getData() );
            n=n.getNext();
        }
        System.out.println(n.getData());
    }
    
    public int getSize() {
        return this.listSize;
    }

}