package com.example.streange.domain.mapper;

import com.example.streange.domain.Author;
import org.json.JSONException;
import org.json.JSONObject;

public class AuthorMapper {

    public static Author authorFromJson (JSONObject jsonObject) {

        Author author = null;

        try {
            author = new Author(
                    jsonObject.getInt("id"),
                    jsonObject.getString("name")
                    );
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return author;
    }

    public static Author authorFromBookJson (JSONObject jsonObject){
        Author author = null;

        try {
            author = new Author(
                    jsonObject.getJSONObject("authorDto").getInt("id"),
                    jsonObject.getJSONObject("authorDto").getString("name")
            );
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return author;
    }
}
