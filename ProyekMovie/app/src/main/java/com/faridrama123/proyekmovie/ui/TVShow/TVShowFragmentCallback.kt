package com.faridrama123.proyekmovie.ui.TVShow

import com.faridrama123.proyekmovie.data.local.entity.ResultsTVShowEntity

interface TVShowFragmentCallback {
    fun onShareClick(tvshow: ResultsTVShowEntity)
}