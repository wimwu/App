package com.wim.network

sealed class NetworkError : Exception(){
    object NoInternet : NetworkError() {
        private fun readResolve(): Any = NoInternet
    }

    object Timeout : NetworkError() {
        private fun readResolve(): Any = Timeout
    }

    object ServerError : NetworkError() {
        private fun readResolve(): Any = ServerError
    }

    data class Unknown(override val message: String) : NetworkError()
}