package com.example.parcial_planetapp_raqs;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class PlanetListActivity extends AppCompatActivity {
    private LinearLayout planetList;
    private String[] planetNames;
    private String[] planetDescriptions;
    private TypedArray planetImages;
    private TextView usernameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planet_list);

        planetList = findViewById(R.id.planetList);
        usernameTextView = findViewById(R.id.usernameTextView);

        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        if (username != null) {
            usernameTextView.setText(username);
        }

        planetNames = getResources().getStringArray(R.array.planet_names);
        planetDescriptions = getResources().getStringArray(R.array.planet_descriptions);
        planetImages = getResources().obtainTypedArray(R.array.planet_images);

        for (int i = 0; i < planetNames.length; i++) {
            addPlanet(planetNames[i], planetImages.getResourceId(i, -1), planetDescriptions[i]);
        }

        planetImages.recycle();
    }

    private void addPlanet(String name, int imageRes, String description) {
        View planetView = getLayoutInflater().inflate(R.layout.planet_item, planetList, false);
        TextView planetName = planetView.findViewById(R.id.planetName);
        ImageView planetImage = planetView.findViewById(R.id.planetImage);

        planetName.setText(name);
        planetImage.setImageResource(imageRes);
        planetView.setOnClickListener(v -> {
            Intent intent = new Intent(PlanetListActivity.this, PlanetDetailActivity.class);
            intent.putExtra("planetName", name);
            intent.putExtra("planetImage", imageRes);
            intent.putExtra("planetDescription", description);
            startActivity(intent);
        });

        planetList.addView(planetView);
    }
}
