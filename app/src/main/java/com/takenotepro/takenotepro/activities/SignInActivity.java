package com.takenotepro.takenotepro.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.takenotepro.takenotepro.R;
import com.takenotepro.takenotepro.utils.ProgressDialog;
import com.takenotepro.takenotepro.utils.Toasts;

public class SignInActivity extends AppCompatActivity {

    TextView signUpTextView;
    EditText emailEditText;
    EditText passwordEditText;
    Button signInBtn;

    ProgressDialog progressDialog = new ProgressDialog(SignInActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        signUpTextView = findViewById(R.id.sign_up_txt);
        emailEditText = findViewById(R.id.edtEmailAddress_signin);
        passwordEditText = findViewById(R.id.edtPassword_signin);
        signInBtn = findViewById(R.id.sign_in_btn);

        signUpTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignInActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signInUser();
            }
        });
    }

    void signInUser(){

        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        boolean isValid = validate(email, password);
        if(!isValid){
            return;
        }

        progressDialog.startProgressBar();

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(SignInActivity.this,
                new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        progressDialog.dismissDialog();

                        if(task.isSuccessful()){
                            if(firebaseAuth.getCurrentUser().isEmailVerified()){
                                Toasts.showToast(SignInActivity.this, "Successfully signed in");
                                startActivity(new Intent(SignInActivity.this, MainActivity.class));
                                finish();
                            }else{
                                Toasts.showToast(SignInActivity.this, "Please verify your email, check your emails");
                            }

                        }else{
                            Toasts.showToast(SignInActivity.this, "Failed to sign in, Make sure you have entered correct credentials");
                        }
                    }
                });
    }

    boolean validate(String email, String password){
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailEditText.setError("Invalid Email, Please enter valid email");
            return false;
        }

        // validate the password
        if(password.length() < 5){
            passwordEditText.setError("Password must contain at least 5 characters");
            return false;
        }
        return true;
    }
}