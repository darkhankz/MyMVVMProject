package com.example.mymvvmproject.model.repository

import com.example.mymvvmproject.data.GetMoviesResponse
import com.example.mymvvmproject.model.apis.ApiInterface
import retrofit2.Call

class MoviesRepositoryImpl: MoviesRepository {
    private val apiInterface = ApiInterface.create()
    override fun getPopularMovies(page: Int): Call<GetMoviesResponse> {
        return apiInterface.getPopularMovies(page = page)
    }
}