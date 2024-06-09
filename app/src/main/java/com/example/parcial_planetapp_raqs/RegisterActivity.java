package com.example.parcial_planetapp_raqs;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {
    private EditText regUsername, regPassword, regConfirmPassword;
    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        regUsername = findViewById(R.id.regUsername);
        regPassword = findViewById(R.id.regPassword);
        regConfirmPassword = findViewById(R.id.regConfirmPassword);
        registerButton = findViewById(R.id.registerButton);

        registerButton.setOnClickListener(v -> {
            String user = regUsername.getText().toString();
            String pass = regPassword.getText().toString();
            String confirmPass = regConfirmPassword.getText().toString();

            if (pass.equals(confirmPass)) {
                SharedPreferences preferences = getSharedPreferences("my_preferences", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("usuario", user);
                editor.putString("contraseña", pass);
                editor.apply();

                Toast.makeText(RegisterActivity.this, "Registrado correctamente", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(RegisterActivity.this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
