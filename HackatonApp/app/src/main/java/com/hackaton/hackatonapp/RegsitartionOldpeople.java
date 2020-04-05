package com.hackaton.hackatonapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RegsitartionOldpeople extends AppCompatActivity {

    Button btnNext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regsitartion_oldpeople);
        btnNext = findViewById(R.id.btn_login);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
Intent a = new Intent(RegsitartionOldpeople.this,GalleryActivity.class);
startActivity(a);
            }
        });
    }
}
