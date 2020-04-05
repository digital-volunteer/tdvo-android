package com.hackaton.hackatonapp.ui.slideshow;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
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
import com.hackaton.hackatonapp.ui.gallery.GalleryViewModel;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class SlideshowFragment extends Fragment implements AdapterView.OnItemClickListener {

    private SlideshowViewModel slideshowViewModel;
    private GalleryViewModel galleryViewModel;
    ArrayList<Item> mFlowerDataSet = new ArrayList<>();
    String value;
    public View onCreateView(@NonNull LayoutInflater inflater,

                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                ViewModelProviders.of(this).get(SlideshowViewModel.class);
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);
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
        Toast.makeText(getActivity(), "Command Successfully Executed",
                Toast.LENGTH_SHORT).show();
        if(position == 0){
            value = "lock";
        }else if(position == 1){
            value = "unlock";
        }
        else if(position == 2){
            value = "doors";
        }
        else if(position == 3){
            value = "odometer";
        }
        else if(position == 4){
            value = "location";
        }
        else if(position == 5){
            value = "trips";
        }
        else if(position == 6){
            value = "honk";
        }

        new RetrieveFeedTask1().execute();

    }

    //Creating Data Set By Adding 6 flower objects
    private ArrayList<Item> prepareDataSet() {
        ArrayList<Item> flowerData = new ArrayList<>();
        Item flower;

        //1st Item
        flower = new Item();
        flower.setItemName("CAR Lock");
        flower.setItemphoto(R.drawable.locks);
        flowerData.add(flower);

        //2nd Item
        flower = new Item();
        flower.setItemName("Car Unlock");
        flower.setItemphoto(R.drawable.unlock);
        flowerData.add(flower);


        //3rd Item
        flower = new Item();
        flower.setItemName("Doors");
        flower.setItemphoto(R.drawable.doors);
        flowerData.add(flower);


        //4th Item
        flower = new Item();
        flower.setItemName("Vehicle Speed");
        flower.setItemphoto(R.drawable.speed);
        flowerData.add(flower);


        //5th Item
        flower = new Item();
        flower.setItemName("Location");
        flower.setItemphoto(R.drawable.location);
        flowerData.add(flower);


        //6th Item
        flower = new Item();
        flower.setItemName("Previous Trips");
        flower.setItemphoto(R.drawable.trips);
        flowerData.add(flower);

        //6th Item
        flower = new Item();
        flower.setItemName("Honk");
        flower.setItemphoto(R.drawable.honk);
        flowerData.add(flower);


        return flowerData;

    }


    class RetrieveFeedTask1 extends AsyncTask<Void, Void, String> {

        private Exception exception;

        protected void onPreExecute() {
            // progressBar.setVisibility(View.VISIBLE);

        }

        protected String doInBackground(Void... urls) {
            //   String email = emailText.getText().toString();
            // Do some validation here

            try {
                URL url = new URL("https://gw.qa.consumer.api.volvocars.com/connectedcar/vehicles/YV1XZ13BCJ2000282/"+value);
                Log.i("INFO", url.toString());
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("POST");
                urlConnection.addRequestProperty("Authorization", "Bearer eyJhbGciOiJSUzI1NiIsImtpZCI6IlNJR05FRENFUlQifQ.eyJzY29wZSI6WyJ2ZWhpY2xlOnVubG9jayIsInZlaGljbGU6Y2FwYWJpbGl0aWVzIiwidmVoaWNsZTphdHRyaWJ1dGVzIiwidmVoaWNsZTpob25rX2JsaW5rIiwiYXBwb2ludG1lbnQiLCJ2ZWhpY2xlOmxvY2siLCJjYXJlX2J5X3ZvbHZvOmZpbmFuY2lhbF9pbmZvcm1hdGlvbjpwYXltZW50X21ldGhvZCIsInZlaGljbGU6Y2xpbWF0aXphdGlvbiIsInZlaGljbGU6ZW5naW5lX3N0YXJ0IiwidmVoaWNsZTphdHRyaWJ1dGVzOndyaXRlIiwidmVoaWNsZTpkb29yc19zdGF0dXMiLCJ2ZWhpY2xlOmxvY2tfc3RhdHVzIiwidmVoaWNsZTplbmdpbmVfc3RhdHVzIiwidHNwX2N1c3RvbWVyX2FwaTphbGwiLCJ2ZWhpY2xlOmNsaW1hdGl6YXRpb25fY2FsZW5kYXIiLCJhcHBvaW50bWVudDp3cml0ZSIsImNhcmVfYnlfdm9sdm86c3Vic2NyaXB0aW9uOnJlYWQiLCJjYXJlX2J5X3ZvbHZvOmZpbmFuY2lhbF9pbmZvcm1hdGlvbjppbnZvaWNlOnJlYWQiLCJ2ZWhpY2xlOmxvY2F0aW9uIiwib3JkZXI6YXR0cmlidXRlcyIsIm9wZW5pZCIsInByb2ZpbGUiLCJjdXN0b21lcjphdHRyaWJ1dGVzIiwidm9sdm9fb25fY2FsbDphbGwiLCJ2ZWhpY2xlOm1haW50ZW5hbmNlX3N0YXR1cyIsInZlaGljbGU6b2RvbWV0ZXJfc3RhdHVzIiwiY3VzdG9tZXI6YXR0cmlidXRlczp3cml0ZSIsInZlaGljbGU6Y29ubmVjdGl2aXR5X3N0YXR1cyIsImNhcmVfYnlfdm9sdm86Y3VzdG9tZXI6aWRlbnRpdHkiLCJ2ZWhpY2xlOmZ1ZWxfc3RhdHVzIiwidmVoaWNsZTp0cmlwcyJdLCJjbGllbnRfaWQiOiJNcTUyRjQiLCJncm50aWQiOiI0SlNLOU0yb1Vpd3dCOFFMbjZpNnpwMmdvSFNsZmRrTCIsImlzcyI6Imh0dHBzOi8vdm9sdm9pZC5ldS5xYS52b2x2b2NhcnMuY29tIiwiYXVkIjoiTXE1MkY0IiwiZmlyc3ROYW1lIjoiWXV0ZWvDpSIsImxhc3ROYW1lIjoiTmlvbmlvdHJlc3NvbiIsInN1YiI6Ijk2NjNmZDhkLWQzMTItNGIyNS1iMDY2LTI1MzFkZDI0MTg3ZiIsInNjb3BlcyI6WyJ2ZWhpY2xlOnVubG9jayIsInZlaGljbGU6Y2FwYWJpbGl0aWVzIiwidmVoaWNsZTphdHRyaWJ1dGVzIiwidmVoaWNsZTpob25rX2JsaW5rIiwiYXBwb2ludG1lbnQiLCJ2ZWhpY2xlOmxvY2siLCJjYXJlX2J5X3ZvbHZvOmZpbmFuY2lhbF9pbmZvcm1hdGlvbjpwYXltZW50X21ldGhvZCIsInZlaGljbGU6Y2xpbWF0aXphdGlvbiIsInZlaGljbGU6ZW5naW5lX3N0YXJ0IiwidmVoaWNsZTphdHRyaWJ1dGVzOndyaXRlIiwidmVoaWNsZTpkb29yc19zdGF0dXMiLCJ2ZWhpY2xlOmxvY2tfc3RhdHVzIiwidmVoaWNsZTplbmdpbmVfc3RhdHVzIiwidHNwX2N1c3RvbWVyX2FwaTphbGwiLCJ2ZWhpY2xlOmNsaW1hdGl6YXRpb25fY2FsZW5kYXIiLCJhcHBvaW50bWVudDp3cml0ZSIsImNhcmVfYnlfdm9sdm86c3Vic2NyaXB0aW9uOnJlYWQiLCJjYXJlX2J5X3ZvbHZvOmZpbmFuY2lhbF9pbmZvcm1hdGlvbjppbnZvaWNlOnJlYWQiLCJ2ZWhpY2xlOmxvY2F0aW9uIiwib3JkZXI6YXR0cmlidXRlcyIsIm9wZW5pZCIsInByb2ZpbGUiLCJjdXN0b21lcjphdHRyaWJ1dGVzIiwidm9sdm9fb25fY2FsbDphbGwiLCJ2ZWhpY2xlOm1haW50ZW5hbmNlX3N0YXR1cyIsInZlaGljbGU6b2RvbWV0ZXJfc3RhdHVzIiwiY3VzdG9tZXI6YXR0cmlidXRlczp3cml0ZSIsInZlaGljbGU6Y29ubmVjdGl2aXR5X3N0YXR1cyIsImNhcmVfYnlfdm9sdm86Y3VzdG9tZXI6aWRlbnRpdHkiLCJ2ZWhpY2xlOmZ1ZWxfc3RhdHVzIiwidmVoaWNsZTp0cmlwcyJdLCJlbWFpbCI6InFhLnl0azkzMy5zZUBjb25uZWN0ZWR2b2x2b2NhcnMuc2UiLCJleHAiOjE1ODI3OTgzNTZ9.AdzUcBLEJVVtoOoZgybrfB3eZxAsT0GFKG2XDSCSgPLifoI_aoUpqTdBZS1JniFsXPgzMFCkgk5jSbUoatfOw1ue_8UoxSPxrYWcQ64u2L3Qjn-GkHseE_rxz9Ui2yYpNTX08FQX3WSOS4C6cIlqo83IC5NKLw58v2GkLhBjS_NWXZAOrLsGtao9lZbr4hDr00yeY0-JgBaAu2_f8xjirseTFmfRce0qqlZTWZ411v-z8ydSznnxjKmyR760-3iTabjtXajKNIKPpDTx9e8dQwm6bbDlFQpvMzIM_z3l-ycH8w_j2JyX0gX1Lmy11a8I6i5LlLtVGSXEBwIgbi6dwQ");
                urlConnection.addRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
                urlConnection.addRequestProperty("Cache-Control", "no-cache");
                urlConnection.addRequestProperty("VCC-Api-Key", "eab959084eb6417bbf8284a6edadf57e");
                try {

                    if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                        String header = urlConnection.getHeaderField("requestId");
                        Log.i("INFO", header);

                    }
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line).append("\n");
                    }
                    bufferedReader.close();
                    return stringBuilder.toString();
                }
                finally{
                    urlConnection.disconnect();
                }
            }
            catch(Exception e) {
                Log.e("ERROR", e.getMessage(), e);
                return null;
            }
        }

        protected void onPostExecute(String response) {
            if(response == null) {
                response = "THERE WAS AN ERROR";
            }
            Log.i("INFO", response);



            // progressBar.setVisibility(View.GONE);



        }
    }
}
