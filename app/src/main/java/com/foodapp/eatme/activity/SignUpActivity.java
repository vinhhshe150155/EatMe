package com.foodapp.eatme.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.foodapp.eatme.R;
import com.foodapp.eatme.util.LoadingDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity {
    private Button btnSignIn;
    private TextView tvSignIn;
    private EditText edtEmail;
    private EditText edtPassword;
    private EditText edtConfirmPassword;
    private EditText edtUsername;
    private LoadingDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        initUI();
        bindingAction();
    }

    private void initUI() {
        btnSignIn = findViewById(R.id.btnSignUp);
        tvSignIn = findViewById(R.id.tvSignIn);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        edtConfirmPassword = findViewById(R.id.edtConfirmPassword);
        progressDialog = new LoadingDialog(this);
        edtUsername = findViewById(R.id.edtUsername);
    }

    private void bindingAction() {

        btnSignIn.setOnClickListener(view -> onClickSignUp());
        tvSignIn.setOnClickListener(view -> {
            Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
            startActivity(intent);
        });
    }

    public static boolean isValidPassword(final String password) {

        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);
        return matcher.matches();

    }

    private void onClickSignUp() {
        String strEmail = edtEmail.getText().toString().trim();
        boolean validEmail = true,
                validPassword = true,
                validConfirmPassword = true,
                validNickname = true;
        String strPassword = edtPassword.getText().toString().trim();
        String strConfirmPassword = edtConfirmPassword.getText().toString().trim();
        if(!strPassword.equals(strConfirmPassword)){
            edtConfirmPassword.setError("Password is not matching");
            validConfirmPassword = false;
        }
        if(strEmail.isEmpty() && !Patterns.EMAIL_ADDRESS.matcher(strEmail).matches()){
            edtEmail.setError("Please enter valid email");
            validEmail = false;
        }
        if(strPassword.isEmpty()){
            edtPassword.setError("Please enter valid password");
            validPassword = false;
        }
        String nickName = edtUsername.getText().toString().trim();
        if(nickName.isEmpty() ){
            edtUsername.setError("Please enter valid user name");
            validNickname = false;
        }
        if (!validEmail || !validNickname || !validPassword || !validConfirmPassword)
            return;
        FirebaseAuth auth = FirebaseAuth.getInstance();
        progressDialog.showDialog(null);
        auth.createUserWithEmailAndPassword(strEmail, strPassword)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = auth.getCurrentUser();
                        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                .setDisplayName(edtUsername.getText().toString().trim()).build();
                        if (user != null) {
                            user.updateProfile(profileUpdates)
                                    .addOnCompleteListener(task1 -> {
                                        progressDialog.hideDialog();
                                        if (task1.isSuccessful()) {
                                            Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                                            startActivity(intent);
                                            finishAffinity();
                                        }
                                    });
                        }
                    } else {
                        Toast.makeText(SignUpActivity.this, "FAILED", Toast.LENGTH_SHORT).show();
                    }
                    progressDialog.hideDialog();
                });

    }
}