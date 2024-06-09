package com.example.parcial_planetapp_raqs;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText username, password;
    private Button loginButton, registerButton;
    private int loginAttempts = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);
        registerButton = findViewById(R.id.registerButton);

        loginButton.setOnClickListener(v -> {
            String user = username.getText().toString();
            String pass = password.getText().toString();

            if (validateLogin(user, pass)) {
                Intent intent = new Intent(MainActivity.this, PlanetListActivity.class);
                intent.putExtra("username", user);
                startActivity(intent);
            } else {
                loginAttempts++;
                if (loginAttempts >= 3) {
                    loginButton.setEnabled(false);
                    new Handler().postDelayed(() -> loginButton.setEnabled(true), 5000);
                } else {
                    Toast.makeText(MainActivity.this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
                }
            }
        });

        registerButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(intent);
        });
    }

    private boolean validateLogin(String user, String pass) {
        SharedPreferences preferences = getSharedPreferences("my_preferences", MODE_PRIVATE);
        String savedUser = preferences.getString("usuario", "");
        String savedPassword = preferences.getString("contraseña", "");

        return user.equals(savedUser) && pass.equals(savedPassword);
    }
}
