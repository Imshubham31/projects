package com.vivacollege.medikart;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by indianrenters on 1/9/17.
 */

public class LandingAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<String> arrData;
    Typeface typeface;

    public LandingAdapter(Context context, ArrayList<String> arrData){
        this.context = context;
        this.arrData = arrData;
        typeface = Typeface.createFromAsset(context.getAssets(),"fonts/fontawesome-webfont.ttf");
    }

    @Override
    public int getCount() {
        return arrData.size();
    }

    @Override
    public Object getItem(int position) {
        return arrData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if(convertView==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.landing_items, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.medicineName = (TextView) convertView.findViewById(R.id.medicine_name);
            viewHolder.arrow = (TextView) convertView.findViewById(R.id.arrow);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.medicineName.setText(arrData.get(position));
        viewHolder.arrow.setText(context.getResources().getString(R.string.arrow));
        viewHolder.arrow.setTypeface(typeface);

        return convertView;
    }

    static class ViewHolder {
        TextView medicineName;
        TextView arrow;
    }
}
