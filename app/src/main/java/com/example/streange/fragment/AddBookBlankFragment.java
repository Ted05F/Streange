package com.example.streange.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.fragment.app.Fragment;
import com.example.streange.R;
import com.example.streange.adapter.AuthorSpinnerAdaptor;
import com.example.streange.adapter.GenreSpinnerAdapter;
import com.example.streange.domain.Author;
import com.example.streange.domain.Book;
import com.example.streange.domain.Genre;
import com.example.streange.nodb.NoDb;
import com.example.streange.rest.LibraryApiVolley;


public class AddBookBlankFragment extends Fragment {

    private AppCompatSpinner sp_author, sp_genre;
    private AuthorSpinnerAdaptor authorSpinnerAdaptor;
    private GenreSpinnerAdapter genreSpinnerAdapter;
    private EditText et_bookName;
    private AppCompatButton btn_add;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_book_blank, container, false);

        sp_author = view.findViewById(R.id.sp_author);
        sp_genre = view.findViewById(R.id.sp_genre);
        btn_add = view.findViewById(R.id.btn_add);
        et_bookName = view.findViewById(R.id.et_bookName);

        authorSpinnerAdaptor = new AuthorSpinnerAdaptor(getContext(), NoDb.AUTHOR_LIST);
        sp_author.setAdapter(authorSpinnerAdaptor);
        genreSpinnerAdapter = new GenreSpinnerAdapter(getContext(), NoDb.GENRE_LIST);
        sp_genre.setAdapter(genreSpinnerAdapter);

        btn_add.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                new LibraryApiVolley(getContext()).addBook(
                        new Book(
                                et_bookName.getText().toString(),
                                ((Author)sp_author.getSelectedItem()),
                                ((Genre)sp_genre.getSelectedItem()),
                                null
                        )
                );

                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .remove(AddBookBlankFragment.this)
                        .commit();
            }
        });

        return view;
    }
}