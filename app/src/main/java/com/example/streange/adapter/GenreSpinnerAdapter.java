package com.example.streange.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.streange.R;
import com.example.streange.domain.Genre;
import com.example.streange.nodb.NoDb;

import java.util.List;

public class GenreSpinnerAdapter extends ArrayAdapter<Genre> {
    public GenreSpinnerAdapter(
            @NonNull Context context,
            @NonNull List<Genre> objects) {

        super(context, R.layout.spinner_item, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null){
            convertView= LayoutInflater.from(getContext())
                    .inflate(R.layout.spinner_item, null);
        }

        ((TextView)convertView.findViewById(R.id.tv_spinner_item))
                .setText(NoDb.GENRE_LIST.get(position).getName());

        return convertView;
    }
}
