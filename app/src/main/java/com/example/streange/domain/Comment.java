package com.example.streange.domain;

public class Comment {

    private int id;

    private String content;


    public Comment(int id, String name) {
        this.id = id;
        this.content = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return content;
    }
}
