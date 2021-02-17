package com.faridrama123.proyekmovie.ui.TVShow

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.faridrama123.proyekmovie.R
import com.faridrama123.proyekmovie.data.local.entity.ResultsTVShowEntity
import com.faridrama123.proyekmovie.databinding.ItemsTvshowBinding

import com.faridrama123.proyekmovie.ui.detail.DetailInfoActivity
import java.util.*

class TVShowAdapter internal constructor(private val callback: TVShowFragmentCallback) : RecyclerView.Adapter<TVShowAdapter.CourseViewHolder>() {
    private val listtvshow = ArrayList<ResultsTVShowEntity>()
    fun setTvshow(tvshow: List<ResultsTVShowEntity>?) {
        if (tvshow == null) return
        listtvshow.clear()
        listtvshow.addAll(tvshow)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val binding = ItemsTvshowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CourseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        val movie = listtvshow[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int {
        return listtvshow.size
    }

    inner class CourseViewHolder(private val binding: ItemsTvshowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(tvshow: ResultsTVShowEntity) {
            binding.tvItemTitle.text = tvshow.originalName
            binding.tvItemDate.text = tvshow.firstAirDate
            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DetailInfoActivity::class.java)
                intent.putExtra(DetailInfoActivity.EXTRA_COURSE2, tvshow.id)
                itemView.context.startActivity(intent)
            }
            binding.imgShare.setOnClickListener { callback.onShareClick(tvshow) }
            Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/w500/"+tvshow.posterPath).centerCrop()
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                    .into(binding.imgPoster)
        }
    }
}