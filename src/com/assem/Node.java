package com.assem;

public class Node {
    Node leftChild;
    Node rightChild;
    int data;
    double dData;

    public Node(int data) {
        this.data = data;
        this.rightChild=null;
        this.leftChild=null;
    }

    public void displayNode()
    {
        System.out.print('{');
        System.out.print(data);
        System.out.print(", ");
        System.out.print(dData);
        System.out.print("} ");
    }
}

