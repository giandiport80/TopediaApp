package com.giandiport80.topediaapp.core.data.source.model

import android.os.Parcelable
import com.giandiport80.topediaapp.R
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Slider(
    val id: Int?,
    val name: String?,

    @SerializedName("image_dummy")
    val image: Int? = R.drawable.asset_slider10,

    @SerializedName("image")
    val imageReal: String? = null,
) : Parcelable
