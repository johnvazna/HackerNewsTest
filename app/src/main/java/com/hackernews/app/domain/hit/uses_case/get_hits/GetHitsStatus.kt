package com.hackernews.app.domain.hit.uses_case.get_hits

import com.hackernews.app.utils.Status

/** */
typealias GetHitsStatus =
    Status<GetHitsFailure, GetHitsResponse>