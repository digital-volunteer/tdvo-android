package com.hackaton.hackatonapp.details;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import com.hackaton.hackatonapp.R;
import com.hackaton.hackatonapp.pojo.CustomAdapter;
import com.hackaton.hackatonapp.pojo.DataModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<DataModel> dataModels;
    ListView listView;
    private static CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        listView=(ListView)findViewById(R.id.list);

        dataModels= new ArrayList<>();

        dataModels.add(new DataModel("Caramel", "€14", "","September 23, 2008",R.drawable.caramel));
        dataModels.add(new DataModel("Cherry", "€13", "2","February 9, 2009",R.drawable.cherry));
        dataModels.add(new DataModel("ColorFreak", "€10", "3","April 27, 2009",R.drawable.colorfreak));
        dataModels.add(new DataModel("CookiesScream","€10","4","September 15, 2009",R.drawable.cookiescream));
        dataModels.add(new DataModel("Mango", "€8", "5","October 26, 2009",R.drawable.mango));
        dataModels.add(new DataModel("Molten choco", "€12", "8","May 20, 2010",R.drawable.moltenchoco));
        dataModels.add(new DataModel("Nutella", "€11", "9","December 6, 2010",R.drawable.nutella));
        dataModels.add(new DataModel("Oreomale","€15","11","February 22, 2011",R.drawable.oreomale));
        dataModels.add(new DataModel("Pista", "€17", "14","October 18, 2011",R.drawable.pista));
        dataModels.add(new DataModel("Rainbow", "€15", "16","July 9, 2012",R.drawable.pista));


        adapter= new CustomAdapter(dataModels,getApplicationContext());

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                DataModel dataModel= dataModels.get(position);
                Intent a = new Intent(MainActivity.this,PaymentCormation.class);
                startActivity(a);



            }
        });
    }

}