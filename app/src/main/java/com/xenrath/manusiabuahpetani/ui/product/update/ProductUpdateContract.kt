package com.xenrath.manusiabuahpetani.ui.product.update

import com.xenrath.manusiabuahpetani.data.database.model.ResponseProductDetail
import com.xenrath.manusiabuahpetani.data.database.model.ResponseProductUpdate
import java.io.File

interface ProductUpdateContract {

    interface Presenter {
        fun getDetail(id: Long)
        fun updateProduct(
            id: Long,
            name: String,
            price: String,
            description: String,
            address: String,
            latitude: String,
            longitude: String,
            image: File?,
            stock: String,
        )
    }

    interface View {
        fun initActivity()
        fun initListener()
        fun onLoading(loading: Boolean)
        fun onResultDetail(responseProductDetail: ResponseProductDetail)
        fun onResultUpdate(responseProductUpdate: ResponseProductUpdate)
        fun showMessage(message: String)
    }

}