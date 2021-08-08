package com.faridrama123.app.movieapp.ui.tvshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.faridrama123.app.movieapp.databinding.FragmentTvshowBinding
import com.faridrama123.app.movieapp.viewmodel.ViewModelFactory
import com.faridrama123.app.movieapp.vo.Status


/**
 * A simple [Fragment] subclass.
 */
class TVShowFragment : Fragment() {

    private var _fragmentTVShowBinding: FragmentTvshowBinding? = null
    private val binding get() = _fragmentTVShowBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        _fragmentTVShowBinding = FragmentTvshowBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {

            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[TVShowViewModel::class.java]
            val tvshowAdapter = TVShowAdapter()

            viewModel.getTVShow().observe(this, { it ->
                if (it != null) {
                    when (it.status) {
                        Status.LOADING -> binding?.progressBar?.visibility = View.VISIBLE
                        Status.SUCCESS -> {
                            binding?.progressBar?.visibility = View.GONE
                            tvshowAdapter.submitList(it.data)
                        }
                        Status.ERROR -> {
                            binding?.progressBar?.visibility = View.GONE
                            Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })


            with(binding?.rvTvshow) {
                this?.layoutManager = LinearLayoutManager(context)
                this?.setHasFixedSize(true)
                this?.adapter = tvshowAdapter
            }



        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _fragmentTVShowBinding = null
    }
}