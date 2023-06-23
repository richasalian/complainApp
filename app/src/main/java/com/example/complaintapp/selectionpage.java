package com.example.complaintapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class selectionpage extends AppCompatActivity {
    Button rbtn,gbtn,ebtn,wbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectionpage);
        rbtn=(Button)findViewById(R.id.button7);
        gbtn=(Button)findViewById(R.id.button6);
        ebtn=(Button)findViewById(R.id.button5);
        wbtn=(Button)findViewById(R.id.button4);
        rbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(selectionpage.this,roadpage.class);
                startActivity(intent);
            }
        });
        gbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(selectionpage.this,garbagepage.class);
                startActivity(intent);

            }
        });
        ebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(selectionpage.this,electricitypage.class);
                startActivity(intent);

            }
        });
        wbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(selectionpage.this,waterpage.class);
                startActivity(intent);

            }
        });
    }
}