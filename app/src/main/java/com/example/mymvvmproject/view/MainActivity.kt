package com.example.mymvvmproject.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mymvvmproject.R
import com.example.mymvvmproject.data.Movie
import com.example.mymvvmproject.model.repository.MoviesRepository
import com.example.mymvvmproject.view.adapters.CustomAdapter

class MainActivity : AppCompatActivity() {
//    private val mMoviesRepository: MoviesRepository = MoviesRepository
    private val mViewModel : MoviesViewModel = MoviesViewModel()


    private lateinit var popularMovies: RecyclerView
    private lateinit var popularMoviesAdapter: CustomAdapter
    private lateinit var mMoviesLayoutMgr: LinearLayoutManager

    private var popularMoviesPage = 1



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        getPopularMovies()
        attachPopularMoviesOnScrollListener()

    }

    private fun onPopularMoviesFetched(movies: List<Movie>) {
        popularMoviesAdapter.appendMovies(movies)
        attachPopularMoviesOnScrollListener()
    }

    private fun getPopularMovies() {
        mViewModel.getPopularMovies(
            popularMoviesPage,
            ::onPopularMoviesFetched,
            ::onError
        )
    }


    private fun initViews() {
        popularMovies = findViewById(R.id.recyclerview)
        mMoviesLayoutMgr = GridLayoutManager(this, 2
        )
        popularMovies.layoutManager = mMoviesLayoutMgr
        popularMoviesAdapter = CustomAdapter(mutableListOf())
        popularMovies.adapter = popularMoviesAdapter
    }

    private fun onError() {
        Toast.makeText(this, getString(R.string.error_fetch_movies), Toast.LENGTH_SHORT).show()
    }

    private fun attachPopularMoviesOnScrollListener() {
        popularMovies.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItemCount = mMoviesLayoutMgr.itemCount
                val visibleItemCount = mMoviesLayoutMgr.childCount
                val firstVisibleItem = mMoviesLayoutMgr.findFirstVisibleItemPosition()

                if (firstVisibleItem + visibleItemCount >= totalItemCount / 2) {
                    popularMovies.removeOnScrollListener(this)
                    popularMoviesPage++
                    getPopularMovies()
                }
            }
        })
    }

}


