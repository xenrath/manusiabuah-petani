package com.xenrath.manusiabuahpetani.ui.product.create

import android.widget.EditText
import com.xenrath.manusiabuahpetani.data.database.model.ResponseProductUpdate
import com.xenrath.manusiabuahpetani.data.database.model.rajaongkir.ResponseRajaongkirTerritory
import java.io.File

interface ProductCreateContract {

    interface Presenter {
        fun getProvince(key: String)
        fun getCity(key: String, id: String)
        fun insertProduct(
            user_id: String,
            name: String,
            category: String,
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
            image: File,
            stock: String
        )
    }

    interface View {
        fun initActivity()
        fun initListener()
        fun onLoading(loading: Boolean, message: String? = "Loading...")
        fun onLoadingTerritory(loading: Boolean)
        fun onResult(responseProductUpdate: ResponseProductUpdate)
        fun onResultProvince(responseRajaongkirTerritory: ResponseRajaongkirTerritory)
        fun onResultCity(responseRajaongkirTerritory: ResponseRajaongkirTerritory)
        fun showAlertError(message: String)
        fun showMessage(message: String)
        fun validationError(editText: EditText, message: String)
    }

}