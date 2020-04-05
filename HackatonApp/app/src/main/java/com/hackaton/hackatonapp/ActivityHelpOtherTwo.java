package com.hackaton.hackatonapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.hackaton.hackatonapp.maps.MainActivity;

public class ActivityHelpOtherTwo extends AppCompatActivity {


    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_other_two);
        button = findViewById(R.id.btn_login1234);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent obj = new Intent(ActivityHelpOtherTwo.this, MainActivity.class);
                startActivity(obj);
            }
        });
    }
}
