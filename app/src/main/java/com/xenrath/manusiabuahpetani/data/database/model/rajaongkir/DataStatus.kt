package com.xenrath.manusiabuahpetani.data.database.model.rajaongkir

import com.google.gson.annotations.SerializedName

data class DataStatus(
    @SerializedName("code") val code: Int,
    @SerializedName("description") val desription: String
)