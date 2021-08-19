package com.xenrath.manusiabuahpetani.data.database.model.rajaongkir

import com.google.gson.annotations.SerializedName

data class ResponseRajaongkirTerritory(
    @SerializedName("status") val status: Boolean,
    @SerializedName("rajaongkir") val rajaongkir: DataRajaOngkirTerritory
)