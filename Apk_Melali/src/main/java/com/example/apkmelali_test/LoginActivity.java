package com.example.apkmelali_test;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameEditText, passwordEditText;
    private Button loginButton;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);

        db = AppDatabase.getDatabase(this);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                // Validate login
                new Thread(() -> {
                    User user = db.userDao().findByUsernameAndPassword(username, password);
                    runOnUiThread(() -> {
                        if (user != null) {
                            // Save login status
                            SharedPreferences preferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putBoolean("isLoggedIn", true);
                            editor.apply();

                            // Redirect to DashboardActivity
                            Intent intent = new Intent(LoginActivity.this, Dashboard_Activity.class);
                            startActivity(intent);
                            finish(); // Close LoginActivity
                        } else {
                            Toast.makeText(LoginActivity.this, "Login failed. Check your username and password.", Toast.LENGTH_SHORT).show();
                        }
                    });
                }).start();
            }
        });
    }
}
