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

public class ItemAdapter extends BaseAdapter {
    private static LayoutInflater inflater = null;
    private final Activity activity;
    private ArrayList<Item> arraylist_data = new ArrayList<>();


    public ItemAdapter(Activity a, ArrayList<Item> d) {
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
            //Memanggil layout rows untuk menampilkan gambar dan nama item sesuai position
            vi = inflater.inflate(R.layout.layout_rows, null);
        }
        //Memanggil komponen yang ada pada layout rows, menggunakan inflater
        TextView textViewName = vi.findViewById(R.id.textViewName);
        ImageView img = vi.findViewById(R.id.monsterImage);

        //Memberikan nilai pada komponen, yang diambil dari database menggunakan Getter pada Item.java
        Item itemList = arraylist_data.get(position);
        textViewName.setText(itemList.getItem_name());
        String url = itemList.getItem_image();
        /*Mengkonversikan image yang tadinya berupa link url,
        sehingga dapat ditampilkan pada image view, memakai library Picasso*/
        Picasso.get().load(url).into(img);
        return vi;
    }
}