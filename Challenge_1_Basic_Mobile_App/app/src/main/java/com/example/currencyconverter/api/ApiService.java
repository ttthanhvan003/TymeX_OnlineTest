package com.example.currencyconverter.api;

import com.example.currencyconverter.model.Currency;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

//Link API: https://api.exchangeratesapi.io/v1/latest?access_key=9e35a3e80b19a0335c077d3fa63ea364
//& base = USD
//    & symbols = GBP,JPY,EUR
public interface ApiService {
    Gson gson = new GsonBuilder()
            .setDateFormat("dd-MM-yyyy HH:mm:ss")
            .create();

    ApiService apiService = new Retrofit.Builder()
            .baseUrl("https://api.exchangeratesapi.io/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);

    @GET("v1/latest")
    Call<Currency> convert(
            @Query("access_key") String access_key,
            @Query("base") String baseCurrency,
            @Query("symbols") String symbols
    );

    static ApiService create() {
        return apiService;
    }

}
