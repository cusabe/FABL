package com.example.propertyrentalapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class userRegistration extends AppCompatActivity {

    EditText fname, lname, emailReg, passwordReg;
    Button btnRegister;
    TextView signinText;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);
        this.setTitle("REGISTER");

        fname = findViewById(R.id.firstNameEdittext);
        lname = findViewById(R.id.lastNameEdittext);
        emailReg = findViewById(R.id.regEmailEdittext);
        passwordReg = findViewById(R.id.regPasswordEdittext);
        btnRegister = findViewById(R.id.btnRegister);
        signinText = findViewById(R.id.signInText);
        firebaseAuth = FirebaseAuth.getInstance();

        emailReg.addTextChangedListener(registerTextWatcher);
        passwordReg.addTextChangedListener(registerTextWatcher);

    }

    // Disabling the register button when the fields are not filled
    public TextWatcher registerTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String email = emailReg.getText().toString().trim();
            String password = passwordReg.getText().toString().trim();
            String firstName = fname.getText().toString().trim();
            String lastName = lname.getText().toString().trim();

            btnRegister.setEnabled(!email.isEmpty() && !password.isEmpty() && !firstName.isEmpty() && !lastName.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    public void regTextPressed (View view) {
        Intent intent = new Intent(getApplicationContext(), userLogin.class);
        startActivity(intent);
    }

    // Register Button
    public void registerPressed (View view) {
        String firstName = fname.getText().toString().trim();
        String lastName = lname.getText().toString().trim();
        String email = emailReg.getText().toString().trim();
        String password = passwordReg.getText().toString().trim();

        if (TextUtils.isEmpty(firstName)) {
            fname.setError("Please Enter Your First Name");
            return;
        }

        if (TextUtils.isEmpty(lastName)) {
            lname.setError("Please Enter You Last Name");
            return;
        }

        if (TextUtils.isEmpty(email)) {
            emailReg.setError("Please Enter Email");
            return;
        }

        if (TextUtils.isEmpty(password)) {
            passwordReg.setError("Please Enter Password");
        }

        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    Toast.makeText(userRegistration.this, "Registration Successful", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), Filter.class);
                    startActivity(intent);
                }

                else {
                    Toast.makeText(userRegistration.this, "Registration Unsuccessful" + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}