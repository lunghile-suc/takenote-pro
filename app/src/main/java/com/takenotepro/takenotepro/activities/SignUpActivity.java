package com.takenotepro.takenotepro.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
    }
}