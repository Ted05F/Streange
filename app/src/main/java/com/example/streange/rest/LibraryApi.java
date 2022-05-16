package com.example.streange.rest;

import com.example.streange.domain.Book;

public interface LibraryApi {

    void fillBook();

    void fillAuthor();

    void fillGenre();

    void addBook(Book book);

    void updateBook(
            int id,
            String newBookName,
            String newAuthorName,
            String newGenreName
    );

    void deleteBook(int id);
}
