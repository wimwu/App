package com.wim.network

import kotlinx.serialization.Serializable

@Serializable
data class BaseResponse<T>(
    val data: T,
)
