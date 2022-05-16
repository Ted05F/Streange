package com.example.streange.domain.mapper;

import com.example.streange.domain.Book;
import org.json.JSONException;
import org.json.JSONObject;

public class BookMapper {

    public static Book bookFromJson(JSONObject jsonObject) {

        Book book = null;

        try {
            book = new Book(
                    jsonObject.getInt("id"),
                    jsonObject.getString("name"),
                    AuthorMapper.authorFromBookJson(jsonObject),
                    GenreMapper.genreFromBookJson(jsonObject),
                    null
            );
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return book;
    }
}
