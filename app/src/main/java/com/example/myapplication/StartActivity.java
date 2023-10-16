package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class StartActivity extends AppCompatActivity {
    private boolean buttonClicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Button startButton = findViewById(R.id.startButton);
        Button exitButton = findViewById(R.id.exitButton);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void onGameScreen(View view) {
        if (!buttonClicked) {
            buttonClicked = true;
            Intent appScreen = new Intent(this, MainActivity.class);
            startActivity(appScreen);
        }
    }

    public void exitGame(View view) {
        finish();
    }


    @Override
    protected void onResume() {
        super.onResume();

        buttonClicked = false;
    }
}
