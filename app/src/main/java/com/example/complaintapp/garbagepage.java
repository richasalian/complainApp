package com.example.complaintapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class garbagepage extends AppCompatActivity {
    EditText comp4,loc4;
    Button submit4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_garbagepage);
        comp4=(EditText)findViewById(R.id.editTextTextMultiLine4);
        loc4=(EditText)findViewById(R.id.editTextTextPostalAddress4);
        submit4=(Button)findViewById(R.id.button9);
        String rcomp=getIntent().getStringExtra("complaint");
        String rloc=getIntent().getStringExtra("Location");
        submit4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String complaint=comp4.getText().toString();
                String Location=loc4.getText().toString();
                Intent i = new Intent(garbagepage.this,mailpage.class);
                startActivity(i);


            }
        });
    }
}