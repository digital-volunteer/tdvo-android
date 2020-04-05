package com.hackaton.hackatonapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.hackaton.hackatonapp.details.PaymentCormation;
import com.hackaton.hackatonapp.pojo.ParkingData;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

   private GoogleMap mMap;
   ArrayList<Integer> integers = new ArrayList<>();
   ArrayList<ParkingData> parkingDataList = new ArrayList<>();

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.mapxml);
      integers.add(R.drawable.book);
      integers.add(R.drawable.unbook);
      integers.add(R.drawable.book);
      integers.add(R.drawable.unbook);
      integers.add(R.drawable.book);
      integers.add(R.drawable.unbook);
      integers.add(R.drawable.book);
      integers.add(R.drawable.unbook);
      integers.add(R.drawable.book);
      integers.add(R.drawable.unbook);
      // Obtain the SupportMapFragment and get notified when the map is ready to be used.
      SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
         .findFragmentById(R.id.map);
      mapFragment.getMapAsync(this);
   }
   
   /**
      * Manipulates the map once available.
      * This callback is triggered when the map is ready to be used.
      * This is where we can add markers or lines, add listeners or move the camera.
      * In this case, we just add a marker near Sydney, Australia.
      * If Google Play services is not installed on the device. 
      * This method will only be triggered once the user has installed 
         Google Play services and returned to the app.
   */
  
   @Override
   public void onMapReady(GoogleMap googleMap) {

      mMap = googleMap;
      new RetrieveFeedTask().execute();
      // Add a marker in Sydney and move the camera
      LatLng TutorialsPoint = new LatLng(21, 57);


      mMap.addMarker(new 
         MarkerOptions().position(TutorialsPoint).title("Tutorialspoint.com"));
      mMap.moveCamera(CameraUpdateFactory.newLatLng(TutorialsPoint));
   }

   @Override
   public boolean onMarkerClick(Marker marker) {
      Log.d("mahabir","data..........");
      Toast.makeText(MapsActivity.this,"data", Toast.LENGTH_SHORT).show();

      CustomInfoWindowGoogleMap customInfoWindowGoogleMap = new CustomInfoWindowGoogleMap(MapsActivity.this);
      customInfoWindowGoogleMap.getInfoContents(marker);
      return false;
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
                    .snippet("Volunteer")
                    .title("Rohan ");


         //   CustomInfoWindowGoogleMap customInfoWindow = new CustomInfoWindowGoogleMap(this);
           // mMap.setInfoWindowAdapter(customInfoWindow);
            // map.addMarker(new MarkerOptions().position(latLng).title("price" + parkingDataList.get(i).getParkingCost() + "PS:" + parkingDataList.get(i).getParkingStartTime() + "PE:" + parkingDataList.get(i).getParkingEndTime()));
            mMap.addMarker(markerOptions);
            mMap.setInfoWindowAdapter(new CustomInfoWindowGoogleMap(MapsActivity.this));
            mMap.setOnMarkerClickListener(this);
            mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
            mMap.moveCamera(CameraUpdateFactory.zoomTo(18));

         }
      }
      catch(JSONException e){

      }
   }
}