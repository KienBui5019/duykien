package com.example.buiduykien_2122110362;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import com.google.gson.JsonElement;

public interface ApiService {

    // Gọi API với endpoint động
    @GET("{endpoint}")
    Call<JsonElement> getDataFromEndpoint(@Path("endpoint") String endpoint);

    @GET("product")
    Call<JsonElement> getProduct();

}
