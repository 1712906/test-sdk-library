package com.production.mytest;

import com.google.gson.JsonObject;
import com.production.mytest.Model.PostImage;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RetrofitInstance {
    @POST("api/userskin")
    Call<JsonObject> createUser(@Header("apikey") String key, @Body PostImage body);
    @GET("api/userskin/{id}")
    Call<JsonObject> getDetail(@Path("id") String id);
}
