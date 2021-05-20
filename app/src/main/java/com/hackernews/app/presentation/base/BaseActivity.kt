package com.hackernews.app.presentation.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<V : ViewBinding> : AppCompatActivity() {

    private var _binding: V? = null
    protected val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = createViewBinding()
        setContentView(binding.root)
        setupViews()
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    abstract fun setupViews()
    abstract fun createViewBinding(): V

}