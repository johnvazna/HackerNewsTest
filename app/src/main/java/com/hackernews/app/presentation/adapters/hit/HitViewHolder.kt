package com.hackernews.app.presentation.adapters.hit

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hackernews.app.databinding.HitItemBinding
import com.hackernews.app.domain.hit.entity.Hit

class HitViewHolder(
    private val binding: HitItemBinding,
    private val hitActionClickListener: (Hit) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    private lateinit var hit: Hit

    fun bind(hit: Hit) {
        this.hit = hit
        binding.hit = this.hit
        setupActions()
    }

    private fun setupActions() {
        binding.root.setOnClickListener {
            hitActionClickListener(hit)
        }
    }

    companion object {
        fun from(
            parent: ViewGroup,
            hitActionClickListener: (Hit) -> Unit,
        ): HitViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = HitItemBinding.inflate(layoutInflater, parent, false)
            return HitViewHolder(binding, hitActionClickListener)
        }
    }

}