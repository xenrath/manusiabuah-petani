package com.xenrath.manusiabuahpetani.data.database.model.rajaongkir

import com.google.gson.annotations.SerializedName

data class DataRajaOngkirTerritory(
    @SerializedName("status") val status: DataStatus,
    @SerializedName("results") val results: List<DataResultsTerritory>
)