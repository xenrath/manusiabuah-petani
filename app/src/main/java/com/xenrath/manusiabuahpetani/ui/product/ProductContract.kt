package com.xenrath.manusiabuahpetani.ui.product

import com.xenrath.manusiabuahpetani.data.database.model.DataProduct
import com.xenrath.manusiabuahpetani.data.database.model.ResponseProductList
import com.xenrath.manusiabuahpetani.data.database.model.ResponseProductUpdate

interface ProductContract {

    interface Presenter {
        fun getProduct(user_id: String)
        fun deleteProduct(id: Long)
    }

    interface View {
        fun initActivity()
        fun initListener()
        fun onLoading(loading: Boolean)
        fun onResult(resultProductList: ResponseProductList)
        fun onResultDelete(responseProductUpdate: ResponseProductUpdate)
        fun showDialogDelete(dataProduct: DataProduct, position: Int)
        fun showDialogDetail(dataProduct: DataProduct, position: Int)
        fun showMessage(message: String)
    }

}