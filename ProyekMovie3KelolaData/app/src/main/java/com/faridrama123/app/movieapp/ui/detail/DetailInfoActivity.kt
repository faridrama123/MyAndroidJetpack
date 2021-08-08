package com.faridrama123.proyekmovie.ui.detail

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.faridrama123.app.movieapp.data.source.local.entity.MovieEntity
import com.faridrama123.app.movieapp.data.source.local.entity.TVShowEntity

import com.faridrama123.app.movieapp.R
import com.faridrama123.app.movieapp.databinding.ActivityDetailBinding
import com.faridrama123.app.movieapp.databinding.ContentDetailBinding
import com.faridrama123.app.movieapp.viewmodel.ViewModelFactory
import com.faridrama123.app.movieapp.vo.Status

class DetailInfoActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_MOVIE = "movie"
        const val EXTRA_TVID = "tv"
    }

    private lateinit var detailContentBinding: ContentDetailBinding
    private lateinit var activityDetailBinding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityDetailBinding = ActivityDetailBinding.inflate(layoutInflater)
        detailContentBinding = activityDetailBinding.detailContent
        setContentView(activityDetailBinding.root)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.setDecorFitsSystemWindows(false)
        } else {
            window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
        }
        window.statusBarColor = Color.TRANSPARENT

        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[DetailInfoViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            val movieId = extras.getString(EXTRA_MOVIE)
            val tvId = extras.getString(EXTRA_TVID)
            activityDetailBinding.progressBar.visibility = View.VISIBLE
            activityDetailBinding.content.visibility = View.INVISIBLE

            if (extras.containsKey("movie") && movieId != null) {
                viewModel.setSelected(movieId)
                viewModel.getMovieById.observe(this, { it ->
                    if (it != null) {
                        when (it.status) {
                            Status.LOADING -> activityDetailBinding.progressBar.visibility = View.VISIBLE
                            Status.SUCCESS -> if (it.data != null) {
                                activityDetailBinding.progressBar.visibility = View.GONE
                                activityDetailBinding.content.visibility = View.VISIBLE
                                var statusFavorite = it.data.isFavorite
                                var movie = it.data
                                populateMovie(movie)
                                setStatusFavorite(statusFavorite)
                                activityDetailBinding.fab.setOnClickListener {
                                    statusFavorite = !statusFavorite
                                    viewModel.setFavoriteMovie(movie, statusFavorite)
                                    setStatusFavorite(statusFavorite)
                                }
                            }
                            Status.ERROR -> {
                                activityDetailBinding.progressBar.visibility = View.GONE
                                Toast.makeText(applicationContext, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                })
            }
            if (extras.containsKey("tv") && tvId != null) {
                viewModel.setSelected(tvId)
                viewModel.getTVShowById.observe(this, { it ->
                    if (it != null) {
                        when (it.status) {
                            Status.LOADING -> activityDetailBinding.progressBar.visibility = View.VISIBLE
                            Status.SUCCESS -> if (it.data != null) {
                                activityDetailBinding.progressBar.visibility = View.GONE
                                activityDetailBinding.content.visibility = View.VISIBLE
                                var statusFavorite = it.data.isFavorite
                                var tvshow = it.data
                                populateTVShow(tvshow)
                                setStatusFavorite(statusFavorite)
                                activityDetailBinding.fab.setOnClickListener {
                                    statusFavorite = !statusFavorite
                                    viewModel.setFavoriteTVShow(tvshow, statusFavorite)
                                    setStatusFavorite(statusFavorite)
                                }
                            }
                            Status.ERROR -> {
                                activityDetailBinding.progressBar.visibility = View.GONE
                                Toast.makeText(applicationContext, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                })
            }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            activityDetailBinding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_baseline_favorite_24_red))
        } else {
            activityDetailBinding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_baseline_favorite_24))
        }
    }



    private fun populateMovie(movieEntity: MovieEntity) {
        detailContentBinding.textTitle.text = movieEntity.title
        detailContentBinding.textDate.text = movieEntity.releaseDate
        detailContentBinding.textGenre.text = movieEntity.genreIds.toString()
        detailContentBinding.textRate.text = movieEntity.voteAverage.toString()
        detailContentBinding.textBahasa.text = resources.getString(R.string.languange,  movieEntity.originalLanguage.toString())
        detailContentBinding.textDescription.text = movieEntity.overview
        Glide.with(this)
                .load("https://image.tmdb.org/t/p/w500/"+movieEntity.posterPath)
                .transform(RoundedCorners(20))
                .into(detailContentBinding!!.imgPoster)
        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500/"+movieEntity.backdropPath)
            .transform(RoundedCorners(20))

            .into(activityDetailBinding!!.imageback)
    }

    private fun populateTVShow(tvShowEntity: TVShowEntity) {
        detailContentBinding.textTitle.text = tvShowEntity.originalName
        detailContentBinding.textDate.text = tvShowEntity.firstAirDate
        detailContentBinding.textGenre.text = tvShowEntity.genreIds.toString()
        detailContentBinding.textRate.text = tvShowEntity.voteAverage.toString()
        detailContentBinding.textBahasa.text = resources.getString(R.string.languange,  tvShowEntity.originalLanguage.toString())
        detailContentBinding.textDescription.text = tvShowEntity.overview
        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500/"+tvShowEntity.posterPath)
            .transform(RoundedCorners(20))

            .into(detailContentBinding!!.imgPoster)
        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500/"+tvShowEntity.backdropPath)
            .into(activityDetailBinding!!.imageback)
    }

}