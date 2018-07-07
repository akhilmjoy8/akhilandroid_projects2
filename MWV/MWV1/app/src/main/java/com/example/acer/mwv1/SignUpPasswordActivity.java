package com.example.acer.mwv1;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpPasswordActivity extends AppCompatActivity {

    EditText editTextPassword;
    private ProgressDialog progressDialog;
    Button btnSignUp;


    //defining firebaseauth object
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_password);

        editTextPassword = findViewById(R.id.edpasswd);

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
                            String uid = firebaseAuth.getUid();
                            Toast.makeText(SignUpPasswordActivity.this,uid,Toast.LENGTH_LONG).show();
                            startActivity(new Intent(getApplicationContext(), MapsActivity.class));
                        }else{
                            //display some message here
                            PopUp(task.getException().getMessage());
                            //Toast.makeText(SignUpPasswordActivity.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();
                        }
//                        progressDialog.dismiss();
                    }
                });

    }
    public void PopUp(String msg){
        final AlertDialog.Builder builder = new AlertDialog.Builder(SignUpPasswordActivity.this);
        LayoutInflater inflater = (SignUpPasswordActivity.this).getLayoutInflater();
        final View dialogView=inflater.inflate(R.layout.popup_message, null);
        builder.setCancelable(false);
        builder.setView(dialogView);

        TextView dayname = dialogView.findViewById(R.id.tvDayName);
        dayname.setText(msg);

        final AlertDialog alertDialog = builder.create();
        Button btnDismiss = (Button)dialogView.findViewById(R.id.btnCancel);
        btnDismiss.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                ActivityCompat.finishAffinity(SignUpPasswordActivity.this);
               // startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                alertDialog.dismiss();
            }});

        Button btnNext = (Button)dialogView.findViewById(R.id.btnNext);
        btnNext.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                alertDialog.dismiss();

            }});



        alertDialog.show();
    }

}