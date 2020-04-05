package com.hackaton.hackatonapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.hackaton.hackatonapp.details.MainActivity;
import com.hackaton.hackatonapp.pojo.FlowerAdapter;
import com.hackaton.hackatonapp.pojo.Item;
import com.hackaton.hackatonapp.ui.gallery.GalleryViewModel;

import java.util.ArrayList;

public class GalleryActivity extends AppCompatActivity implements AdapterView.OnItemClickListener  {

    private GalleryViewModel galleryViewModel;
    ArrayList<Item> mFlowerDataSet = new ArrayList<>();
    private Button gallarty;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_gallery);

        mFlowerDataSet = prepareDataSet();

        //Initialize Grid View for programming
        GridView gridview = (GridView) findViewById(R.id.gridView);

        //Connect DataSet to Adapter
        FlowerAdapter flowerAdapter = new FlowerAdapter(this, mFlowerDataSet);

        //Now Connect Adapter To GridView
        gridview.setAdapter(flowerAdapter);

        //Add Listener For Grid View Item Click
        gridview.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        //Show Name Of The Flower
        Intent a = new Intent(GalleryActivity.this,FinalOrderActivity.class);
        startActivity(a);
        Toast.makeText(GalleryActivity.this, mFlowerDataSet.get(position).getItemName(),
                Toast.LENGTH_SHORT).show();

    }


    //Creating Data Set By Adding 6 flower objects
    private ArrayList<Item> prepareDataSet() {

        ArrayList<Item> flowerData = new ArrayList<>();

        Item flower;

        //1st Item
        flower = new Item();
        flower.setItemName("Groceries");
        flower.setItemphoto(R.drawable.food);
        flowerData.add(flower);

        //2nd Item
        flower = new Item();
        flower.setItemName("Transport");
        flower.setItemphoto(R.drawable.shopping);
        flowerData.add(flower);


        //3rd Item
        flower = new Item();
        flower.setItemName("Medicine");
        flower.setItemphoto(R.drawable.medicine);
        flowerData.add(flower);



        //5th Item
        flower = new Item();
        flower.setItemName("Others");
        flower.setItemphoto(R.drawable.other);
        flowerData.add(flower);




        return flowerData;

    }
}
