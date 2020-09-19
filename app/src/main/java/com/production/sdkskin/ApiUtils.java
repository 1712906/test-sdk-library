package com.production.sdkskin;

public class ApiUtils {
    public static RetrofitInstance getInstance(String baseUrl)
    {
        return ApiClient.getClient(baseUrl).create(RetrofitInstance.class);
    }
}
