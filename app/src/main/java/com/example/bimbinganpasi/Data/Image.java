package com.example.bimbinganpasi.Data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Image {

    @SerializedName("id")
    @Expose
    private String no_user_id;
    @SerializedName("url")
    @Expose
    private String image_url;

    public String getNo_user_id() {
        return no_user_id;
    }
    public String getImage_url() {
        return image_url;
    }
}
