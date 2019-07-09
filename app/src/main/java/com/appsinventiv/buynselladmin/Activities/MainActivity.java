package com.appsinventiv.buynselladmin.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.appsinventiv.buynselladmin.Activities.Categories.AddMainCategories;
import com.appsinventiv.buynselladmin.Activities.Locations.AddLocations;
import com.appsinventiv.buynselladmin.R;

public class MainActivity extends AppCompatActivity {
    Button categories, locations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        categories = findViewById(R.id.categories);
        locations = findViewById(R.id.locations);
        categories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AddMainCategories.class));
            }
        });
        locations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AddLocations.class));
            }
        });
    }
}
