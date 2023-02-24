package com.example.ezotask;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    String Base_URL = "https://picsum.photos/v2/";

    @GET("list?limit=10")
    Call<List<Result>> getResult();

}
