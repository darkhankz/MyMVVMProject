package com.example.mymvvmproject.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mymvvmproject.R
import com.example.mymvvmproject.data.Movie
import com.squareup.picasso.Picasso

class CustomAdapter(private var movies: MutableList<Movie>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_design, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemsViewModel = movies[position]
        Picasso.get().load("https://image.tmdb.org/t/p/w500/" + itemsViewModel.poster_path).into(holder.imageView)
//        holder.textView.text = itemsViewModel.title



        // sets the image to the imageview from our itemHolder class
        // sets the text to the textview from our itemHolder class
//        holder.textView.text = itemsViewModel.title

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return movies.size
    }

    fun appendMovies(movies: List<Movie>) {
        this.movies.addAll(movies)
        notifyItemRangeInserted(
            this.movies.size,
            movies.size - 1
        )
    }

    // Holds the views for adding it to image and text
    inner class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
//        val textView: TextView = itemView.findViewById(R.id.textView)
        val imageView: ImageView = itemView.findViewById(R.id.image_view)


    }
}
