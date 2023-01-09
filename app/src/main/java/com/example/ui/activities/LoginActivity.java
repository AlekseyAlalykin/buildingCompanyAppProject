package com.example.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.services.AuthenticationService;


public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        TextView link = findViewById(R.id.textViewSignUp);
        link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });

        Button button = findViewById(R.id.btnLogin);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText emailField = findViewById(R.id.inputEmail);
                EditText passwordField = findViewById(R.id.inputPassword);

                try {
                    AuthenticationService authenticationService = new AuthenticationService();
                    authenticationService.authenticate(emailField.getText().toString(),
                            passwordField.getText().toString());

                } catch (Exception e){
                    Toast.makeText(LoginActivity.this,
                            getString(R.string.login_invalid_email_or_password),
                            Toast.LENGTH_LONG).show();
                    return;
                }

                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }
}
