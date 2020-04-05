package com.hackaton.hackatonapp.ui.home;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.hackaton.hackatonapp.R;
import com.hackaton.hackatonapp.pojo.ParkingData;

public class CustomInfoWindowGoogleMap implements GoogleMap.InfoWindowAdapter {

    private Context context;

    public CustomInfoWindowGoogleMap(Context ctx){
        context = ctx;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        View view = ((Activity)context).getLayoutInflater()
.inflate(R.layout.map_info_window, null);

      /*  TextView name_tv = view.findViewById(R.id.name);
        TextView details_tv = view.findViewById(R.id.details);
        ImageView img = view.findViewById(R.id.pic);

        TextView hotel_tv = view.findViewById(R.id.hotels);
        TextView food_tv = view.findViewById(R.id.food);
        TextView transport_tv = view.findViewById(R.id.transport);

        name_tv.setText(marker.getTitle());
        details_tv.setText(marker.getSnippet());*/

       // ParkingData infoWindowData = (ParkingData) marker.getTag();

       // int imageId = context.getResources().getIdentifier(infoWindowData.getImage().toLowerCase(),
         //       "drawable", context.getPackageName());
       // img.setImageResource(imageId);

       // hotel_tv.setText(infoWindowData.getParkingStartTime());
      //  food_tv.setText(infoWindowData.getParkingEndTime());
       // transport_tv.setText(infoWindowData.getElectricChargingAvl());

        return view;
    }
}