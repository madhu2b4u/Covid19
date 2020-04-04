package com.covid.covid19.home.presentation.ui.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.lifecycle.lifecycleScope
import com.covid.covid19.R
import com.covid.covid19.common.BaseFragment
import com.covid.covid19.common.Urls.APOLLO
import kotlinx.android.synthetic.main.fragment_covid_tracker.*


class CovidRiskFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_covid_tracker, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        lifecycleScope.launchWhenStarted {

            val webSettings = mbEmbeddedWebView.getSettings()
            webSettings.javaScriptEnabled = true
            webSettings.domStorageEnabled = true
            webSettings.builtInZoomControls = true
            webSettings.useWideViewPort = true
            webSettings.loadWithOverviewMode = true
            mbEmbeddedWebView.webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(
                    view: WebView,
                    url: String
                ): Boolean {
                    view.loadUrl(url)
                    return true
                }

            }
            openURL()
        }
    }
    private fun openURL() {
        mbEmbeddedWebView.loadUrl(APOLLO)
        mbEmbeddedWebView.requestFocus()
    }


}
