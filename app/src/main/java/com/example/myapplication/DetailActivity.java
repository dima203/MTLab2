package com.example.myapplication;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class DetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView detailsTextView = findViewById(R.id.textViewDetails);

        String details = getIntent().getStringExtra("details");
        detailsTextView.setText(details);

    }
}
