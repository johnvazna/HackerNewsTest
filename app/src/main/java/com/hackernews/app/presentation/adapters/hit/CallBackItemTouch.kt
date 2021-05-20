package com.hackernews.app.presentation.adapters.hit

import androidx.recyclerview.widget.RecyclerView

interface CallBackItemTouch {

    /** */
    fun onSwiped(viewHolder: RecyclerView.ViewHolder, position: Int)

}