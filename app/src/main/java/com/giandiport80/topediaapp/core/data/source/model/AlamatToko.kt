package com.giandiport80.topediaapp.core.data.source.model

import com.google.gson.annotations.SerializedName

data class AlamatToko(
    @SerializedName("id")
    val id: Int? = null,

    @SerializedName("toko_id")
    val tokoId: Int? = null,

    @SerializedName("alamat")
    val alamat: String? = null,

    @SerializedName("provinsi_id")
    val provinsiId: String? = null,

    @SerializedName("provinsi")
    val provinsi: String? = null,

    @SerializedName("kota_id")
    val kotaId: String? = null,

    @SerializedName("kota")
    val kota: String? = null,

    @SerializedName("kecamatan_id")
    val kecamatanId: String? = null,

    @SerializedName("kecamatan")
    val kecamatan: String? = null,

    @SerializedName("kodepos")
    val kodepos: String? = null,

    @SerializedName("phone")
    val phone: String? = null,

    @SerializedName("email")
    val email: String? = null,

    @SerializedName("is_active")
    val isActive: Boolean? = null,

    @SerializedName("created_at")
    val createdAt: String? = null,

    @SerializedName("updated_at")
    val updatedAt: String? = null
)
