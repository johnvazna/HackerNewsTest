package com.hackernews.app.presentation.detail

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.net.http.SslError
import android.os.Build
import android.view.MenuItem
import android.view.View
import android.webkit.*
import com.hackernews.app.R
import com.hackernews.app.databinding.ActivityHitDetailBinding
import com.hackernews.app.domain.hit.entity.Hit
import com.hackernews.app.presentation.base.BaseActivity


@Suppress("PrivatePropertyName")
@SuppressLint("SetJavaScriptEnabled")
class HitDetailActivity : BaseActivity<ActivityHitDetailBinding>() {

    /** */
    override fun createViewBinding(): ActivityHitDetailBinding =
        ActivityHitDetailBinding.inflate(layoutInflater)

    /** */
    override fun setupViews() {
        setContentView(binding.root)

        supportActionBar!!.title = getString(R.string.general_back)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        initWebView()
        setupWebView()
    }

    /** */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    /** */
    private fun setupWebView() {
        val hit = intent.getSerializableExtra("hit") as Hit
        hit.story_url?.let { loadUrl(it) }

    }

    /** */
    private fun loadUrl(pageUrl: String) {
        binding.webView.loadUrl(pageUrl)
        binding.webView.webChromeClient = object : WebChromeClient() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            override fun onPermissionRequest(request: PermissionRequest) {
                request.grant(request.resources)
            }

            override fun onProgressChanged(view: WebView, progress: Int) {
                binding.progressBar.progress = progress
                if (progress == 100) {
                    binding.progressBar.visibility = View.GONE
                } else {
                    binding.progressBar.visibility = View.VISIBLE
                }
            }
        }
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