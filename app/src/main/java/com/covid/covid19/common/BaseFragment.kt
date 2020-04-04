package com.covid.covid19.common

import android.content.Context
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import com.covid.covid19.R
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import dagger.android.support.DaggerFragment


open class BaseFragment : DaggerFragment() {

    private var progressBar: ProgressBar? = null

    private var mInterstitialAd: InterstitialAd? = null

    fun setProgressBar(resId: Int) {
        progressBar = activity?.findViewById(resId)
    }
    fun showProgressBar() {
        progressBar?.visibility = View.VISIBLE
    }

    fun hideProgressBar() {
        progressBar?.visibility = View.GONE
    }

    fun setAds(context: Context){
        mInterstitialAd = newInterstitialAd(context)
        loadInterstitial()
    }
    private fun loadInterstitial() {
        // Disable the load ad button and load the ad.
        val adRequest = AdRequest.Builder().build()
        mInterstitialAd!!.loadAd(adRequest)

    }

    fun showInterstitial(context: Context) {
        // Show the ad if it is ready. Otherwise toast and reload the ad.
        if (mInterstitialAd != null && mInterstitialAd!!.isLoaded) {
            mInterstitialAd!!.show()
        } else {
          //  Toast.makeText(context, "Ad did not load", Toast.LENGTH_SHORT).show()
            tryToLoadAdOnceAgain(context)
        }
    }

    private fun tryToLoadAdOnceAgain(context: Context) {
        mInterstitialAd = newInterstitialAd(context)
        loadInterstitial()
    }

    private fun newInterstitialAd(context: Context): InterstitialAd {
        val interstitialAd = InterstitialAd(context)
        interstitialAd.adUnitId = getString(R.string.interstitial)
        interstitialAd.adListener = object : AdListener() {
            override fun onAdLoaded() {
              //  Toast.makeText(context, "Ad Loaded", Toast.LENGTH_SHORT).show()
            }

            override fun onAdFailedToLoad(errorCode: Int) {
                //Toast.makeText(context, "Ad Failed To Load", Toast.LENGTH_SHORT).show()
            }

            override fun onAdClosed() {
                // Proceed to the next level.
                // goToNextLevel()
                Toast.makeText(context, "Ad Closed", Toast.LENGTH_SHORT).show()
                tryToLoadAdOnceAgain(context)
            }
        }
        return interstitialAd
    }




}
