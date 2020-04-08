package com.example.adscourseproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button rbTree,trie,splayTree;

    Button rbtheory,trietheory,splaytheory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rbTree = findViewById(R.id.rbTree);
        splayTree = findViewById(R.id.splay);
        trie = findViewById(R.id.trie);


        rbtheory = findViewById(R.id.theoryrb);
        splaytheory = findViewById(R.id.theorysp);
        trietheory = findViewById(R.id.theorytr);


        rbtheory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this,RedBlackTheory.class);
                startActivity(intent);
            }
        });


        splaytheory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this,SplayTheory.class);
                startActivity(intent);
            }
        });


        trietheory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this,TrieTheory.class);
                startActivity(intent);
            }
        });



        rbTree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this,RBTreeActivity.class);
                startActivity(intent);

            }
        });

        splayTree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, SplayTreeActivity.class);
                startActivity(intent);

            }
        });

        trie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, TrieActivity.class);
                startActivity(intent);

            }
        });


    }


}
