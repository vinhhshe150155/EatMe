package com.foodapp.eatme.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.foodapp.eatme.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

public class ForgotPasswordActivity extends AppCompatActivity {
    private Button btnForgotPassword;
    private EditText edtEmailReset;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        btnForgotPassword = (Button) findViewById(R.id.btnReset);
        edtEmailReset = (EditText) findViewById(R.id.edtReset);
        auth = FirebaseAuth.getInstance();
        btnForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetPasswordByEmail();
            }
        });
    }
    private void resetPasswordByEmail() {
        String email = edtEmailReset.getText().toString().trim();
        if(email.isEmpty()){
            edtEmailReset.setError("Email is required");
            edtEmailReset.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            edtEmailReset.setError("Please enter valid email");
            edtEmailReset.requestFocus();
            return;
        }
        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Intent intent = new Intent(ForgotPasswordActivity.this,CheckingMailActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(ForgotPasswordActivity.this, "Try again", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}