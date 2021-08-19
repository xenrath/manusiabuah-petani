package com.xenrath.manusiabuahpetani.ui.product.update

import android.widget.EditText
import com.xenrath.manusiabuahpetani.data.database.model.ResponseProductDetail
import com.xenrath.manusiabuahpetani.data.database.model.ResponseProductUpdate
import com.xenrath.manusiabuahpetani.data.database.model.rajaongkir.ResponseRajaongkirTerritory
import java.io.File

interface ProductUpdateContract {

    interface Presenter {
        fun getDetail(id: Long)
        fun getProvince(key: String)
        fun getCity(key: String, id: String)
        fun updateProduct(
            id: Long,
            name: String,
            price: String,
            description: String,
            address: String,
            province_id: String,
            province_name: String,
            city_id: String,
            city_name: String,
            postal_code: String,
            latitude: String,
            longitude: String,
            image: File?,
            stock: String,
        )
    }

    interface View {
        fun initActivity()
        fun initListener()
        fun onLoading(loading: Boolean, message: String? = "Loading...")
        fun onLoadingTerritory(loading: Boolean)
        fun onResultDetail(responseProductDetail: ResponseProductDetail)
        fun onResultUpdate(responseProductUpdate: ResponseProductUpdate)
        fun onResultProvince(responseRajaongkirTerritory: ResponseRajaongkirTerritory)
        fun onResultCity(responseRajaongkirTerritory: ResponseRajaongkirTerritory)
        fun showAlertError(message: String)
        fun showMessage(message: String)
        fun validationError(editText: EditText, message: String)
    }

}