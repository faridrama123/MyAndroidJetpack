package com.faridrama123.moviecatalogue.ui.TVShow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ShareCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory
import androidx.recyclerview.widget.LinearLayoutManager
import com.faridrama123.moviecatalogue.data.TvSHOWEntity
import com.faridrama123.moviecatalogue.databinding.FragmentTvshowBinding
import com.faridrama123.moviecatalogue.ui.TVShow.TVShowFragmentCallback
import com.faridrama123.moviecatalogue.ui.TVShow.TVShowViewModel

/**
 * A simple [Fragment] subclass.
 */
 class TVShowFragment : Fragment(), TVShowFragmentCallback {
    private var fragmentBookmarkBinding: FragmentTvshowBinding? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentBookmarkBinding = FragmentTvshowBinding.inflate(inflater, container, false)
        return fragmentBookmarkBinding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val viewModel = ViewModelProvider(this, NewInstanceFactory()).get(TVShowViewModel::class.java)
            val tvshow = viewModel.gettv()
            val adapter = TVShowAdapter(this)
            adapter.setTvshow(tvshow)
            fragmentBookmarkBinding!!.rvTvshow.layoutManager = LinearLayoutManager(context)
            fragmentBookmarkBinding!!.rvTvshow.setHasFixedSize(true)
            fragmentBookmarkBinding!!.rvTvshow.adapter = adapter
        }
    }

    override fun onShareClick(tvshow: TvSHOWEntity) {
        if (activity != null) {
            val mimeType = "text/plain"
            ShareCompat.IntentBuilder
                    .from(activity!!)
                    .setType(mimeType)
                    .setChooserTitle("Bagikan aplikasi ini sekarang.")
                    .setText("Segera daftar kelas ${tvshow.title} di dicoding.com")
                    .startChooser()
        }
    }



}