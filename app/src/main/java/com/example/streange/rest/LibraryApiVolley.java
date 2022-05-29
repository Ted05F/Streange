package com.example.streange.rest;

import android.content.Context;
import android.util.Log;
import androidx.annotation.Nullable;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.streange.MainActivity;
import com.example.streange.domain.Author;
import com.example.streange.domain.Book;
import com.example.streange.domain.Genre;
import com.example.streange.domain.mapper.AuthorMapper;
import com.example.streange.domain.mapper.BookMapper;
import com.example.streange.domain.mapper.GenreMapper;
import com.example.streange.nodb.NoDb;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LibraryApiVolley implements LibraryApi {

    public static final String API_TEST = "API_TEST";
    private final Context context;
    public static final String BASE_URL = "http://192.168.31.85:8086";
    private ErrorListener errorListener;

    public LibraryApiVolley(Context context) {
        this.context = context;

        errorListener = error -> Log.d(API_TEST, error.toString());
    }

    @Override
    public void fillBook() {

        RequestQueue requestQueue = Volley.newRequestQueue(context);

        String url = BASE_URL + "/book";

        JsonArrayRequest arrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        NoDb.BOOK_LIST.clear();

                        try {
                            for (int i = 0; i < response.length(); i++) {

                                JSONObject jsonObject = response.getJSONObject(i);
                                Genre genre = new GenreMapper().genreFromJson(jsonObject);

                                Author author = new AuthorMapper().authorFromBookJson(jsonObject);
                                Book book = new BookMapper().bookFromJson(jsonObject);
                                NoDb.BOOK_LIST.add(book);

                            }

                            ((MainActivity) context).updateAdapter();
                            Log.d(API_TEST, NoDb.BOOK_LIST.toString());

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                errorListener
        );

        requestQueue.add(arrayRequest);
    }

    @Override
    public void fillAuthor() {

        RequestQueue requestQueue = Volley.newRequestQueue(context);

        String url = BASE_URL + "/author";

        JsonArrayRequest arrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        NoDb.AUTHOR_LIST.clear();

                        try {
                            for (int i = 0; i < response.length(); i++) {

                                JSONObject jsonObject = response.getJSONObject(i);

                                Author author = new AuthorMapper().authorFromJson(jsonObject);
                                NoDb.AUTHOR_LIST.add(author);

                            }

                            Log.d(API_TEST, NoDb.AUTHOR_LIST.toString());

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                errorListener
        );

        requestQueue.add(arrayRequest);

    }

    @Override
    public void fillGenre() {

        RequestQueue requestQueue = Volley.newRequestQueue(context);

        String url = BASE_URL + "/genre";

        JsonArrayRequest arrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        NoDb.GENRE_LIST.clear();

                        try {
                            for (int i = 0; i < response.length(); i++) {

                                JSONObject jsonObject = response.getJSONObject(i);

                                Genre genre = new GenreMapper().genreFromJson(jsonObject);
                                NoDb.GENRE_LIST.add(genre);

                            }

                            Log.d(API_TEST, NoDb.GENRE_LIST.toString());

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                errorListener
        );

        requestQueue.add(arrayRequest);

    }

    @Override
    public void addBook(Book book) {

        RequestQueue requestQueue = Volley.newRequestQueue(context);

        String url = BASE_URL + "/book" ;

        StringRequest request = new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        fillBook();
                        Log.d(API_TEST, response);

                    }
                },
                errorListener
        ) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();

                params.put("nameBook", book.getName());
                params.put("nameAuthor", book.getAuthor().getName());
                params.put("nameGenre", book.getGenre().getName());

                return params;
            }
        };

        requestQueue.add(request);

    }

    @Override
    public void updateBook(int id, String newBookName, String newAuthorName, String newGenreName) {

        RequestQueue requestQueue = Volley.newRequestQueue(context);

        String url = BASE_URL + "/book/" + id;

        StringRequest request = new StringRequest(
                Request.Method.PUT,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        fillBook();
                        Log.d(API_TEST, response);

                    }
                },
                errorListener
        ) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();

                params.put("nameBook", newBookName);
                params.put("nameAuthor", newAuthorName);
                params.put("nameGenre", newGenreName);

                return params;
            }
        };

        requestQueue.add(request);

    }

    @Override
    public void deleteBook(int id) {

        RequestQueue requestQueue = Volley.newRequestQueue(context);

        String url = BASE_URL + "/book" + "/" + id;

        StringRequest request = new StringRequest(
                Request.Method.DELETE,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        fillBook();
                        Log.d(API_TEST, response);

                    }
                },
                errorListener
        );

        requestQueue.add(request);

    }
}