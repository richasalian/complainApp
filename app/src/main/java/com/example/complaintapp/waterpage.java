package com.example.complaintapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
public class waterpage extends AppCompatActivity {
    EditText comp1,loc1;
    Button submit1;
DatabaseReference db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waterpage);
        comp1 = (EditText) findViewById(R.id.editTextTextMultiLine2);
        loc1 = (EditText) findViewById(R.id.editTextTextPostalAddress2);
        submit1 = (Button) findViewById(R.id.button3);
        String wcomp = getIntent().getStringExtra("complaint1");
        String wloc = getIntent().getStringExtra("Location1");
        submit1.setOnClickListener(view -> {
            insertdata();
            Intent i = new Intent(waterpage.this, mailpage.class);
            startActivity(i);


        });
    }
            private void insertdata() {
                db=FirebaseDatabase.getInstance().getReference("Water Complaints");
                String waterq = comp1.getText().toString();
                String waterl = loc1.getText().toString();
                ReadWriteComplaints write=new ReadWriteComplaints(waterl,waterq);
                db.child(FirebaseAuth.getInstance().getUid()).setValue(write).addOnCompleteListener(v->{
                   if(v.isSuccessful()){
                       Toast.makeText(this, "Data inserted", Toast.LENGTH_SHORT).show();
                   }
                });

//                Water water = new Water(waterl, waterq);
//
//                db.push().setValue(water, (error, ref) -> {
//                });
//                Toast.makeText(waterpage.this,"Data Inserted", Toast.LENGTH_SHORT).show();
            }


}