package com.example.streange.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import com.example.streange.R;
import com.example.streange.adapter.BookAdapter;
import com.example.streange.domain.Book;


public class ChangeBookBlankFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_change_book_blank, container, false);

        Book book = (Book)getArguments().getSerializable(BookAdapter.BOOK_KEY);

        Toast.makeText(getContext(), book.toString(), Toast.LENGTH_SHORT).show();
        return view;
    }
}