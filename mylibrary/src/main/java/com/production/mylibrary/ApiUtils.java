package com.production.mylibrary;

public class ApiUtils {
    public static RetrofitInstance getInstance(String baseUrl)
    {
        return ApiClient.getClient(baseUrl).create(RetrofitInstance.class);
    }
}
