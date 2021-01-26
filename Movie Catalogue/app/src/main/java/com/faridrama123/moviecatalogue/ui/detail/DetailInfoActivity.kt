package com.faridrama123.moviecatalogue.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.faridrama123.moviecatalogue.R
import com.faridrama123.moviecatalogue.data.MOVIEEntity
import com.faridrama123.moviecatalogue.data.TvSHOWEntity
import com.faridrama123.moviecatalogue.databinding.ActivityDetailCourseBinding
import com.faridrama123.moviecatalogue.databinding.ContentDetailBinding
import com.faridrama123.moviecatalogue.ui.detail.DetailInfoViewModel

class DetailInfoActivity : AppCompatActivity() {
    private var detailContentBinding: ContentDetailBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityDetailCourseBinding = ActivityDetailCourseBinding.inflate(layoutInflater)
        detailContentBinding = activityDetailCourseBinding.detailContent
        setContentView(activityDetailCourseBinding.root)
        setSupportActionBar(activityDetailCourseBinding.toolbar)
        if (supportActionBar != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        }
        //val viewModel = ViewModelProvider(this, NewInstanceFactory()).get(DetailInfoViewModel::class.java)
        val viewModel = ViewModelProvider(this, NewInstanceFactory())[DetailInfoViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            val movieId = extras.getString(EXTRA_COURSE)
            val tvId = extras.getString(EXTRA_COURSE2)
            if (extras.containsKey("movie") && movieId != null) {
                viewModel.setSelectedCourse(movieId)
                populateMovie(viewModel.getmovie());

            }
            if (extras.containsKey("tv") && tvId != null) {
                viewModel.setSelectedCourse(tvId)
                populateTv(viewModel.gettv());
            }
        }
    }

    private fun populateTv(tvEntity: TvSHOWEntity) {
        detailContentBinding!!.textTitle.text = tvEntity.title
        detailContentBinding!!.textDate.text = tvEntity.rilis
        detailContentBinding!!.textGenre.text = tvEntity.genre
        detailContentBinding!!.textRate.text = tvEntity.rating
        detailContentBinding!!.textDescription.text = tvEntity.desc
        Glide.with(this)
                .load(tvEntity.img)
                .transform(RoundedCorners(20))
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error))
                .into(detailContentBinding!!.imgPoster)
    }

    private fun populateMovie(movieEntity: MOVIEEntity) {
        detailContentBinding!!.textTitle.text = movieEntity.title
        detailContentBinding!!.textDate.text = movieEntity.rilis
        detailContentBinding!!.textGenre.text = movieEntity.genre
        detailContentBinding!!.textRate.text = movieEntity.rating
        detailContentBinding!!.textDescription.text = movieEntity.desc
        Glide.with(this)
                .load(movieEntity.img)
                .transform(RoundedCorners(20))
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error))
                .into(detailContentBinding!!.imgPoster)
    }

    companion object {
        const val EXTRA_COURSE = "movie"
        const val EXTRA_COURSE2 = "tv"
    }
}