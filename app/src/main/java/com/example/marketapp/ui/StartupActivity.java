package com.example.marketapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import com.example.marketapp.R;
import com.example.marketapp.databinding.ActivityStartupBinding;

public class StartupActivity extends AppCompatActivity {

    ActivityStartupBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStartupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getWindow().setStatusBarColor(getResources().getColor(R.color.light_orange));

        binding.introTitle.setAlpha(0f);
        binding.introTitle.animate().alpha(1f).setDuration(2000).start();

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(StartupActivity.this, MainActivity.class));
                finish();
            }
        }, 3000);
    }
}