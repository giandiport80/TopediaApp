package com.giandiport80.topediaapp.core.data.source.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Category(
    val id: Int?,
    val name: String?,

    @SerializedName("image_dummy")
    val image: Int,

    @SerializedName("image")
    val imageReal: String? = null
) : Parcelable
