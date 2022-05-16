package com.example.streange.nodb;

import com.example.streange.domain.Author;
import com.example.streange.domain.Book;
import com.example.streange.domain.Genre;

import java.util.ArrayList;
import java.util.List;

public class NoDb {
    private NoDb() {
    }

    public static final List<Book> BOOK_LIST = new ArrayList<>();
    public static final List<Author> AUTHOR_LIST = new ArrayList<>();
    public static final List<Genre> GENRE_LIST = new ArrayList<>();

}
