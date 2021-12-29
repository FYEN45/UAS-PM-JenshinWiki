package com.example.jenshinwiki;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MonsterAdapter extends BaseAdapter {
    private static LayoutInflater inflater = null;
    private final Activity activity;
    private ArrayList<Monster> arraylist_data = new ArrayList<>();


    public MonsterAdapter(Activity a, ArrayList<Monster> d) {
        activity = a;
        arraylist_data = d;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return arraylist_data.size();
    }

    @Override
    public Object getItem(int position) {
        return arraylist_data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        if (convertView == null) {
            vi = inflater.inflate(R.layout.layout_rows, null);
        }

        TextView textViewName = vi.findViewById(R.id.monsterName);
        ImageView img = vi.findViewById(R.id.monsterImage);

        Monster modelList = arraylist_data.get(position);
        textViewName.setText(modelList.getMonster_name());
        String url = modelList.getMonster_image();
        Picasso.get().load(url).into(img);
        return vi;
    }
}