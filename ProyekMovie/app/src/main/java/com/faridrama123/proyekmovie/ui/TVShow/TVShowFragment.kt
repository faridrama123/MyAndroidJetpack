package com.faridrama123.proyekmovie.ui.TVShow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ShareCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory
import androidx.recyclerview.widget.LinearLayoutManager
import com.faridrama123.proyekmovie.ViewModelFactory
import com.faridrama123.proyekmovie.data.local.entity.ResultsTVShowEntity
import com.faridrama123.proyekmovie.databinding.FragmentTvshowBinding
import com.faridrama123.proyekmovie.ui.movies.MovieAdapter
import com.faridrama123.proyekmovie.ui.movies.MovieViewModel


/**
 * A simple [Fragment] subclass.
 */
 class TVShowFragment : Fragment() , TVShowFragmentCallback{
    private lateinit var fragmentTvshowBinding: FragmentTvshowBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentTvshowBinding = FragmentTvshowBinding.inflate(inflater, container, false)
        return fragmentTvshowBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {

            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this,factory)[TVShowViewModel::class.java]
            val tvShowAdapter = TVShowAdapter(this)

            fragmentTvshowBinding.progressBar.visibility = View.VISIBLE

            viewModel.getTVShow().observe(this,{
                fragmentTvshowBinding.progressBar.visibility = View.GONE
                tvShowAdapter.setTvshow(it)
                tvShowAdapter.notifyDataSetChanged()

            })
            with(fragmentTvshowBinding.rvTvshow) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = tvShowAdapter
            }


        }
    }

    override fun onShareClick(tvshow: ResultsTVShowEntity) {
        if (activity != null) {
            val mimeType = "text/plain"
            ShareCompat.IntentBuilder
                    .from(activity!!)
                    .setType(mimeType)
                    .setChooserTitle("Bagikan aplikasi ini sekarang.")
                    .setText("Segera lihat tvShow ${tvshow.originalName} di dicoding.com")
                    .startChooser()
        }
    }



}