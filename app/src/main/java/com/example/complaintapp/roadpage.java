package com.example.complaintapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class roadpage extends AppCompatActivity {
    EditText comp,loc;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roadpage);
        comp=(EditText)findViewById(R.id.editTextTextMultiLine);
        loc=(EditText)findViewById(R.id.editTextTextPostalAddress);
        submit=(Button)findViewById(R.id.button2);
        String rcomp=getIntent().getStringExtra("complaint");
        String rloc=getIntent().getStringExtra("Location");
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String complaint=comp.getText().toString();
                String Location=loc.getText().toString();


                Intent i = new Intent(roadpage.this,mailpage.class);
                startActivity(i);


            }
        });

    }
}