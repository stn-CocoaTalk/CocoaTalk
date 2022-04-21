package com.stn.cocoatalk.util

import com.stn.cocoatalk.domain.model.Message
import com.stn.cocoatalk.domain.model.User

object AppState {
    var currentUser: User? = null
    var authorized: Boolean = false
}