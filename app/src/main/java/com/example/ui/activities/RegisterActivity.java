package com.example.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.services.SignUpService;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        TextView link = findViewById(R.id.alreadyHaveAccount);
        link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
                finish();
            }
        });

        Button button = findViewById(R.id.btnRegister);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                EditText emailField = findViewById(R.id.inputEmail);
                EditText phoneNumberField = findViewById(R.id.inputPhone);
                EditText passwordField = findViewById(R.id.inputPassword);
                EditText passwordConformField = findViewById(R.id.inputConformPassword);

                String email = emailField.getText().toString();
                String phoneNumber = phoneNumberField.getText().toString();
                String password = passwordField.getText().toString();
                String passwordConform = passwordConformField.getText().toString();

                if (!password.equals(passwordConform)){
                    Toast.makeText(RegisterActivity.this,
                            getString(R.string.registration_password_not_matching),
                            Toast.LENGTH_LONG).show();
                    return;
                }

                try{
                    SignUpService signUpService = new SignUpService();

                    signUpService.SingUpUser(phoneNumber,email,password);
                } catch (IllegalStateException e){
                    Toast.makeText(RegisterActivity.this,
                            getString(R.string.registration_identity_already_taken),
                            Toast.LENGTH_LONG).show();

                    return;

                } catch (IllegalArgumentException e){
                    Toast.makeText(RegisterActivity.this,
                            getString(R.string.registration_invalid_data_presented),
                            Toast.LENGTH_LONG).show();

                    return;
                }

                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }
}
