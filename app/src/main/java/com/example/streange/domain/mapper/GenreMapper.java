package com.example.streange.domain.mapper;

import com.example.streange.domain.Genre;
import org.json.JSONException;
import org.json.JSONObject;

public class GenreMapper {

    public static Genre genreFromJson (JSONObject jsonObject) {

        Genre genre = null;

        try {
            genre = new Genre(
                    jsonObject.getInt("id"),
                    jsonObject.getString("name")
            );
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return genre;
    }

    public static Genre genreFromBookJson (JSONObject jsonObject){
        Genre genre = null;

        try {
            genre = new Genre(
                    jsonObject.getJSONObject("genreDto").getInt("id"),
                    jsonObject.getJSONObject("genreDto").getString("name")
            );
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return genre;
    }
}
