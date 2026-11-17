package com.example.distancecalculatorapp;

import android.location.Location;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private EditText lat1EditText, lon1EditText, lat2EditText, lon2EditText;
    private Button calculateButton;
    private TextView resultTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        lat1EditText = findViewById(R.id.lat1EditText);
        lon1EditText = findViewById(R.id.lon1EditText);
        lat2EditText = findViewById(R.id.lat2EditText);
        lon2EditText = findViewById(R.id.lon2EditText);
        calculateButton = findViewById(R.id.calculateButton);
        resultTextView = findViewById(R.id.resultTextView);
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateDistance();
            }
        });

    }

    private void calculateDistance() {
        String sLat1 = lat1EditText.getText().toString().trim();
        String sLon1 = lon1EditText.getText().toString().trim();
        String sLat2 = lat2EditText.getText().toString().trim();
        String sLon2 = lon2EditText.getText().toString().trim();

        // Validate that fields are not empty
        if (TextUtils.isEmpty(sLat1) || TextUtils.isEmpty(sLon1) ||
                TextUtils.isEmpty(sLat2) || TextUtils.isEmpty(sLon2)) {
            Toast.makeText(this, "Please fill in all coordinate fields", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double lat1 = Double.parseDouble(sLat1);
            double lon1 = Double.parseDouble(sLon1);
            double lat2 = Double.parseDouble(sLat2);
            double lon2 = Double.parseDouble(sLon2);

            // Array to store the result from Location.distanceBetween
            float[] results = new float[1];

            // Computes the approximate distance in meters between two locations
            Location.distanceBetween(lat1, lon1, lat2, lon2, results);

            float distanceInMeters = results[0];
            float distanceInKm = distanceInMeters / 1000;

            // Display result dynamically based on distance size
            if (distanceInKm >= 1.0) {
                resultTextView.setText(String.format("Distance: %.2f km", distanceInKm));
            } else {
                resultTextView.setText(String.format("Distance: %.2f meters", distanceInMeters));
            }

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Please enter valid numeric coordinates", Toast.LENGTH_SHORT).show();
        }
    }
}