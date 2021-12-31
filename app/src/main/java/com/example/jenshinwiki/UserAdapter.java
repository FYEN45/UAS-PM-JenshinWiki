package com.example.jenshinwiki;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class UserAdapter extends BaseAdapter {
    private static LayoutInflater inflater = null;
    private final Activity activity;
    private ArrayList<User> arraylist_data = new ArrayList<>();

    public UserAdapter(Activity a, ArrayList<User> d) {
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
            //Memanggil layout rows untuk menampilkan gambar dan nama user sesuai position
            vi = inflater.inflate(R.layout.user_item, null);
        }

        //Men-deklarasi dan menginisialisasikan komponen yang digunakan pada baris data user.
        TextView textViewName = vi.findViewById(R.id.textViewName);
        TextView textViewId = vi.findViewById(R.id.textViewId);

        //Mengambil data dari arrayList_data dan menyimpannya untuk ditampilkan.
        User users = arraylist_data.get(position);

        //Menampilkan data user yang telah diambil dari array list
        textViewId.setText(users.getId());
        textViewName.setText(users.getName());
        return vi;
    }
}
