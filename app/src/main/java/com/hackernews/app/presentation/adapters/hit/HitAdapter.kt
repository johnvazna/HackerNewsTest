package com.hackernews.app.presentation.adapters.hit

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.hackernews.app.domain.hit.entity.Hit
import com.hackernews.app.utils.TimeFormat

class HitAdapter(private val hitActionListener: (Hit) -> Unit) :
    ListAdapter<Hit, HitViewHolder>(SelectHitCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HitViewHolder =
        HitViewHolder.from(parent, hitActionListener)

    override fun onBindViewHolder(holder: HitViewHolder, position: Int) {
        val hit: Hit = currentList[position]
        if (hit.title == null) hit.title = hit.story_title else hit.title
        val time = hit.created_at?.let { TimeFormat.getTimeAgo(hit.created_at_i!!) }
        hit.created_at = time
        holder.bind(hit)
    }

    /** */
    private object SelectHitCallBack : DiffUtil.ItemCallback<Hit>() {

        override fun areItemsTheSame(
            oldItem: Hit,
            newItem: Hit
        ): Boolean = oldItem.story_id == newItem.story_id

        override fun areContentsTheSame(
            oldItem: Hit,
            newItem: Hit
        ): Boolean = oldItem == newItem
    }

}