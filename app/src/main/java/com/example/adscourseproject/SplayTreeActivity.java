package com.example.adscourseproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class SplayTreeActivity extends AppCompatActivity {

    Button SInsert;
    Button SDelete;
    Button SSearch;
    Button SCount;
    Button SclearTree;
    Button displaySTree;

    EditText SEditInsert;
    EditText SEditDelete;
    EditText SEditSearch;
    TextView SCountDisplay;
    TextView SSearchDisplay;


    public TextView SPreorderTv;
    TextView SInorder;
    TextView SPostorder;

    SplayTree spt = new SplayTree();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splay_tree);


        SInsert = findViewById(R.id.SInsert);
        SDelete = findViewById(R.id.SDelete);
        SSearch = findViewById(R.id.SSearch);
        SCount = findViewById(R.id.SCount);
        SclearTree = findViewById(R.id.SClearTree);
        displaySTree = findViewById(R.id.SDisplay);

        SEditDelete = findViewById(R.id.SDeleteET);
        SEditInsert = findViewById(R.id.SEditInsert);
        SEditSearch =  findViewById(R.id.SEditSearch);
        SCountDisplay = findViewById(R.id.STextCount);
        SSearchDisplay = findViewById(R.id.dispSearchSRes);

        SPreorderTv = findViewById(R.id.SPreorder);
        SInorder = findViewById(R.id.SInorder);
        SPostorder = findViewById(R.id.SPostorder);


    }

    public void InsertSFunction(View view) {

        String s = SEditInsert.getEditableText().toString();

        if(!(s.isEmpty()))
        {
            int val = Integer.parseInt(s);
            spt.insert(val);
            Toast.makeText(this, "Value Inserted Successfully", Toast.LENGTH_SHORT).show();
            SEditInsert.setText("");
        }
        else
        {
            Toast.makeText(this, "Please Insert A Value", Toast.LENGTH_SHORT).show();
            Log.i("Reached else","yes");
        }



    }

    public void SearchSTreeFunction(View view) {


            String val = SEditSearch.getText().toString();
            boolean flag=false;

            if(!val.isEmpty()) {

                int valNum = Integer.parseInt(val);
                flag = spt.search(valNum);
                if(flag==true)
                    SSearchDisplay.setText(val+" Found in Splay");
                else
                    SSearchDisplay.setText(val+" Not Found in Splay");
            }
            else
            {
                Toast.makeText(this,"Null Value cannot be searched",Toast.LENGTH_SHORT).show();
            }


    }

    public void CountSFunction(View view) {

        int countNodes = spt.countNodes();
        String valCountNodes = String.valueOf(countNodes);

        SCountDisplay.setText(valCountNodes);

    }

    public void ClearSTreeFunction(View view) {


        int countNodes = spt.countNodes();
        if(countNodes!=0)
        {
            spt.clear();
            Toast.makeText(this, "RB Tree Cleared", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "Tree already empty. Please enter some elements", Toast.LENGTH_SHORT).show();
        }

    }

    public void DeleteSFunction(View view) {

        String str = SEditDelete.getText().toString();

        if(!str.isEmpty())
        {
            int val = Integer.valueOf(str);
            spt.remove(val);
            Toast.makeText(this, val+" Successfully Deleted", Toast.LENGTH_SHORT).show();
            SEditDelete.setText("");
        }
        else
        {
            Toast.makeText(this, "Please enter something to delete", Toast.LENGTH_SHORT).show();
        }


    }









class SplayNode

{

    SplayNode left, right, parent;

    int element;





    public SplayNode()

    {

        this(0, null, null, null);

    }

    /** Constructor **/

    public SplayNode(int ele)

    {

        this(ele, null, null, null);

    }

    /** Constructor **/

    public SplayNode(int ele, SplayNode left, SplayNode right, SplayNode parent)

    {

        this.left = left;

        this.right = right;

        this.parent = parent;

        this.element = ele;

    }

}



/** Class SplayTree **/

class SplayTree

{

    private SplayNode root;

    private int count = 0;



    /** Constructor **/

    public SplayTree()

    {

        root = null;

    }



    /** Function to check if tree is empty **/

    public boolean isEmpty()

    {

        return root == null;

    }



    /** clear tree **/

    public void clear()

    {

        root = null;

        count = 0;

    }



    /** function to insert element */

    public void insert(int ele)

    {

        SplayNode z = root;

        SplayNode p = null;

        while (z != null)

        {

            p = z;

            if (ele > p.element)

                z = z.right;

            else

                z = z.left;

        }

        z = new SplayNode();

        z.element = ele;

        z.parent = p;

        if (p == null)

            root = z;

        else if (ele > p.element)

            p.right = z;

        else

            p.left = z;

        Splay(z);

        count++;

    }

    /** rotate **/

    public void makeLeftChildParent(SplayNode c, SplayNode p)

    {

        if ((c == null) || (p == null) || (p.left != c) || (c.parent != p))

            throw new RuntimeException("WRONG");



        if (p.parent != null)

        {

            if (p == p.parent.left)

                p.parent.left = c;

            else

                p.parent.right = c;

        }

        if (c.right != null)

            c.right.parent = p;



        c.parent = p.parent;

        p.parent = c;

        p.left = c.right;

        c.right = p;

    }



    /** rotate **/

    public void makeRightChildParent(SplayNode c, SplayNode p)

    {

        if ((c == null) || (p == null) || (p.right != c) || (c.parent != p))

            throw new RuntimeException("WRONG");

        if (p.parent != null)

        {

            if (p == p.parent.left)

                p.parent.left = c;

            else

                p.parent.right = c;

        }

        if (c.left != null)

            c.left.parent = p;

        c.parent = p.parent;

        p.parent = c;

        p.right = c.left;

        c.left = p;

    }



    /** function splay **/

    private void Splay(SplayNode x)

    {

        while (x.parent != null)

        {

            SplayNode Parent = x.parent;

            SplayNode GrandParent = Parent.parent;

            if (GrandParent == null)

            {

                if (x == Parent.left)

                    makeLeftChildParent(x, Parent);

                else

                    makeRightChildParent(x, Parent);

            }

            else

            {

                if (x == Parent.left)

                {

                    if (Parent == GrandParent.left)

                    {

                        makeLeftChildParent(Parent, GrandParent);

                        makeLeftChildParent(x, Parent);

                    }

                    else

                    {

                        makeLeftChildParent(x, x.parent);

                        makeRightChildParent(x, x.parent);

                    }

                }

                else

                {

                    if (Parent == GrandParent.left)

                    {

                        makeRightChildParent(x, x.parent);

                        makeLeftChildParent(x, x.parent);

                    }

                    else

                    {

                        makeRightChildParent(Parent, GrandParent);

                        makeRightChildParent(x, Parent);

                    }

                }

            }

        }

        root = x;

    }



    /** function to remove element **/

    public void remove(int ele)

    {

        SplayNode node = findNode(ele);

        remove(node);

    }



    /** function to remove node **/

    private void remove(SplayNode node)

    {

        if (node == null)

            return;



        Splay(node);

        if( (node.left != null) && (node.right !=null))

        {

            SplayNode min = node.left;

            while(min.right!=null)

                min = min.right;



            min.right = node.right;

            node.right.parent = min;

            node.left.parent = null;

            root = node.left;

        }

        else if (node.right != null)

        {

            node.right.parent = null;

            root = node.right;

        }

        else if( node.left !=null)

        {

            node.left.parent = null;

            root = node.left;

        }

        else

        {

            root = null;

        }

        node.parent = null;

        node.left = null;

        node.right = null;

        node = null;

        count--;

    }



    /** Functions to count number of nodes **/

    public int countNodes()

    {

        return count;

    }



    /** Functions to search for an element **/

    public boolean search(int val)

    {

        return findNode(val) != null;

    }



    private SplayNode findNode(int ele)

    {

        SplayNode PrevNode = null;

        SplayNode z = root;

        while (z != null)

        {

            PrevNode = z;

            if (ele > z.element)

                z = z.right;

            else if (ele < z.element)

                z = z.left;

            else if(ele == z.element) {

                Splay(z);

                return z;

            }



        }

        if(PrevNode != null)

        {

            Splay(PrevNode);

            return null;

        }

        return null;

    }



    /** Function for inorder traversal **/

    public void inorder()

    {

        inorder(root);

    }

    private void inorder(SplayNode r)

    {

        if (r != null)

        {

            inorder(r.left);

            System.out.print(r.element +" ");
            SInorder.append(r.element+"  ");



            inorder(r.right);

        }

    }



    /** Function for preorder traversal **/

    public void preorder()

    {

        preorder(root);

    }

    private void preorder(SplayNode r)

    {
        String str="";

        if (r != null)

        {

            System.out.print(r.element +" ");
            SPreorderTv.append(r.element+"  ");




            preorder(r.left);

            preorder(r.right);

        }

    }



    /** Function for postorder traversal **/

    public void postorder()

    {

        postorder(root);

    }

    private void postorder(SplayNode r)

    {

        if (r != null)

        {

            postorder(r.left);

            postorder(r.right);

            System.out.print(r.element +" ");
            SPostorder.append(r.element+"  ");

        }

    }

}

    public void DisplaySTree(View view) {

        SPreorderTv.setText("Preorder->");
        SPostorder.setText("Postorder->");
        SInorder.setText("Inorder->");
        spt.preorder();
        spt.inorder();
        spt.postorder();


    }
}