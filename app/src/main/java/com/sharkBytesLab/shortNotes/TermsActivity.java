package com.sharkBytesLab.shortNotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.sharkBytesLab.shortNotes.databinding.ActivityTermsBinding;

public class TermsActivity extends AppCompatActivity {

    private ActivityTermsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTermsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        try {
            getSupportActionBar().hide();
            binding.termsWebView.getSettings().setJavaScriptEnabled(true);
            binding.termsWebView.loadUrl("file:///android_asset/terms.html");
        } catch (Exception e) {
            e.printStackTrace();
        }

        binding.termsBackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(TermsActivity.this, MainActivity.class);
                startActivity(intent);

            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        startActivity(new Intent(TermsActivity.this, MainActivity.class));

    }
}