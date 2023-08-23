package com.darkness.sparkwomen;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.material.snackbar.Snackbar;

public class FakeCallActivity extends AppCompatActivity {
    Button start, stop;
    MediaPlayer mediaPlayer;
//     MediaPlayer xy;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(FakeCallActivity.this, MainActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fakecall);
        stop = findViewById(R.id.stopServiceoffakecall);
        start = findViewById(R.id.startServiceoffakecall);
        start.setOnClickListener(this::startServiceV);
        stop.setOnClickListener(this::stopService);

    }


    public void stopService(View view) {
        if(mediaPlayer!=null)
        mediaPlayer.release();

       mediaPlayer=null;
    }

    public void startServiceV(View view) {
            mediaPlayer = MediaPlayer.create(this, R.raw.telephone);

       mediaPlayer.start();

    }
}

