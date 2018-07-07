package com.example.acer.mwv;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SignUpEmailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_email);
        getSupportActionBar().hide();
        try {
            etEmail = findViewById(R.id.edEmail);

            btnNext = findViewById(R.id.btnNext);
            btnNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String email = etEmail.getText().toString();
                    Intent intent = new Intent(SignUpEmailActivity.this, SignUpPasswordActivity.class);
                    intent.putExtra("email", email);
                    startActivity(intent);
                }
            });
        } catch (Exception e) {
        }
    }
}
