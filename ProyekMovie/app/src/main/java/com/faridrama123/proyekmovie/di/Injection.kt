package com.faridrama123.proyekmovie.di

import android.content.Context
import com.faridrama123.proyekmovie.data.ProyekRepository
import com.faridrama123.proyekmovie.data.RemoteDataSource
import com.faridrama123.proyekmovie.utils.JsonHelper

object Injection {
    fun provideRepository (context: Context) : ProyekRepository {
        val remoteDataSource = RemoteDataSource.getInstance(JsonHelper(context))
        return  ProyekRepository.getInstance(remoteDataSource)
    }

}