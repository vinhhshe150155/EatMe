package com.vinhhs.afood;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class SignUpActivity extends AppCompatActivity {
    private Button btnSignIn;
    private EditText edtEmail;
    private EditText edtPassword;
    private EditText edtNickname;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        initUI();
        bindingAction();
    }

    private void initUI() {
        btnSignIn = findViewById(R.id.btnSignIn);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        progressDialog = new ProgressDialog(this);
        edtNickname = findViewById(R.id.edtNickname);
    }
    private void bindingAction(){
        btnSignIn.setOnClickListener(view -> onClickSignUp());
    }

    private void onClickSignUp() {
        String strEmail = edtEmail.getText().toString().trim();
        String strPassword = edtPassword.getText().toString().trim();
        FirebaseAuth auth = FirebaseAuth.getInstance();
        progressDialog.show();
        auth.createUserWithEmailAndPassword(strEmail, strPassword)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = auth.getCurrentUser();
                        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                .setDisplayName(edtNickname.getText().toString().trim()).build();
                        user.updateProfile(profileUpdates)
                                .addOnCompleteListener(task1 -> {
                                    progressDialog.dismiss();
                                    if (task1.isSuccessful()) {
                                        Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                                        startActivity(intent);
                                        finishAffinity();
                                    }
                                });
                    } else {
                        Toast.makeText(SignUpActivity.this, "FAILED", Toast.LENGTH_SHORT).show();
                    }
                    progressDialog.dismiss();
                });

    }
}