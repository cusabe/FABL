package com.example.propertyrentalapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class userLogin extends AppCompatActivity {

    EditText emailLog, passwordLog;
    Button btnLogin;
    TextView signUpText;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
        this.setTitle("SIGN IN");

        emailLog = findViewById(R.id.loginEmailEdittext);
        passwordLog = findViewById(R.id.loginPasswordEdittext);
        btnLogin = findViewById(R.id.btnLogin);
        signUpText = findViewById(R.id.signUpText);
        firebaseAuth = FirebaseAuth.getInstance();

    }

    public void logTextPressed (View view) {
        Intent intent = new Intent(getApplicationContext(), userRegistration.class);
        startActivity(intent);
    }

    public void loginPressed(View view) {
        String email = emailLog.getText().toString().trim();
        String password = passwordLog.getText().toString().trim();


        if (TextUtils.isEmpty(email)) {
            emailLog.setError("Please Enter Email");
            return;
        }

        if (TextUtils.isEmpty(password)) {
            passwordLog.setError("Please Enter Password");
        }

        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    Toast.makeText(userLogin.this, "Log In Successful", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), Filter.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(userLogin.this, "Sign In Unsuccessful" + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}