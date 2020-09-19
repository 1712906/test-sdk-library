package com.production.sdkskin.Model;

import com.google.gson.annotations.SerializedName;

public class PostImage {
    @SerializedName("email")
    String email;
    @SerializedName("image_base64")
    String image_base64;

    public PostImage(String email, String image_base64) {
        this.email = email;
        this.image_base64 = image_base64;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage_base64() {
        return image_base64;
    }

    public void setImage_base64(String image_base64) {
        this.image_base64 = image_base64;
    }
}
