package com.example.apkmelali_test;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    private EditText emailEditText, usernameEditText, noHpEditText, passwordEditText;
    private Button registerButton;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);

        emailEditText = findViewById(R.id.email);
        usernameEditText = findViewById(R.id.username);
        noHpEditText = findViewById(R.id.noHp);
        passwordEditText = findViewById(R.id.password);
        registerButton = findViewById(R.id.registerButton);

        db = AppDatabase.getDatabase(this);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString();
                String username = usernameEditText.getText().toString();
                String noHp = noHpEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                // Simple validation
                if (email.isEmpty() || username.isEmpty() || noHp.isEmpty() || password.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                } else {
                    // Insert data into the database
                    new Thread(() -> {
                        User user = new User(email, username, noHp, password);
                        db.userDao().insert(user);
                        runOnUiThread(() -> {
                            Toast.makeText(RegisterActivity.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                            // Redirect to login activity
                            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish(); // Close RegisterActivity
                        });
                    }).start();
                }
            }
        });
    }
}
