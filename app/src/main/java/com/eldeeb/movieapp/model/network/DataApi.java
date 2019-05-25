package com.eldeeb.movieapp.model.network;


import com.eldeeb.movieapp.R;
import com.eldeeb.movieapp.model.Results;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DataApi {

   @GET("search/movie?")
   Single<Results> getMovies(@Query("api_key")String api_key, @Query("query") String query, @Query("page") int page);
}
