package com.hackernews.app.presentation.detail

import android.annotation.SuppressLint
import android.net.http.SslError
import android.view.LayoutInflater
import android.view.ViewGroup
import android.webkit.SslErrorHandler
import android.webkit.WebView
import android.webkit.WebViewClient
import com.hackernews.app.databinding.FragmentHitDetailBinding
import com.hackernews.app.domain.hit.entity.Hit
import com.hackernews.app.presentation.base.BaseFragment

@Suppress("PrivatePropertyName")
@SuppressLint("SetJavaScriptEnabled")
class HitDetailFragment : BaseFragment<FragmentHitDetailBinding>() {

    private val MAX_PROGRESS = 100

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHitDetailBinding =
        FragmentHitDetailBinding.inflate(inflater, container, false)

    override fun setupViews() {
        val hit = arguments?.get("hit") as Hit

        initWebView()
        hit.story_url?.let { loadUrl(it) }
    }

    /** */
    private fun loadUrl(pageUrl: String) {
        binding.webView.loadUrl(pageUrl)
    }

    /** */
    private fun initWebView() {
        binding.webView.settings.javaScriptEnabled = true
        binding.webView.settings.loadWithOverviewMode = true
        binding.webView.settings.useWideViewPort = true
        binding.webView.settings.domStorageEnabled = true

        binding.webView.webViewClient = object : WebViewClient() {
            override
            fun onReceivedSslError(view: WebView?, handler: SslErrorHandler?, error: SslError?) {
                handler?.proceed()
            }
        }
    }

}