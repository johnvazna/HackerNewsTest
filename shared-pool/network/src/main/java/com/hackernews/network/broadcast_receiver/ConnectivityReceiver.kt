package com.hackernews.network.broadcast_receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

/** */
internal class ConnectivityReceiver : BroadcastReceiver() {



    override fun onReceive(context: Context?, intent: Intent?) {

    }

    /** */
    companion object {

        /* */
        const val CONNECTIVITY_CHANGE: String = "android.net.conn.CONNECTIVITY_CHANGE"

    }

}