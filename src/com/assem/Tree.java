package com.assem;

import java.util.Stack;

public class Tree {
    private Node root; // first node of tree
    private int numNodes;
    private int n1;

    public Tree() {
        root=null; // no nodes in tree yet
    }
    public int getNodes(){
        return numNodes;
    }
    public int getHeight(){
        return ((getNodes()+1)/2)-2;
    }
    public int depthBST(int key){
        find(key);
        return n1;
    }
    public Node find(int key){
        if(root==null){
            return null;
        }
        Node current=root;

        while(current.data!=key) {
            if (key < current.data) { // go left
                current = current.leftChild;
                n1++;
            } else {   // or go right
                current = current.rightChild;
                n1++;
            }
            if(current==null){
                return null;
            }
        }
            return current;//found it

    }
    public void insert(int id){
        Node newNode=new Node(id);

        if(root==null){
            root=newNode;
            numNodes=1;
        }
        else{
            Node current=root;
            Node parent;        //parent must be null here!!! otherwise wont insert!!

            while(true){// exits internally
                parent=current;
                if(id<current.data){// go left
                    current=current.leftChild;
                    if(current==null){// if end of the line, insert on left
                        parent.leftChild=newNode;
                        numNodes++;
                        return;
                    }
                }
                else{
                    current=current.rightChild;
                    if(current==null){// if end of the line, insert on right
                        parent.rightChild=newNode;
                        numNodes++;
                        return;
                    }
                }
                //end else go right
            }//end while
        }

    }
    public boolean delete(int key){
        if(root==null){
            System.out.println("There are no elements");
            return false;
        }
        else{
            Node current=root;
            Node parent=root;
            boolean isLeftChild=true;

            while(current.data!=key){// search for node
                parent=current;

                if(key<current.data){// go left?
                    isLeftChild=true;
                    current=current.leftChild;
                }
                else{
                    isLeftChild=false;
                    current=current.rightChild;
                }
                if(current==null){
                    return false;
                }
            }//end while
            //found node to delete

            if(current.leftChild==null&&current.rightChild==null){//if there are no childs of the node that you want to delete
                if(isLeftChild){
                    parent.leftChild=null;//disconnect from parent
                }
                else{
                    parent.rightChild=null;
                }
            }
            //if no right child, replace with left subtree
            else if(current.leftChild==null){
                if(current==root){
                    root=current.rightChild;
                }
                else if(isLeftChild){
                    parent.leftChild=current.rightChild;
                }
                else{
                    parent.rightChild=current.rightChild;
                }
            }
            else{ // two children, so replace with inorder successor
                // get successor of node to delete (current)
                Node successor=getSuccessor(current);

                //connect parent of current to successor instead

                if(current==root){
                    root=successor;
                }
                else if(isLeftChild){
                    parent.leftChild=successor;
                }
                else{
                    parent.rightChild=successor;
                }

                // connect successor to current's left child
                successor.leftChild=current.leftChild;
            }

        }// end else two children
        // (successor cannot have a left child)
        return true;
    }


    // returns node with next-highest value after delNode
    // goes to right child, then right child's left descendents

    private Node getSuccessor(Node delNode){
        Node successorParent=delNode;
        Node successor=delNode;
        Node current =delNode.rightChild;

        while(current!=null){//go to right child until no more left child
            successorParent=successor;
            successor=current;
            current=current.leftChild;   // go to left child

        }
        if(successor!=delNode.rightChild){//if successor not right child, make connections
            successorParent.leftChild=successor.rightChild;
            successor.rightChild=delNode.rightChild;
        }
        return successor;
    }
    public void traverse(int traverseType)
    {
        switch(traverseType)
        {
            case 1: System.out.print("\nPreorder traversal: ");
                preOrder(root);
                break;
            case 2: System.out.print("\nInorder traversal:  ");
                inOrder(root);
                break;
            case 3: System.out.print("\nPostorder traversal: ");
                postOrder(root);
                break;
        }
        System.out.println();
    }
    private void preOrder(Node localRoot)
    {
        if(localRoot != null)
        {
            System.out.print(localRoot.data + " ");
            preOrder(localRoot.leftChild);
            preOrder(localRoot.rightChild);
        }
    }
    private void inOrder(Node localRoot)
    {
        if(localRoot != null)
        {
            inOrder(localRoot.leftChild);
            System.out.print(localRoot.data + " ");
            inOrder(localRoot.rightChild);
        }
    }
    private void postOrder(Node localRoot)
    {
        if(localRoot != null)
        {
            postOrder(localRoot.leftChild);
            postOrder(localRoot.rightChild);
            System.out.print(localRoot.data + " ");
        }
    }
    public void displayTree()
    {
        Stack globalStack = new Stack();
        globalStack.push(root);
        int nBlanks = 32;
        boolean isRowEmpty = false;
        System.out.println(
                "......................................................");
        while(isRowEmpty==false)
        {
            Stack localStack = new Stack();
            isRowEmpty = true;

            for(int j=0; j<nBlanks; j++)
                System.out.print(' ');

            while(globalStack.isEmpty()==false)
            {
                Node temp = (Node)globalStack.pop();
                if(temp != null)
                {
                    System.out.print(temp.data);
                    localStack.push(temp.leftChild);
                    localStack.push(temp.rightChild);

                    if(temp.leftChild != null ||
                            temp.rightChild != null)
                        isRowEmpty = false;
                }
                else
                {
                    System.out.print("--");
                    localStack.push(null);
                    localStack.push(null);
                }
                for(int j=0; j<nBlanks*2-2; j++)
                    System.out.print(' ');
            }  // end while globalStack not empty
            System.out.println();
            nBlanks /= 2;
            while(localStack.isEmpty()==false)
                globalStack.push( localStack.pop() );
        }  // end while isRowEmpty is false
        System.out.println(
                "......................................................");
    }  // end displayTree()
}
