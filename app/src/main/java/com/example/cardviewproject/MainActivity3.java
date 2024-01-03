package com.example.cardviewproject;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;

import com.example.cardviewproject.databinding.ActivityMain2Binding;
import com.example.cardviewproject.databinding.ActivityMain3Binding;

public class MainActivity3 extends AppCompatActivity {
    private ActivityMain3Binding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main2);
        setContentView((binding = ActivityMain3Binding.inflate(getLayoutInflater())).getRoot());

        Uri path = Uri.parse( "android.resource://"+ getPackageName() + "/" + R.raw.animalesmarinos3);
        binding.videoActivity3.setVideoURI(path);
        //Toolbar
        setSupportActionBar(binding.toolbar3);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        //Habilitar los botones de control
        MediaController mediaController = new MediaController(this);
        binding.videoActivity3.setMediaController(mediaController);
        binding.videoActivity3.start();
    }
}