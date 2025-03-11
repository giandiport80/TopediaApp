package com.giandiport80.topediaapp.util

fun String?.defaultError(): String {
    return this ?: Constant.DEFAULT_ERROR
}

fun String?.toUrlProduct(): String {
    return Constant.PRODUCT_URL + this
}

fun String?.toUrlCategory() = Constant.BASE_URL + "storage/uploads/category/" + this

fun String?.toUrlSlider() = Constant.BASE_URL + "storage/uploads/slider/" + this