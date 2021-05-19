package com.hackernews.network

import android.app.Application
import android.content.IntentFilter
import com.hackernews.network.broadcast_receiver.ConnectivityReceiver

/** */
fun Application.initConnectivityReceiverBroadcastReceiver() {
    registerReceiver(
        ConnectivityReceiver(),
        IntentFilter(ConnectivityReceiver.CONNECTIVITY_CHANGE)
    )
}