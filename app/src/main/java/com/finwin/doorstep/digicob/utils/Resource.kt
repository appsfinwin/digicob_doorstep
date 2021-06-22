package com.finwin.doorstep.digicob.utils

import com.finwin.doorstep.digicob.utils.Status.ERROR
import com.finwin.doorstep.digicob.utils.Status.SUCCESS
import com.finwin.doorstep.digicob.utils.Status.LOADING


data class Resource<out T>(val status: Status, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T): Resource<T> = Resource(status = SUCCESS, data = data, message = null)

        fun <T> error(data: T?, message: String): Resource<T> =
            Resource(status = Status.ERROR, data = data, message = message)

        fun <T> loading(data: T?): Resource<T> = Resource(status = Status.LOADING, data = data, message = null)
    }
}