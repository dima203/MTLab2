package com.example.myapplication;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class StartActivity extends AppCompatActivity {
    private MediaPlayer mediaPlayer;

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
            mediaPlayer.stop();
            Intent appScreen = new Intent(this, MainActivity.class);
            startActivity(appScreen);
        }
    }
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }
    public void exitGame(View view) {
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
        finish();
    }


    @Override
    protected void onResume() {
        super.onResume();

        if (mediaPlayer == null || !mediaPlayer.isPlaying()) {
            mediaPlayer = MediaPlayer.create(this, R.raw.sound);
            mediaPlayer.start();
        }

        buttonClicked = false;
    }
}
