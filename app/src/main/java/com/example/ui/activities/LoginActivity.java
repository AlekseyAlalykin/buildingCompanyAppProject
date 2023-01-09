package com.example.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.activities.R;
import com.example.model.User;
import com.example.services.AuthenticationService;
import com.example.services.Session;
import com.example.services.UserManager;


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

                String email = emailField.getText().toString();
                String password = passwordField.getText().toString();

                boolean isAuthenticated = AuthenticationService.authenticate(email, password);

                if (!isAuthenticated){
                    Toast.makeText(LoginActivity.this,
                            getString(R.string.login_invalid_email_or_password),
                            Toast.LENGTH_LONG).show();
                    return;
                }


                UserManager userManager = UserManager.getInstance();
                User user = userManager.getUserByEmail(email);

                Session.setCurrentUser(user);

                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.putExtra("email", user.getEmail());
                intent.putExtra("phone", user.getPhoneNumber());
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        TextView forgotPasswordButton = findViewById(R.id.forgotPassword);
        forgotPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoginActivity.this, getString(R.string.function_not_available),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
