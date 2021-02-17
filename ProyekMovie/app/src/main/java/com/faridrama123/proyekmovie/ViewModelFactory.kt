package com.faridrama123.proyekmovie

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


import com.faridrama123.proyekmovie.data.ProyekRepository
import com.faridrama123.proyekmovie.di.Injection
import com.faridrama123.proyekmovie.ui.TVShow.TVShowViewModel
import com.faridrama123.proyekmovie.ui.detail.DetailInfoViewModel
import com.faridrama123.proyekmovie.ui.movies.MovieViewModel

class ViewModelFactory private constructor(private val proyekRepository: ProyekRepository) : ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
                instance ?: synchronized(this) {
                    instance ?: ViewModelFactory(Injection.provideRepository(context))
                }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MovieViewModel::class.java) -> {
                MovieViewModel(proyekRepository) as T
            }

            modelClass.isAssignableFrom(TVShowViewModel::class.java) -> {
                TVShowViewModel(proyekRepository) as T
            }

            modelClass.isAssignableFrom(DetailInfoViewModel::class.java) -> {
                DetailInfoViewModel(proyekRepository) as T
            }

            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }
}
