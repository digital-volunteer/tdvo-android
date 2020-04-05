package com.hackaton.hackatonapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class ActivityHelpOthers extends AppCompatActivity {

    Button btnNext;
    ProgressBar progressBar;
    EditText editText;
    String value;
    String fromwhere;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_others);
        btnNext = findViewById(R.id.et_password);
        progressBar = findViewById(R.id.prgLoading);
        editText = findViewById(R.id.et_email);
        String comefrom = getIntent().getExtras().getString("data");
        if(comefrom.equals("volunteer"))
        {
            fromwhere = "true";
        }else {
            fromwhere = "false";
        }
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               new RetrieveFeedTask().execute();
               value = editText.getText().toString();
                progressBar.setVisibility(View.VISIBLE);
            }
        });
    }

    class RetrieveFeedTask extends AsyncTask<Void, Void, String> {

        private Exception exception;

        protected void onPreExecute() {
            // progressBar.setVisibility(View.VISIBLE);

        }

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        protected String doInBackground(Void... urls) {
            //   String email = emailText.getText().toString();
            // Do some validation here
            try {

                URL url = new URL("http://ec2-52-57-33-254.eu-central-1.compute.amazonaws.com:8080/api/user/create");

                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("POST");
                urlConnection.setRequestProperty("Content-Type", "application/json");
                urlConnection.setDoOutput(true);

              //  String jsonInputString = { "personalNumber": "197406235500", "volunteer":true }:
                JsonObject a = new JsonObject();
                a.addProperty("personalNumber","12345566");
                a.addProperty("volunteer","true");
                urlConnection.connect();

                try {
                    try(OutputStream os = urlConnection.getOutputStream()) {
                        byte[] input = a.toString().getBytes("utf-8");
                        os.write(input, 0, input.length);
                    }
                    int date = urlConnection.getResponseCode();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line).append("\n");
                    }
                    bufferedReader.close();
                    return stringBuilder.toString();
                } finally {
                    urlConnection.disconnect();
                }
            } catch (Exception e) {
                Log.e("ERROR", e.getMessage(), e);
                return null;
            }
        }

        protected void onPostExecute(String response) {
            progressBar.setVisibility(View.GONE);
            if (response == null) {
                response = "THERE WAS AN ERROR";
            }
            Log.d("data",response);
            String comefrom = getIntent().getExtras().getString("data");
            if(comefrom.equals("volunteer"))
            {
                Intent a = new Intent(ActivityHelpOthers.this,ActivityHelpOtherOne.class);
                startActivity(a);
            }else {
                Intent a = new Intent(ActivityHelpOthers.this,RegsitartionOldpeople.class);
                startActivity(a);
            }
           // addDataToMap(response);
        }
    }
}
