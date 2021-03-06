package com.faridrama123.proyekmovie.ui.movies

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.faridrama123.proyekmovie.R
import com.faridrama123.proyekmovie.data.local.entity.ResultsMovieEntity
import com.faridrama123.proyekmovie.databinding.ItemsMovieBinding
import com.faridrama123.proyekmovie.ui.detail.DetailInfoActivity
import java.util.*

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.CourseViewHolder>() {
    private val listmovies: MutableList<ResultsMovieEntity> = ArrayList()
    fun setCourses(listmovies: List<ResultsMovieEntity>?) {
        if (listmovies == null) return
        this.listmovies.clear()
        this.listmovies.addAll(listmovies)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val binding = ItemsMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CourseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        val movie = listmovies[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int {
        return listmovies.size
    }

    class CourseViewHolder(private val binding: ItemsMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(Movie: ResultsMovieEntity) {
            binding.tvItemTitle.text = Movie.title
            binding.tvItemDate.text = Movie.releaseDate
            binding.tvItemRate.text = Movie.voteAverage.toString()
            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DetailInfoActivity::class.java)
                intent.putExtra(DetailInfoActivity.EXTRA_COURSE, Movie.id.toString())
                itemView.context.startActivity(intent)
            }
            Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/w500/"+Movie.posterPath).centerCrop()
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                    .into(binding.imgPoster)
        }
    }
}