package com.giandiport80.topediaapp.util

fun String?.defaultError(): String {
    return this ?: Constant.DEFAULT_ERROR
}

fun String?.toUrlProduct(): String {
    return Constant.PRODUCT_URL + this
}