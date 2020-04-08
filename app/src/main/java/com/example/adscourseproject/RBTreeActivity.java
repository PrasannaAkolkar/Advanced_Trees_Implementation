package com.example.adscourseproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


public class RBTreeActivity extends AppCompatActivity {


    Button rbInsert;
    Button rbDelete;
    Button rbSearch;
    Button rbCount;
    Button clearTree;
    Button diplayRbTree;

    EditText rbEditInsert;
    EditText rbEditDelete;
    EditText rbEditSearch;
    TextView rbCountDisplay;
    TextView rbSearchDisplay;


    TextView RbPreorderTv;
    TextView RbInorder;
    TextView RbPostorder;

    RBTree rbt = new RBTree(Integer.MIN_VALUE);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rbtree);



        rbInsert = findViewById(R.id.rbInsert);

        rbSearch = findViewById(R.id.rbSearch);
        rbCount = findViewById(R.id.rbCount);
        clearTree = findViewById(R.id.rbClearTree);
        diplayRbTree = findViewById(R.id.RbDisplay);


        rbEditInsert = findViewById(R.id.rbEditInsert);

        rbEditSearch = findViewById(R.id.rbEditSearch);
        rbCountDisplay = findViewById(R.id.rbTextCount);
        rbSearchDisplay = findViewById(R.id.dispSearchRbRes);

        RbPreorderTv = findViewById(R.id.RbPreorder);
        RbInorder = findViewById(R.id.RbInorder);
        RbPostorder = findViewById(R.id.RbPostorder);


    }




class RedBlackNode

{

    RedBlackNode left, right;

    int element;

    int color;



    /* Constructor */

    public RedBlackNode(int theElement)

    {

        this( theElement, null, null );

    }

    /* Constructor */

    public RedBlackNode(int theElement, RedBlackNode lt, RedBlackNode rt)

    {

        left = lt;

        right = rt;

        element = theElement;

        color = 1;

    }

}


class RBTree

{

    private RedBlackNode current;

    private RedBlackNode parent;

    private RedBlackNode grand;

    private RedBlackNode great;

    private RedBlackNode header;

    private RedBlackNode nullNode;

    /* static initializer for nullNode */

    {

        nullNode = new RedBlackNode(0);

        nullNode.left = nullNode;

        nullNode.right = nullNode;

    }

    /* Black - 1  RED - 0 */

    static final int BLACK = 1;

    static final int RED   = 0;



    /* Constructor */

    public RBTree(int negInf)

    {

        header = new RedBlackNode(negInf);

        header.left = nullNode;

        header.right = nullNode;

    }

    /* Function to check if tree is empty */

    public boolean isEmpty()

    {

        return header.right == nullNode;

    }

    /* Make the tree logically empty */

    public void makeEmpty()

    {

        header.right = nullNode;

    }

    /* Function to insert item */

    public void insert(int item )

    {

        current = parent = grand = header;

        nullNode.element = item;

        while (current.element != item)

        {

            great = grand;

            grand = parent;

            parent = current;

            current = item < current.element ? current.left : current.right;

            // Check if two red children and fix if so

            if (current.left.color == RED && current.right.color == RED)

                handleReorient( item );

        }

        // Insertion fails if already present

        if (current != nullNode)

            return;

        current = new RedBlackNode(item, nullNode, nullNode);

        // Attach to parent

        if (item < parent.element)

            parent.left = current;

        else

            parent.right = current;

        handleReorient( item );

    }

    private void handleReorient(int item)

    {

        // Do the color flip

        current.color = RED;

        current.left.color = BLACK;

        current.right.color = BLACK;



        if (parent.color == RED)

        {

            // Have to rotate

            grand.color = RED;

            if (item < grand.element != item < parent.element)

                parent = rotate( item, grand );  // Start dbl rotate

            current = rotate(item, great );

            current.color = BLACK;

        }

        // Make root black

        header.right.color = BLACK;

    }

    private RedBlackNode rotate(int item, RedBlackNode parent)

    {

        if(item < parent.element)

            return parent.left = item < parent.left.element ? rotateWithLeftChild(parent.left) : rotateWithRightChild(parent.left) ;

        else

            return parent.right = item < parent.right.element ? rotateWithLeftChild(parent.right) : rotateWithRightChild(parent.right);

    }

    /* Rotate binary tree node with left child */

    private RedBlackNode rotateWithLeftChild(RedBlackNode k2)

    {

        RedBlackNode k1 = k2.left;

        k2.left = k1.right;

        k1.right = k2;

        return k1;

    }

    /* Rotate binary tree node with right child */

    private RedBlackNode rotateWithRightChild(RedBlackNode k1)

    {

        RedBlackNode k2 = k1.right;

        k1.right = k2.left;

        k2.left = k1;

        return k2;

    }

    /* Functions to count number of nodes */

    public int countNodes()

    {

        return countNodes(header.right);

    }

    private int countNodes(RedBlackNode r)

    {

        if (r == nullNode)

            return 0;

        else

        {

            int l = 1;

            l += countNodes(r.left);

            l += countNodes(r.right);

            return l;

        }

    }

    /* Functions to search for an element */

    public boolean search(int val)

    {

        return search(header.right, val);

    }

    private boolean search(RedBlackNode r, int val)

    {

        boolean found = false;

        while ((r != nullNode) && !found)

        {

            int rval = r.element;

            if (val < rval)

                r = r.left;

            else if (val > rval)

                r = r.right;

            else

            {

                found = true;

                break;

            }

            found = search(r, val);

        }

        return found;

    }

    /* Function for inorder traversal */

    public void inorder()

    {

        inorder(header.right);

    }

    private void inorder(RedBlackNode r)

    {

        if (r != nullNode)

        {

            inorder(r.left);

            char c = 'B';

            if (r.color == 0)

                c = 'R';

            System.out.print(r.element +""+c+" ");

            RbInorder.append(r.element+"("+c+")");

            inorder(r.right);

        }

    }

    /* Function for preorder traversal */

    public void preorder()

    {

        preorder(header.right);

    }

    private void preorder(RedBlackNode r)

    {


        if (r != nullNode)

        {

            char c = 'B';

            if (r.color == 0)

                c = 'R';

            System.out.print(r.element +""+c+" ");

            RbPreorderTv.append(r.element+"("+c+")");


            preorder(r.left);

            preorder(r.right);

        }


    }

    /* Function for postorder traversal */

    public void postorder()

    {

        postorder(header.right);

    }

    private void postorder(RedBlackNode r)

    {


        if (r != nullNode)

        {

            postorder(r.left);

            postorder(r.right);

            char c = 'B';

            if (r.color == 0)

                c = 'R';

            System.out.print(r.element +""+c+" ");
            RbPostorder.append(r.element+"("+c+")");

        }

    }

}





    public void InsertFunction(View view) {

        String val = rbEditInsert.getText().toString();

        if(!val.isEmpty()) {

            int valNum = Integer.parseInt(val);
            rbt.insert(valNum);
            Toast.makeText(this, val + " Value Successfully Inserted", Toast.LENGTH_SHORT).show();
            rbEditInsert.setText("");
        }
        else
        {
            Toast.makeText(this, "Null values can't be inserted", Toast.LENGTH_SHORT).show();
        }



    }

    public void CountFunction(View view) {

        int countNodes = rbt.countNodes();
        String valCountNodes = String.valueOf(countNodes);

        rbCountDisplay.setText(valCountNodes);

    }


    public void SearchRBTreeFunction(View view) {

        String val = rbEditSearch.getText().toString();
        boolean flag=false;

        if(!val.isEmpty()) {

            int valNum = Integer.parseInt(val);
            flag = rbt.search(valNum);
            if(flag==true)
                rbSearchDisplay.setText("Element found in the tree");
            else
                rbSearchDisplay.setText("Element not found in the rb tree");
        }
        else
        {
            Toast.makeText(this,"Null Value cannot be searched",Toast.LENGTH_SHORT).show();
        }


    }


    public void ClearRbTreeFunction(View view) {

        int countNodes = rbt.countNodes();
        if(countNodes!=0)
        {
            rbt.makeEmpty();
            Toast.makeText(this, "RB Tree Cleared", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "Tree already empty. Please enter some elements", Toast.LENGTH_SHORT).show();
        }


    }


    public void DisplayRBTree(View view) {

        RbPreorderTv.setText("Preorder->");
        RbPostorder.setText("PostOrder->");
        RbInorder.setText("Inorder->");
        rbt.preorder();
        rbt.inorder();
        rbt.postorder();


    }
}
































































































