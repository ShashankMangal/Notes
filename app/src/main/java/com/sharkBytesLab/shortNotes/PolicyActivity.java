package com.sharkBytesLab.shortNotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.sharkBytesLab.shortNotes.databinding.ActivityPolicyBinding;

public class PolicyActivity extends AppCompatActivity {

    private ActivityPolicyBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPolicyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        try {
            getSupportActionBar().hide();
            binding.policyWebView.getSettings().setJavaScriptEnabled(true);
            binding.policyWebView.loadUrl("file:///android_asset/policy.html");
        } catch (Exception e) {
            e.printStackTrace();
        }


        binding.policyBackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              onBackPressed();

            }
        });

    }

}