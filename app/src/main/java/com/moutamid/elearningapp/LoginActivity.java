package com.moutamid.elearningapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.moutamid.elearningapp.utilis.Constants;

public class LoginActivity extends AppCompatActivity {
    TextView goto_signup;
    TextView forget_text;
    Button submit_login_btn;
    EditText email, password;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.email_et_login);
        password = findViewById(R.id.password_et_login);

        progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setMessage("Logging In");
        progressDialog.setCancelable(false);

        goto_signup = findViewById(R.id.goto_signup);
        goto_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent homeIntent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(homeIntent);
                Animatoo.animateFade(LoginActivity.this);
                finish();
            }
        });

        forget_text = findViewById(R.id.forget_text);
        forget_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent homeIntent = new Intent(LoginActivity.this, ForgetPassword.class);
                startActivity(homeIntent);
                Animatoo.animateFade(LoginActivity.this);
                finish();
            }
        });

        submit_login_btn = findViewById(R.id.submit_login_btn);
        submit_login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()){
                    progressDialog.show();
                    Constants.auth().signInWithEmailAndPassword(
                            email.getText().toString(),
                            password.getText().toString()
                    ).addOnSuccessListener(command -> {
                        progressDialog.dismiss();
                        Intent homeIntent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(homeIntent);
                        Animatoo.animateFade(LoginActivity.this);
                        finish();
                    }).addOnFailureListener(e -> {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    });
                }
            }
        });

    }
    private boolean validate() {
        if (email.getText().toString().isEmpty()){
            email.setError("Email is Required");
            email.requestFocus();
            return false;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()) {
            email.setError("Please Provide valid email*");
            email.requestFocus();
            return false;
        }
        if (password.getText().toString().isEmpty()){
            password.setError("Password is Required");
            password.requestFocus();
            return false;
        }
        return true;
    }
}