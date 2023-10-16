package com.example.myapplication;

import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

public interface MyApi {
    @GET("https://raw.githubusercontent.com/dima203/MTLab2/master/data.json")
    Call<List<Item>> getData();
}
