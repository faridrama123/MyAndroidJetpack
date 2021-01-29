package com.faridrama123.countapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.faridrama123.countapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private  lateinit var mLiveDataTimerViewModel: MainViewModel
    private  lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        activityMainBinding = ActivityMainBinding.inflate(layoutInflater);
        setContentView(activityMainBinding.root);

        mLiveDataTimerViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        subscribe()
    }

    private fun subscribe (){
        val elapsedTimeObserver = Observer<Long?>{
            f->
            val newText = this@MainActivity.resources.getString(R.string.seconds, f)
            activityMainBinding.timerTextview.text = newText
           // Log.d( aLong.toString(), newText);

        }
        mLiveDataTimerViewModel.getElapsedTime().observe(this, elapsedTimeObserver)

    }
}