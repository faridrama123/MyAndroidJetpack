package com.faridrama123.proyekmovie.ui.detail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.faridrama123.proyekmovie.R
import com.faridrama123.proyekmovie.ViewModelFactory
import com.faridrama123.proyekmovie.data.local.entity.ResultsMovieEntity
import com.faridrama123.proyekmovie.data.local.entity.ResultsTVShowEntity
import com.faridrama123.proyekmovie.databinding.ActivityDetailBinding
import com.faridrama123.proyekmovie.databinding.ContentDetailBinding
import com.faridrama123.proyekmovie.ui.detail.DetailInfoViewModel

class DetailInfoActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_COURSE = "movie"
        const val EXTRA_COURSE2 = "tv"
    }

    private var detailContentBinding: ContentDetailBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityDetailBinding = ActivityDetailBinding.inflate(layoutInflater)
        detailContentBinding = activityDetailBinding.detailContent
        setContentView(activityDetailBinding.root)
        setSupportActionBar(activityDetailBinding.toolbar)
        if (supportActionBar != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        }
        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[DetailInfoViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            val movieId = extras.getString(EXTRA_COURSE)
            val tvId = extras.getString(EXTRA_COURSE2)
            activityDetailBinding.progressBar.visibility = View.VISIBLE
            activityDetailBinding.content.visibility = View.INVISIBLE

            if (extras.containsKey("movie") && movieId != null) {
                viewModel.setSelectedCourse(movieId)
                viewModel.getMovie().observe(this, { populateMovie(it)
                    activityDetailBinding.progressBar.visibility = View.GONE
                    activityDetailBinding.content.visibility = View.VISIBLE
                })
            }
            if (extras.containsKey("tv") && tvId != null) {
                viewModel.setSelectedCourse(tvId)
                viewModel.getTVShow().observe(this, { populateTVShow(it)
                    activityDetailBinding.progressBar.visibility = View.GONE
                    activityDetailBinding.content.visibility = View.VISIBLE
                })
            }
        }
    }



    private fun populateMovie(movieEntity: ResultsMovieEntity) {
        detailContentBinding!!.textTitle.text = movieEntity.title
        detailContentBinding!!.textDate.text = movieEntity.releaseDate
        detailContentBinding!!.textGenre.text = movieEntity.genreIds.toString()
        detailContentBinding!!.textRate.text = movieEntity.voteAverage.toString()
        detailContentBinding!!.textBahasa.text = resources.getString(R.string.bahasa,  movieEntity.originalLanguage.toString())
        detailContentBinding!!.textDescription.text = movieEntity.overview
        Glide.with(this)
                .load("https://image.tmdb.org/t/p/w500/"+movieEntity.posterPath)
                .transform(RoundedCorners(20))
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error))
                .into(detailContentBinding!!.imgPoster)
        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500/"+movieEntity.backdropPath)
            .transform(RoundedCorners(20))
            .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                .error(R.drawable.ic_error))
            .into(detailContentBinding!!.imageback)
    }

    private fun populateTVShow(tvShowEntity: ResultsTVShowEntity) {
        detailContentBinding!!.textTitle.text = tvShowEntity.originalName
        detailContentBinding!!.textDate.text = tvShowEntity.firstAirDate
        detailContentBinding!!.textGenre.text = tvShowEntity.genreIds.toString()
        detailContentBinding!!.textRate.text = tvShowEntity.voteAverage.toString()
        detailContentBinding!!.textBahasa.text = resources.getString(R.string.bahasa,  tvShowEntity.originalLanguage.toString())
        detailContentBinding!!.textDescription.text = tvShowEntity.overview
        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500/"+tvShowEntity.posterPath)
            .transform(RoundedCorners(20))
            .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                .error(R.drawable.ic_error))
            .into(detailContentBinding!!.imgPoster)
        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500/"+tvShowEntity.backdropPath)
            .transform(RoundedCorners(20))
            .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                .error(R.drawable.ic_error))
            .into(detailContentBinding!!.imageback)
    }

}