package com.example.parcial_planetapp_raqs;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class PlanetDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planet_detail);

        TextView planetName = findViewById(R.id.planetName);
        ImageView planetImage = findViewById(R.id.planetImage);
        TextView planetDescription = findViewById(R.id.planetDescription);

        Intent intent = getIntent();
        String name = intent.getStringExtra("planetName");
        int imageRes = intent.getIntExtra("planetImage", 0);
        String description = intent.getStringExtra("planetDescription");

        planetName.setText(name);
        planetImage.setImageResource(imageRes);
        planetDescription.setText(description);
    }

    public void closeDetail(View view) {
        finish();
    }
}

