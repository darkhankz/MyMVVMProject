package com.example.mymvvmproject.model.repository

import com.example.mymvvmproject.data.GetMoviesResponse
import com.example.mymvvmproject.data.Movie
import com.example.mymvvmproject.model.apis.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface MoviesRepository {

    fun getPopularMovies(page: Int) : Call<GetMoviesResponse>
}