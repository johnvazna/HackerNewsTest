package com.hackernews.app.domain.hit.uses_case.delete_hits

import com.hackernews.app.utils.Status

/** */
typealias DeleteHitStatus =
        Status<DeleteHitFailure, DeleteHitResponse>