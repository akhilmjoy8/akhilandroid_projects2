package com.example.acer.nsample.Login;


import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.acer.nsample.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class OtpVerificationActivity extends AppCompatActivity {
    private static final String TAG = "PhoneAuth";

    private String phoneText;
    private String codeText;
    private Button verifyButton;
    private Button sendButton;
    private Button resendButton;
    private Button signoutButton;
    private TextView statusText;

    private String phoneVerificationId;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks
            verificationCallbacks;
    private PhoneAuthProvider.ForceResendingToken resendToken;
    LinearLayout li;

    private FirebaseAuth fbAuth;

    TextView text1, error;
    private static final String FORMAT = "%02d:%02d";
    int seconds, minutes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verification);
        getSupportActionBar().hide();


        //timer
        text1 = (TextView) findViewById(R.id.etTimer);
        error = (TextView) findViewById(R.id.etError);

        new CountDownTimer(60000, 1000) { // adjust the milli seconds here

            public void onTick(long millisUntilFinished) {

                text1.setText("" + String.format(FORMAT,

                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(
                                TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                                TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
            }

            public void onFinish() {
                text1.setText("00:00");
                error.setText("Timed Out");

            }
        }.start();


        final EditText num1 = (EditText) findViewById(R.id.etVerif1);
        final EditText num2 = (EditText) findViewById(R.id.etVerif2);
        final EditText num3 = (EditText) findViewById(R.id.etVerif3);
        final EditText num4 = (EditText) findViewById(R.id.etVerif4);
        final EditText num5 = (EditText) findViewById(R.id.etVerif5);
        final EditText num6 = (EditText) findViewById(R.id.etVerif6);
        num1.setFocusable(true);
        num1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                num2.requestFocus();

            }

            @Override
            public void afterTextChanged(Editable s) {
                num2.setFocusable(true);
            }
        });
        num2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                num3.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        num3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                num4.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        num4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                num5.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        num5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                num6.requestFocus();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        num6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                codeText = String.format("%s%s%s%s%s%s", num1.getText().toString(),num2.getText().toString(), num3.getText().toString()
                        , num4.getText().toString(), num5.getText().toString(), num6.getText().toString());
                //Toast.makeText(OtpVerificationActivity.this,codeText,Toast.LENGTH_LONG).show();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        phoneText = "8281306327";
        resendButton = (Button) findViewById(R.id.btResend);
        resendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
    public void verifyCode(View view) {

        String code = codeText;

        PhoneAuthCredential credential =
                PhoneAuthProvider.getCredential(phoneVerificationId, code);
        signInWithPhoneAuthCredential(credential);
    }
}

