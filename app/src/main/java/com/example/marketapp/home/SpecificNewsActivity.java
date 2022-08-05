package com.example.marketapp.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import com.example.marketapp.ui.MainActivity;
import com.example.marketapp.R;
import com.example.marketapp.databinding.ActivitySpecificNewsBinding;

public class SpecificNewsActivity extends AppCompatActivity {

    ActivitySpecificNewsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySpecificNewsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.webView.loadUrl(getIntent().getStringExtra("news_url"));
        binding.details.setText("By: " + getIntent().getStringExtra("news_author"));

        getSupportActionBar().setTitle("News");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.orange)));
        getWindow().setStatusBarColor(getResources().getColor(R.color.black));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}