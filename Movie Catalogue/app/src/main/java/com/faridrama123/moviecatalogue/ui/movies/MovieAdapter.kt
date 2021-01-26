package com.faridrama123.moviecatalogue.ui.movies

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.faridrama123.moviecatalogue.R
import com.faridrama123.moviecatalogue.data.MOVIEEntity
import com.faridrama123.moviecatalogue.databinding.ItemsAcademyBinding
import com.faridrama123.moviecatalogue.ui.detail.DetailInfoActivity
import java.util.*

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.CourseViewHolder>() {
    private val listmovies: MutableList<MOVIEEntity> = ArrayList()
    fun setCourses(listmovies: List<MOVIEEntity>?) {
        if (listmovies == null) return
        this.listmovies.clear()
        this.listmovies.addAll(listmovies)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val binding = ItemsAcademyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CourseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        val movie = listmovies[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int {
        return listmovies.size
    }

    class CourseViewHolder(private val binding: ItemsAcademyBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(Movie: MOVIEEntity) {
            binding.tvItemTitle.text = Movie.title
            binding.tvItemDate.text = Movie.rilis
            binding.tvItemRate.text = Movie.rating
            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DetailInfoActivity::class.java)
                intent.putExtra(DetailInfoActivity.EXTRA_COURSE, Movie.id)
                itemView.context.startActivity(intent)
            }
            Glide.with(itemView.context)
                    .load(Movie.img)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                    .into(binding.imgPoster)
        }
    }
}