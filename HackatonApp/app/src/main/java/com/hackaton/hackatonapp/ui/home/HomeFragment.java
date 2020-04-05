package com.hackaton.hackatonapp.ui.home;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.hackaton.hackatonapp.R;
import com.hackaton.hackatonapp.details.PaymentCormation;
import com.hackaton.hackatonapp.details.Paymentcomplted;
import com.hackaton.hackatonapp.pojo.ParkingData;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
public class HomeFragment  extends Fragment {
    MapView mapView;
    GoogleMap map;
    ArrayList<ParkingData> parkingDataList = new ArrayList<>();
    private Button distance, cost, availablity;
    ArrayList<Integer> integers = new ArrayList<>();




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        //distance = v.findViewById(R.id.button);
        //cost = v.findViewById(R.id.button2);
        //availablity = v.findViewById(R.id.button3);
        integers.add(R.drawable.ps1);
        integers.add(R.drawable.ps2);
        integers.add(R.drawable.ps3);
        integers.add(R.drawable.ps4);
        integers.add(R.drawable.ps5);
        integers.add(R.drawable.ps6);
        integers.add(R.drawable.ps7);
        integers.add(R.drawable.ps8);
        integers.add(R.drawable.ps9);
        integers.add(R.drawable.ps10);
        // Gets the MapView from the XML layout and creates it

        MapsInitializer.initialize(getActivity());

        switch (GooglePlayServicesUtil.isGooglePlayServicesAvailable(getActivity())) {
            case ConnectionResult.SUCCESS:
               // Toast.makeText(getActivity(), "SUCCESS", Toast.LENGTH_SHORT).show();
                mapView = (MapView) v.findViewById(R.id.map);
                mapView.onCreate(savedInstanceState);
                // Gets to GoogleMap from the MapView and does initialization stuff
                if (mapView != null) {
                    map = mapView.getMap();
                    map.getUiSettings().setMyLocationButtonEnabled(false);
                    map.setMyLocationEnabled(true);
                    new RetrieveFeedTask().execute();
                }
                break;
            case ConnectionResult.SERVICE_MISSING:
                Toast.makeText(getActivity(), "SERVICE MISSING", Toast.LENGTH_SHORT).show();
                break;
            case ConnectionResult.SERVICE_VERSION_UPDATE_REQUIRED:
                Toast.makeText(getActivity(), "UPDATE REQUIRED", Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(getActivity(), GooglePlayServicesUtil.isGooglePlayServicesAvailable(getActivity()), Toast.LENGTH_SHORT).show();
        }

      /*  distance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                distance.setBackgroundColor(getActivity().getResources().getColor(R.color.colorgreen));
                cost.setBackgroundColor(getActivity().getResources().getColor(R.color.colorlightgreen));
                availablity.setBackgroundColor(getActivity().getResources().getColor(R.color.colorlightgreen));
                if (map != null) { //prevent crashing if the map doesn't exist yet (eg. on starting activity)
                    map.clear();
                }

            }
        });
        cost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cost.setBackgroundColor(getActivity().getResources().getColor(R.color.colorgreen));
                distance.setBackgroundColor(getActivity().getResources().getColor(R.color.colorlightgreen));
                availablity.setBackgroundColor(getActivity().getResources().getColor(R.color.colorlightgreen));

            }
        });

        availablity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                availablity.setBackgroundColor(getActivity().getResources().getColor(R.color.colorgreen));
                distance.setBackgroundColor(getActivity().getResources().getColor(R.color.colorlightgreen));
                cost.setBackgroundColor(getActivity().getResources().getColor(R.color.colorlightgreen));

            }
        });*/
        // Updates the location and zoom of the MapView

        return v;
    }

    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    class RetrieveFeedTask extends AsyncTask<Void, Void, String> {

        private Exception exception;

        protected void onPreExecute() {
            // progressBar.setVisibility(View.VISIBLE);

        }

        protected String doInBackground(Void... urls) {
            //   String email = emailText.getText().toString();
            // Do some validation here
            try {
                URL url = new URL("https://my.api.mockaroo.com/my_saved_schema.json?key=f15691c0");
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                try {
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
            if (response == null) {
                response = "THERE WAS AN ERROR";
            }
           addDataToMap(response);
        }
    }



    private void addDataToMap(String jsonData) {
        try {
            Gson gson = new Gson();
            JSONArray jsonArray = new JSONArray(jsonData);
            for (int i = 0; i < jsonArray.length(); i++) {
                ParkingData data = gson.fromJson(jsonArray.get(i).toString(), ParkingData.class);
                parkingDataList.add(data);
                LatLng latLng = new LatLng(parkingDataList.get(i).getLongitude(), parkingDataList.get(i).getLatitude());

                final MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(latLng)
                        .icon(BitmapDescriptorFactory.fromResource(integers.get(i)))
                        .flat(true)
                        .snippet("Parking Start Time: "+parkingDataList.get(i).getParkingStartTime()+"Parking End Time:" + parkingDataList.get(i).getParkingEndTime() +"Chance "+parkingDataList.get(i).getChance()+" %")
                        .title("Price "+parkingDataList.get(i).getParkingCost());



                map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {

                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            public void run() {
                                Intent a = new Intent(getActivity(),PaymentCormation.class);
                                startActivity(a);
                            }
                        }, 10000);
                      return false;
                    }
                });
              // map.addMarker(new MarkerOptions().position(latLng).title("price" + parkingDataList.get(i).getParkingCost() + "PS:" + parkingDataList.get(i).getParkingStartTime() + "PE:" + parkingDataList.get(i).getParkingEndTime()));
                map.addMarker(markerOptions);
                map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                map.moveCamera(CameraUpdateFactory.zoomTo(18));

            }
        }
        catch(JSONException e){

        }
    }
}

