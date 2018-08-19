package com.assem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException{
        Tree theTree = new Tree();

        theTree.insert(50);
        theTree.insert(25);
        theTree.insert(75);
        theTree.insert(12);
        theTree.insert(37);
        theTree.insert(43);
        theTree.insert(30);
        theTree.insert(33);
        theTree.insert(87);
        theTree.insert(93);
        theTree.insert(97);
        System.out.println("The number of nodes: "+theTree.getNodes());
        System.out.println("The levels: "+theTree.getHeight());



        boolean quit=true;

        while(quit)
        {
            System.out.print("Enter first letter of\n" +
                    " (Q)uit, (S)how,(I)nsert,(F)ind,(D)elete,(T)raverse, or to find the level of a particular value enter (L): ");

            int choice = getChar();
            int value;
            switch(choice)
            {
                case 'q':
                    quit=false;
                    break;
                case 's':
                    theTree.displayTree();
                    break;
                case 'i':
                    System.out.print("Enter value to insert: ");
                    value = getInt();
                    theTree.insert(value);
                    break;
                case'l':
                    System.out.print("Enter value to find the level:");
                    value = getInt();
                    Node a1=theTree.find(value);
                    if(a1!=null)
                        System.out.println("The level of the value is:"+(theTree.depthBST(value))/2);
                    else
                        System.out.println("This number doesn't exist!!");
                    break;
                case 'f':
                    System.out.print("Enter value to find: ");
                    value = getInt();
                    Node found = theTree.find(value);
                    if(found != null)
                    {
                        System.out.print("Found: ");
                        found.displayNode();
                        System.out.print("\n");
                    }
                    else
                        System.out.print("Could not find "+value+'\n');
                    break;
                case 'd':
                    System.out.print("Enter value to delete: ");
                    value = getInt();
                    boolean didDelete = theTree.delete(value);
                    if(didDelete)
                        System.out.print(value+" Deleted "+ '\n');
                    else
                        System.out.print(value+" Doesnt exist\n");
                    break;
                case 't':
                    System.out.print("Enter 1(for Preorder), 2(for InOrder) or 3(for PostOrder): ");
                    value = getInt();
                    theTree.traverse(value);
                    break;
                default:
                    System.out.print("Invalid entry\n");

            }  // end switch

        }  // end while
    }  // end main()
    // -------------------------------------------------------------
    public static String getString() throws IOException
    {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String s = br.readLine();
        return s;
    }
    // -------------------------------------------------------------
    public static char getChar() throws IOException
    {
        String s = getString();
        return s.charAt(0);
    }
    //-------------------------------------------------------------
    public static int getInt() throws IOException
    {
        String s = getString();
        return Integer.parseInt(s);
    }
}
