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
            String userInput = username.getText().toString();
            String passInput = password.getText().toString();

            if (validateLogin(userInput, passInput)) {
                Intent intentLogin = new Intent(MainActivity.this, PlanetListActivity.class);
                intentLogin.putExtra("username", userInput);
                startActivity(intentLogin);
            } else {
                loginAttempts++;
                if (loginAttempts >= 3) {
                    loginButton.setEnabled(false);
                    Toast.makeText(MainActivity.this, "Demasiados intentos fallidos. Inténtelo de nuevo en 5 segundos.", Toast.LENGTH_SHORT).show();
                    new Handler().postDelayed(() -> {
                        loginButton.setEnabled(true);
                        loginAttempts = 0;
                    }, 5000);
                } else {
                    Toast.makeText(MainActivity.this, "Credenciales incorrectas. Intento " + loginAttempts + " de 3.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        registerButton.setOnClickListener(v -> {
            Intent intentRegister = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(intentRegister);
        });
    }

    private boolean validateLogin(String user, String pass) {
        SharedPreferences preferences = getSharedPreferences("my_preferences", MODE_PRIVATE);
        String savedUser = preferences.getString("usuario", "");
        String savedPassword = preferences.getString("contraseña", "");

        return user.equals(savedUser) && pass.equals(savedPassword);
    }
}
