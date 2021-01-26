package com.faridrama123.moviecatalogue.ui.TVShow

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.faridrama123.moviecatalogue.R
import com.faridrama123.moviecatalogue.data.TvSHOWEntity
import com.faridrama123.moviecatalogue.databinding.ItemsBookmarkBinding
import com.faridrama123.moviecatalogue.ui.TVShow.TVShowFragmentCallback
import com.faridrama123.moviecatalogue.ui.detail.DetailInfoActivity
import java.util.*

class TVShowAdapter internal constructor(private val callback: TVShowFragmentCallback) : RecyclerView.Adapter<TVShowAdapter.CourseViewHolder>() {
    private val listtvshow = ArrayList<TvSHOWEntity>()
    fun setTvshow(tvshow: List<TvSHOWEntity>?) {
        if (tvshow == null) return
        listtvshow.clear()
        listtvshow.addAll(tvshow)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val binding = ItemsBookmarkBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CourseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        val movie = listtvshow[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int {
        return listtvshow.size
    }

    inner class CourseViewHolder(private val binding: ItemsBookmarkBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(tvshow: TvSHOWEntity) {
            binding.tvItemTitle.text = tvshow.title
            binding.tvItemDate.text = tvshow.rilis
            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DetailInfoActivity::class.java)
                intent.putExtra(DetailInfoActivity.EXTRA_COURSE2, tvshow.id)
                itemView.context.startActivity(intent)
            }
            binding.imgShare.setOnClickListener { callback.onShareClick(tvshow) }
            Glide.with(itemView.context)
                    .load(tvshow.img)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                    .into(binding.imgPoster)
        }
    }
}