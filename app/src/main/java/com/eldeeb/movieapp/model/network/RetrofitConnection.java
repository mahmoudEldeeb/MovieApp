package com.eldeeb.movieapp.model.network;

import com.eldeeb.movieapp.model.Constants;

import java.util.Collections;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConnection {


    private static Retrofit retrofit = null;


    public static DataApi getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .baseUrl(Constants.BASE_URL)
                    .build();
        }

        return retrofit.create(DataApi.class);
    }
}
