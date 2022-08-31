package com.takenotepro.takenotepro.activities;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.takenotepro.takenotepro.R;

public class SignUpActivity extends AppCompatActivity {

    TextView signIntextView;
    EditText emailEditText;
    EditText passwordEditText;
    EditText confirmPassEditText;
    Button signUpBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        signIntextView = findViewById(R.id.sign_in_txt);
        emailEditText = findViewById(R.id.edtEmailAddress);
        passwordEditText = findViewById(R.id.edtPassword);
        confirmPassEditText = findViewById(R.id.edtConfirmPassword);
        signUpBtn = findViewById(R.id.sign_up_btn);

        signIntextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                startActivity(intent);
            }
        });

        signUpBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                createAccount();

            }
        });
    }

    void createAccount(){
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        String confirmPassword = confirmPassEditText.getText().toString();

//        boolean isValid = validate(email, password, confirmPassword);
//        if(!isValid){
//            return;
//        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailEditText.setError("Invalid Email, Please enter valid email");
        } else if(password.length() < 5){
            passwordEditText.setError("Password must contain at least 5 characters");
        } else if(!password.equals(confirmPassword)){
            confirmPassEditText.setError("Passwords don't match");
        } else{
            FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
            firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(SignUpActivity.this,
                    new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(SignUpActivity.this, "Successfully created an account", Toast.LENGTH_SHORT).show();
                                firebaseAuth.getCurrentUser().sendEmailVerification();
                                firebaseAuth.signOut();
                                finish();
                            }else{
                                Toast.makeText(SignUpActivity.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }

//    boolean validate(String email, String password, String confirmPassword){
//        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
//            emailEditText.setError("Invalid Email, Please enter valid email");
//            return false;
//        } else if(password.length() < 5){
//            passwordEditText.setError("Password must contain at least 5 characters");
//            return false;
//        } else if(!password.equals(confirmPassword)){
//            confirmPassEditText.setError("Passwords don't match");
//            return false;
//        }
//
//        // validate the password
//        if(password.length() < 5){
//            passwordEditText.setError("Password must contain at least 5 characters");
//            return false;
//        }
//
//        //validate the confirm password
//        if(!password.equals(confirmPassword)){
//            confirmPassEditText.setError("Passwords don't match");
//            return false;
//        }
//        return true;
//    }
}