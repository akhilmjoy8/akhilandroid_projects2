package com.example.acer.mwv2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignUpEmailActivity extends AppCompatActivity {

    Button btnNext;
    EditText etEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_email);
        getSupportActionBar().hide();
        try {
            etEmail = findViewById(R.id.edEmail);

            btnNext = findViewById(R.id.btnNext);
            btnNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                   userSignUp();
                }
            });
        } catch (Exception e) {
        }
    }
    private void userSignUp() {
        String email = etEmail.getText().toString().trim();


        if (email.isEmpty()) {
            etEmail.setError("Email is required");
            etEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.setError("Please enter a valid email");
            etEmail.requestFocus();
            return;
        }
        else {
            Intent intent = new Intent(SignUpEmailActivity.this, SignUpPasswordActivity.class);
            intent.putExtra("email", email);
            startActivity(intent);
        }
    }
}
