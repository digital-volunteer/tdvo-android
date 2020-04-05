package com.hackaton.hackatonapp;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FirstActivity extends Activity {

    Button btnHelpOthers, btnNeedHelp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        btnHelpOthers = findViewById(R.id.et_password);
        btnNeedHelp = findViewById(R.id.btn_login);
        btnHelpOthers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
 Intent a = new Intent(FirstActivity.this,ActivityHelpOthers.class);
 a.putExtra("data","volunteer");
 startActivity(a);
            }
        });

        btnNeedHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(FirstActivity.this,ActivityHelpOthers.class);
                a.putExtra("data","oldage");
                startActivity(a);
            }
        });
    }
}
