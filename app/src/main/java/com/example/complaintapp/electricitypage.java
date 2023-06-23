package com.example.complaintapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class electricitypage extends AppCompatActivity {
    EditText comp3,loc3;
    Button submit3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electricitypage);
        comp3=(EditText)findViewById(R.id.editTextTextMultiLine3);
        loc3=(EditText)findViewById(R.id.editTextTextPostalAddress3);
        submit3=(Button)findViewById(R.id.button8);
        String ecomp=getIntent().getStringExtra("complaint");
        String eloc=getIntent().getStringExtra("Location");
        submit3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String complaint2=comp3.getText().toString();
                String Location2=loc3.getText().toString();
                Intent i = new Intent(electricitypage.this,mailpage.class);
                startActivity(i);


            }
        });
    }
}