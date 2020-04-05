package com.hackaton.hackatonapp.pojo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hackaton.hackatonapp.R;

import java.util.ArrayList;




//This is a custom adapter. It has been extended from BaseAdapter because
//we need to overrider the getView function for changing the layout of each Grid View Item
public class FlowerAdapter extends BaseAdapter {

    private ArrayList<Item> mFlowerData = new ArrayList<>();
    private LayoutInflater mInflaterCatalogListItems;

    public FlowerAdapter(Context context, ArrayList<Item> flowerData) {
        mFlowerData = flowerData;
        mInflaterCatalogListItems = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    //This function will determine how many items to be displayed
    @Override
    public int getCount() {
        return mFlowerData.size();
    }

    @Override
    public Object getItem(int position) {
        return mFlowerData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    //This function will iterate through each object in the Data Set. This function will form each item in a Grid View
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if (convertView == null) {

            holder = new ViewHolder();
            convertView = mInflaterCatalogListItems.inflate(R.layout.adapter_flower, null);
            holder.sFlowerName = (TextView) convertView.findViewById(R.id.textView);
            holder.sFlowerPhotoPath = (ImageView) convertView.findViewById(R.id.photoView);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        //Change the content here
        if (mFlowerData.get(position) != null) {
            holder.sFlowerName.setText(mFlowerData.get(position).getItemName());
            holder.sFlowerPhotoPath.setImageResource(mFlowerData.get(position).getItemphoto());
        }

        return convertView;
    }

    //View Holder class used for reusing the same inflated view. It will decrease the inflation overhead @getView
    private static class ViewHolder {
        TextView sFlowerName;
        ImageView sFlowerPhotoPath;

    }

}