package com.faridrama123.app.movieapp.di

import android.content.Context
import com.faridrama123.app.movieapp.data.CatalogueRepository
import com.faridrama123.app.movieapp.data.source.local.LocalDataSource
import com.faridrama123.app.movieapp.data.source.local.room.CatalogueDatabase
import com.faridrama123.app.movieapp.data.source.remote.RemoteDataSource
import com.faridrama123.app.movieapp.utils.AppExecutors
import com.faridrama123.app.movieapp.utils.JsonHelper

object Injection {
    fun provideRepository(context: Context): CatalogueRepository {

        val database = CatalogueDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance(JsonHelper(context))
        val localDataSource = LocalDataSource.getInstance(database.catalogueDao())
        val appExecutors = AppExecutors()

        return CatalogueRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}
