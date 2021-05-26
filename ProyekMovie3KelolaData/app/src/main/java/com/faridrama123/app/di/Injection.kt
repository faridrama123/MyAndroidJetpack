package com.faridrama123.app.di

import android.content.Context
import com.faridrama123.app.data.CatalogueRepository
import com.faridrama123.app.data.source.local.LocalDataSource
import com.faridrama123.app.data.source.local.room.CatalogueDatabase
import com.faridrama123.app.data.source.remote.RemoteDataSource
import com.faridrama123.app.utils.AppExecutors
import com.faridrama123.app.utils.JsonHelper

object Injection {
    fun provideRepository(context: Context): CatalogueRepository {

        val database = CatalogueDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance(JsonHelper(context))
        val localDataSource = LocalDataSource.getInstance(database.catalogueDao())
        val appExecutors = AppExecutors()

        return CatalogueRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}
