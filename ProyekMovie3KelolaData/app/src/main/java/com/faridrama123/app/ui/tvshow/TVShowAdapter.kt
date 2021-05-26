package com.faridrama123.app.ui.tvshow

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.faridrama123.app.R
import com.faridrama123.app.data.source.local.entity.TVShowEntity
import com.faridrama123.app.databinding.ItemsListBinding
import com.faridrama123.proyekmovie.ui.detail.DetailInfoActivity


class TVShowAdapter : PagedListAdapter<TVShowEntity, TVShowAdapter.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TVShowEntity>() {
            override fun areItemsTheSame(oldItem: TVShowEntity, newItem: TVShowEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: TVShowEntity, newItem: TVShowEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemsAcademyBinding = ItemsListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemsAcademyBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val course = getItem(position)
        if (course != null) {
            holder.bind(course)
        }
    }

    fun getSwipedData(swipedPosition: Int): TVShowEntity? = getItem(swipedPosition)

    class ViewHolder(private val binding: ItemsListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(tvshow: TVShowEntity) {
            with(binding) {
                tvItemTitle.text = tvshow.originalName
                tvItemDate.text = tvshow.firstAirDate
                tvItemRate.text = tvshow.voteAverage.toString()
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailInfoActivity::class.java)
                    intent.putExtra(DetailInfoActivity.EXTRA_TVID, tvshow.id.toString())
                    itemView.context.startActivity(intent)
                }
                Glide.with(itemView.context)
                        .load("https://image.tmdb.org/t/p/w500/"+tvshow.posterPath).centerCrop()
                        .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                                .error(R.drawable.ic_error))
                        .into(binding.imgPoster)
            }
        }
    }
}