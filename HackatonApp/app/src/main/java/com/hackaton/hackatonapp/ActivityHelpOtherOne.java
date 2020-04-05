package com.hackaton.hackatonapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActivityHelpOtherOne extends AppCompatActivity {

    Button btnNext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_other_one);
         btnNext = findViewById(R.id.btn_login);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(ActivityHelpOtherOne.this,ActivityHelpOtherTwo.class);
                startActivity(a);
            }
        });


    }
}
