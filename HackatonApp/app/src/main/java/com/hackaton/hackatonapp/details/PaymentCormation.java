package com.hackaton.hackatonapp.details;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.hackaton.hackatonapp.R;

public class PaymentCormation extends AppCompatActivity {

    ConstraintLayout constraintLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_cormation);
        constraintLayout = (ConstraintLayout)findViewById(R.id.abcdef);
        constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
          Intent a = new Intent(PaymentCormation.this,Paymentcomplted.class);
          startActivity(a);
          finish();
            }
        });


    }
}
