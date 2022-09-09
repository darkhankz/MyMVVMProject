package com.example.mymvvmproject.view

import com.example.mymvvmproject.data.GetMoviesResponse
import com.example.mymvvmproject.data.Movie
import com.example.mymvvmproject.model.repository.MoviesRepository
import com.example.mymvvmproject.model.repository.MoviesRepositoryImpl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesViewModel {
    private val mMoviesRepository : MoviesRepository = MoviesRepositoryImpl()


    fun getPopularMovies(
        page: Int = 1,
        onSuccess: (movies: List<Movie>) -> Unit,
        onError: () -> Unit
    ) {
        val response = mMoviesRepository.getPopularMovies(page)

        response.enqueue(object : Callback<GetMoviesResponse> {
                override fun onResponse(
                    call: Call<GetMoviesResponse>,
                    response: Response<GetMoviesResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()

                        if (responseBody != null) {
                            onSuccess.invoke(responseBody.results)
                        } else {
                            onError.invoke()
                        }
                    } else {
                        onError.invoke()
                    }
                }

                override fun onFailure(call: Call<GetMoviesResponse>, t: Throwable) {
                    onError.invoke()
                }
            })
    }
}