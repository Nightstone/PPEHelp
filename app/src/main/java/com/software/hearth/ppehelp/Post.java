package com.software.hearth.ppehelp;

import com.google.gson.annotations.SerializedName;

public class Post {

    @SerializedName("id")
    private int id;

    @SerializedName("title")
    private String title;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}
