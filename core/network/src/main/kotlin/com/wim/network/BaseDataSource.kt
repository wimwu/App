package com.wim.network

import java.io.IOException
import java.net.SocketTimeoutException

interface BaseDataSource {
    suspend fun <T> callBack(call: suspend () -> T): Result<T>
}

suspend fun <T> BaseDataSource.callBack(call: suspend () -> T): Result<T> {
    return try {
        Result.Success(call.invoke())
    } catch (throwable: Throwable) {
        Result.Error(mapExceptionToNetworkError(throwable))
    }
}

private fun mapExceptionToNetworkError(throwable: Throwable): NetworkError {
    return when (throwable) {
        is SocketTimeoutException -> NetworkError.Timeout
        is IOException -> NetworkError.NoInternet
        else -> NetworkError.Unknown(throwable.message ?: "")
    }

}