package com.hackernews.app.utils

/**
 * Manage the status of the execution.
 * This can be [Loading] when the execution is in process, [Failed] if the execution concluded
 * with error and [Done] it the execution concluded successfully.
 * @see Loading
 * @see Failed
 * @see Done
 */
sealed class Status<F, R> {

    /** Data type created when the execution is in process. */
    class Loading<F, R> : Status<F, R>()

    /** Data type created when the execution was failed. */
    data class Failed<F, R>(val failure: F) : Status<F, R>()

    /** Data type created when the execution was successfully. */
    data class Done<F, R>(val value: R) : Status<F, R>()

}
