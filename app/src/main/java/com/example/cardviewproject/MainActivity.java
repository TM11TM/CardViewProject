package com.example.cardviewproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.cardviewproject.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        setContentView((binding = ActivityMainBinding.inflate(getLayoutInflater())).getRoot());

        binding.btn1.setOnClickListener(view -> {
            openReading(view);
        });
        binding.btn2.setOnClickListener(view -> {
            Intent i = new Intent(this, MainActivity3.class);
            startActivity(i);
        });
        binding.btn3.setOnClickListener(view -> {
            Intent i = new Intent(this, MainActivity4.class);
            startActivity(i);
        });
    }

    public void openReading(View view){
        try {
            Intent i = new Intent(this, MainActivity2.class);
            startActivity(i);
        }catch (Exception e){
            Log.e("CardView", "Error: "+ e.getMessage());
        }
    }

}