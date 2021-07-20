package com.xenrath.manusiabuahpetani.data.database.model

import com.google.gson.annotations.SerializedName

data class ResponseBargainList(
    @SerializedName("status") val status: Boolean,
    @SerializedName("bargains") val bargains: List<DataBargain>
)
