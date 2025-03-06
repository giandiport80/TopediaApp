package com.giandiport80.topediaapp.core.data.source.remote.network

data class Resource<out T>(
    val state: State,
    val data: T?,
    val message: String?,
    val loading: Boolean = false
) {
    companion object {
        fun <T> success(data: T?, message: String? = null): Resource<T> {
            return Resource(State.SUCCESS, data, message)
        }

        fun <T> error(msg: String, data: T?): Resource<T> {
            return Resource(State.ERROR, data, msg)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(State.LOADING, data, null, loading = true)
        }

    }
}
