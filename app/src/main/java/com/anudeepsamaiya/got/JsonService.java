package com.anudeepsamaiya.got;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

/**
 * Created by anudeepsamaiya on 7/1/17.
 */

public class JsonService {
    private final String BASE_URL = "http://starlord.hackerearth.com/";

    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public Westeros getWesteros() {
        return retrofit.create(Westeros.class);
    }

    public interface Westeros {
        @GET("gotjson")
        Call<List<WarLogModel>> getWarLog();
    }

}