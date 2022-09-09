package com.example.mymvvmproject.model.apis

import com.example.mymvvmproject.data.GetMoviesResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("3/movie/popular")
    fun getPopularMovies(
        @Query("api_key") apiKey: String = "6e76ecffda0a59dc4f19a343c6e7648a",
        @Query("page") page: Int
    ): Call<GetMoviesResponse>

    companion object{
        private const val BASE_URL = "https://api.themoviedb.org/"

        fun create() : ApiInterface{
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ApiInterface::class.java)
        }
    }
}