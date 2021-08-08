package com.faridrama123.app.movieapp.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.faridrama123.app.movieapp.data.CatalogueRepository
import com.faridrama123.app.movieapp.ui.movie.MovieViewModel
import com.faridrama123.app.movieapp.ui.moviefavorites.MovieFavoritesViewModel
import com.faridrama123.app.movieapp.ui.tvshow.TVShowViewModel
import com.faridrama123.app.movieapp.ui.tvshowfavorites.TVShowFavoritesViewModel
import com.faridrama123.app.movieapp.di.Injection
import com.faridrama123.proyekmovie.ui.detail.DetailInfoViewModel


class ViewModelFactory private constructor(private val mCatalogueRepository: CatalogueRepository) : ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
                instance ?: synchronized(this) {
                    ViewModelFactory(Injection.provideRepository(context)).apply {
                        instance = this
                    }
                }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MovieViewModel::class.java) -> {
                MovieViewModel(mCatalogueRepository) as T
            }

            modelClass.isAssignableFrom(MovieFavoritesViewModel::class.java) -> {
                MovieFavoritesViewModel(mCatalogueRepository) as T
            }

            modelClass.isAssignableFrom(DetailInfoViewModel::class.java) -> {
                DetailInfoViewModel(mCatalogueRepository) as T
            }


            modelClass.isAssignableFrom(TVShowViewModel::class.java) -> {
                TVShowViewModel(mCatalogueRepository) as T
            }


            modelClass.isAssignableFrom(TVShowFavoritesViewModel::class.java) -> {
                TVShowFavoritesViewModel(mCatalogueRepository) as T
            }






            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }
}
