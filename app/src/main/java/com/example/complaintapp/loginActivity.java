package com.example.complaintapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class loginActivity extends AppCompatActivity {
    private FirebaseAuth authProfile;
    Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText loginEmail=findViewById(R.id.EmaileditText);
        EditText loginPassword=findViewById(R.id.passEditText);
        authProfile=FirebaseAuth.getInstance();
        login=findViewById(R.id.loginBtn);
        login.setOnClickListener(view -> {
            String email=loginEmail.getText().toString();
            String password=loginPassword.getText().toString();

            if(TextUtils.isEmpty(email)){
                Toast.makeText(this, "Email isn't Entered", Toast.LENGTH_SHORT).show();
                loginEmail.setError("Enter your Email");
                loginEmail.requestFocus();
            } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(this, "Please re-enter your email", Toast.LENGTH_SHORT).show();
                loginEmail.setError("Enter your Email");
                loginEmail.requestFocus();
            }else if(TextUtils.isEmpty(password)){
                Toast.makeText(this, "Password is required", Toast.LENGTH_SHORT).show();
                loginPassword.setError("Password is required");
                loginPassword.requestFocus();
            }else{
                loginUser(email,password);
            }
        });
    }

    private void loginUser(String email, String password) {

        authProfile.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                Toast.makeText(this, "Logged In", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
            }else {
                Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
