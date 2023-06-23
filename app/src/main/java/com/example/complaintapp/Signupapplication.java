package com.example.complaintapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class Signupapplication extends AppCompatActivity {
    private FirebaseAuth auth;
    private static final String TAG="RegisterActivity";
    Button registerBtn;
    private EditText registerName,registerPassword,registerEmail,registerPhoneNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signupapplication);
        registerBtn = findViewById(R.id.registerbtn);
        registerEmail=findViewById(R.id.userEmail);
        registerPassword=findViewById(R.id.userPassword);
        registerName=findViewById(R.id.userName);
        registerPhoneNumber=findViewById(R.id.userPhoneNumber);

        Toast.makeText(this, "Register your Account", Toast.LENGTH_SHORT).show();

        registerBtn.setOnClickListener(view -> {
            String fullName=registerName.getText().toString();
            String email=registerEmail.getText().toString();
            String phoneNumber=registerPhoneNumber.getText().toString();
            String password=registerPassword.getText().toString();
            if(TextUtils.isEmpty(fullName)){
                Toast.makeText(this, "Please Enter your Full Name", Toast.LENGTH_SHORT).show();
                registerName.setError("Full Name is Required");
                registerName.requestFocus();
            } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(this, "Please re-enter your email", Toast.LENGTH_SHORT).show();
                registerEmail.setError("Valid Email Required");
                registerEmail.requestFocus();
            }   else if (TextUtils.isEmpty(password)) {
                Toast.makeText(this, "Please Enter your Password", Toast.LENGTH_SHORT).show();
                registerPassword.setError("Password required");
                registerPassword.requestFocus();
            } else if (password.length()<6) {
                Toast.makeText(this, "Please enter 8 digits/letters Password", Toast.LENGTH_SHORT).show();
                registerPassword.setError("Password is weak");
                registerPassword.requestFocus();

            }else if (TextUtils.isEmpty(phoneNumber)) {
                Toast.makeText(this, "Please enter your phone Number", Toast.LENGTH_SHORT).show();
                registerPhoneNumber.setError("Phone number required");
                registerPhoneNumber.requestFocus();
            } else{
                registerUser(fullName,email,phoneNumber,password);
            }
        });
    }

    private void registerUser(String fullName, String email, String phoneNumber, String password){
        auth=FirebaseAuth.getInstance();
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, task -> {

                        if(task.isSuccessful()){

                FirebaseUser firebaseUser=auth.getCurrentUser();

                ReadWriteUserDetails readWriteDetails=new ReadWriteUserDetails(fullName,phoneNumber);

                //Extracting User Reference from Database-"Registered Users"
                DatabaseReference referenceProfile= FirebaseDatabase.getInstance().getReference("Registered Users");

                assert firebaseUser != null;
                referenceProfile.child(firebaseUser.getUid()).setValue(readWriteDetails).addOnCompleteListener(task1-> {

                    if(task1.isSuccessful()){
                        Toast.makeText(this, "User Created", Toast.LENGTH_SHORT).show();

                        //open user after successful Registration
                        Intent intent=new Intent(this, loginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();
                    }else{
                        Toast.makeText(this, "Registration Failed", Toast.LENGTH_SHORT).show();
                    }
                });
            }else{
                try {
                    throw Objects.requireNonNull(task.getException());
                }catch (FirebaseAuthWeakPasswordException e){
                    registerPassword.setError("Your password is too weak.kindly use password of length 8 digits/letter long");
                    registerPassword.requestFocus();
                }catch(FirebaseAuthInvalidCredentialsException e){
                    registerEmail.setError("Your email is invalid or already in use.Kindly re-enter it");
                    registerEmail.clearComposingText();
                    registerPassword.clearComposingText();
                    registerEmail.requestFocus();

                }catch (FirebaseAuthUserCollisionException e){
                    registerEmail.setError("User is already registered with this email.Use another email");
                    registerEmail.clearComposingText();
                    registerPassword.clearComposingText();
                    registerEmail.requestFocus();

                }catch (Exception e){
                    Log.e(TAG,e.getMessage());
                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}