package com.google.asiancountriesapp.utils

import kotlin.Exception

sealed class Resource<T>(val data: T? = null, val message: Exception? = null) {
    class Success<T>(data: T) : Resource<T>(data = data)
    class Error<T>(message: Exception, data: T? = null) : Resource<T>(data = data, message)
    class Loading<T>(data: T? = null) : Resource<T>(data)
}