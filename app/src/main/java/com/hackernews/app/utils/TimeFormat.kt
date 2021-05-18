package com.hackernews.app.utils

object TimeFormat {

    private const val SECOND_MILLIS = 1000
    private const val MINUTE_MILLIS = 60 * SECOND_MILLIS
    private const val HOUR_MILLIS = 60 * MINUTE_MILLIS
    private const val DAY_MILLIS = 24 * HOUR_MILLIS
    private const val WEEK_MILLIS = 7 * DAY_MILLIS

    fun getTimeAgo(currentTime: Long): String? {

        var time = currentTime
        if (time < 1000000000000L) {
            time *= 1000
        }

        val now = System.currentTimeMillis()
        if (time > now || time <= 0) {
            return null
        }

        val diff = now - time
        return when {
            diff < MINUTE_MILLIS -> {
                "now"
            }
            diff < 2 * MINUTE_MILLIS -> {
                "m"
            }
            diff < 50 * MINUTE_MILLIS -> {
                (diff / MINUTE_MILLIS).toString() + " m"
            }
            diff < 90 * MINUTE_MILLIS -> {
                "hr"
            }
            diff < 24 * HOUR_MILLIS -> {
                (diff / HOUR_MILLIS).toString() + "hrs"
            }
            diff < 48 * HOUR_MILLIS -> {
                "yesterday"
            }
            diff < 7 * DAY_MILLIS -> {
                (diff / DAY_MILLIS).toString() + " days"
            }
            diff < 2 * WEEK_MILLIS -> {
                "a week"
            }
            else -> {
                (diff / WEEK_MILLIS).toString() + " weeks"
            }
        }
    }

}