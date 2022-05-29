package com.example.streange.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.fragment.app.Fragment;
import com.example.streange.R;
import com.example.streange.adapter.AuthorSpinnerAdaptor;
import com.example.streange.adapter.BookAdapter;
import com.example.streange.adapter.GenreSpinnerAdapter;
import com.example.streange.domain.Author;
import com.example.streange.domain.Book;
import com.example.streange.domain.Genre;
import com.example.streange.nodb.NoDb;
import com.example.streange.rest.LibraryApiVolley;


public class ChangeBookBlankFragment extends Fragment {

    private AppCompatSpinner sp_author, sp_genre;
    private AuthorSpinnerAdaptor authorSpinnerAdaptor;
    private GenreSpinnerAdapter genreSpinnerAdapter;
    private EditText et_bookName;
    private AppCompatButton btn_change;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_change_book_blank, container, false);

        Book book = (Book) getArguments().getSerializable(BookAdapter.BOOK_KEY);

        sp_author = view.findViewById(R.id.sp_author);
        sp_genre = view.findViewById(R.id.sp_genre);
        btn_change = view.findViewById(R.id.btn_change);
        et_bookName = view.findViewById(R.id.et_bookName);

        et_bookName.setText(book.getName());

        authorSpinnerAdaptor = new AuthorSpinnerAdaptor(getContext(), NoDb.AUTHOR_LIST);
        sp_author.setAdapter(authorSpinnerAdaptor);
        genreSpinnerAdapter = new GenreSpinnerAdapter(getContext(), NoDb.GENRE_LIST);
        sp_genre.setAdapter(genreSpinnerAdapter);

        btn_change.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                new LibraryApiVolley(getContext()).updateBook(

                        book.getId(),
                        et_bookName.getText().toString(),
                        ((Author) sp_author.getSelectedItem()).getName(),
                        ((Genre) sp_genre.getSelectedItem()).getName()
                );

                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .remove(ChangeBookBlankFragment.this)
                        .commit();
            }
        });

        return view;
    }
}