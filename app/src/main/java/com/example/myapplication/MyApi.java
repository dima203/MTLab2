package com.example.myapplication;

import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

public interface MyApi {
    @GET("https://raw.githubusercontent.com/fakehomer/jsonforms2/main/test.json")
    Call<List<Item>> getData();
}
