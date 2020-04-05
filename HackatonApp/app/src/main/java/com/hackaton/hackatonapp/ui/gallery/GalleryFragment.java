package com.hackaton.hackatonapp.ui.gallery;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.hackaton.hackatonapp.R;
import com.hackaton.hackatonapp.details.MainActivity;
import com.hackaton.hackatonapp.pojo.FlowerAdapter;
import com.hackaton.hackatonapp.pojo.Item;

import java.util.ArrayList;

public class GalleryFragment extends Fragment implements AdapterView.OnItemClickListener  {

    private GalleryViewModel galleryViewModel;
    ArrayList<Item> mFlowerDataSet = new ArrayList<>();
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Prepare DataSet
        mFlowerDataSet = prepareDataSet();

        //Initialize Grid View for programming
        GridView gridview = (GridView) view.findViewById(R.id.gridView);

        //Connect DataSet to Adapter
        FlowerAdapter flowerAdapter = new FlowerAdapter(getActivity(), mFlowerDataSet);

        //Now Connect Adapter To GridView
        gridview.setAdapter(flowerAdapter);

        //Add Listener For Grid View Item Click
        gridview.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        //Show Name Of The Flower
        Toast.makeText(getActivity(), mFlowerDataSet.get(position).getItemName(),
                Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
    }


    //Creating Data Set By Adding 6 flower objects
    private ArrayList<Item> prepareDataSet() {

        ArrayList<Item> flowerData = new ArrayList<>();

        Item flower;

        //1st Item
        flower = new Item();
        flower.setItemName("StarBuck");
        flower.setItemphoto(R.drawable.strabuk);
        flowerData.add(flower);

        //2nd Item
        flower = new Item();
        flower.setItemName("7Eleven");
        flower.setItemphoto(R.drawable.eleven);
        flowerData.add(flower);


        //3rd Item
        flower = new Item();
        flower.setItemName("H&M");
        flower.setItemphoto(R.drawable.hmm);
        flowerData.add(flower);


        //4th Item
        flower = new Item();
        flower.setItemName("KFC");
        flower.setItemphoto(R.drawable.kfc);
        flowerData.add(flower);


        //5th Item
        flower = new Item();
        flower.setItemName("PressByran");
        flower.setItemphoto(R.drawable.press);
        flowerData.add(flower);


        //6th Item
        flower = new Item();
        flower.setItemName("Circle");
        flower.setItemphoto(R.drawable.circle);
        flowerData.add(flower);

        //6th Item
        flower = new Item();
        flower.setItemName("ZARA");
        flower.setItemphoto(R.drawable.zara);
        flowerData.add(flower);


        return flowerData;

    }
}
