package com.xenrath.manusiabuahpetani.data.database.model

import com.google.gson.annotations.SerializedName

data class ResponseProductList(
    @SerializedName("status") val status: Boolean,
    @SerializedName("message") val message: String?,
    @SerializedName("products") val products: List<DataProduct>?
)