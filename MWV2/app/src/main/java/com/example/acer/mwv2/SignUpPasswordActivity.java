package com.example.acer.mwv2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class SignUpPasswordActivity extends AppCompatActivity {


    EditText editTextPassword;
    private ProgressDialog progressDialog;
    Button btnSignUp;


    //defining firebaseauth object
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_password);

        editTextPassword = findViewById(R.id.editTextPassword);

        //initializing firebase auth object
        firebaseAuth = FirebaseAuth.getInstance();

        btnSignUp = findViewById(R.id.btnSignup);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });

    }

    private void registerUser(){

        //getting email and password from edit texts

        Intent in = getIntent();
        String email = in.getStringExtra("email");
        String password  = editTextPassword.getText().toString().trim();

        //checking if email and passwords are empty
        if (password.isEmpty()) {
            editTextPassword.setError("Password is required");
            editTextPassword.requestFocus();
            return;
        }

        if (password.length() < 6) {
            editTextPassword.setError("Minimum lenght of password should be 6");
        }

        //if the email and password are not empty
        //displaying a progress dialog

//        progressDialog.setMessage("Registering Please Wait...");
//        progressDialog.show();

        //creating a new user
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //checking if success
                        if(task.isSuccessful()){
                            finish();
                            startActivity(new Intent(getApplicationContext(), MapsActivity.class));
                        }else{
                            //display some message here
                            Toast.makeText(SignUpPasswordActivity.this,"Registration Error",Toast.LENGTH_LONG).show();
                        }
//                        progressDialog.dismiss();
                    }
                });

    }


}