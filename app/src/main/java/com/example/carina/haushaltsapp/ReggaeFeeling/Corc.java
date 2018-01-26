package com.example.carina.haushaltsapp.ReggaeFeeling;

import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.media.audiofx.BassBoost;
import android.media.audiofx.Virtualizer;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.ToggleButton;
import android.view.View;
import android.graphics.Color;

import com.example.carina.haushaltsapp.R;

import static com.example.carina.haushaltsapp.R.id.button1;


public class Corc extends AppCompatActivity {

    private Switch sReggaeFeeling;
    private MediaPlayer mpReggaeFeeling;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.corc);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        GifImageView gifImageView = (GifImageView) findViewById(R.id.corc);
        gifImageView.setGifImageResource(R.drawable.corc_animated);


        InitializeActivity();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (mpReggaeFeeling.isPlaying()){
                    mpReggaeFeeling.pause();
                }
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void InitializeActivity() {

        mpReggaeFeeling = MediaPlayer.create(this, R.raw.reggae_feeling_terrasound_de);
        mpReggaeFeeling.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mpReggaeFeeling.stop();
            }
        });
        mpReggaeFeeling.start();
        mpReggaeFeeling.setLooping(true);


    }


}