package com.xenrath.manusiabuahpetani.ui.product.update

import com.xenrath.manusiabuahpetani.data.database.model.ResponseProductDetail
import com.xenrath.manusiabuahpetani.data.database.model.ResponseProductUpdate
import com.xenrath.manusiabuahpetani.network.ApiService
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class ProductUpdatePresenter(val view: ProductUpdateContract.View): ProductUpdateContract.Presenter {

    init {
        view.initActivity()
        view.initListener()
    }

    override fun getDetail(id: Long) {
        view.onLoading(true)
        ApiService.endPoint.getProductDetail(id).enqueue(object :
            Callback<ResponseProductDetail> {
            override fun onResponse(
                call: Call<ResponseProductDetail>,
                response: Response<ResponseProductDetail>
            ) {
                view.onLoading(false)
                if (response.isSuccessful) {
                    val responseProductDetail: ResponseProductDetail? = response.body()
                    view.onResultDetail(responseProductDetail!!)
                }
            }

            override fun onFailure(call: Call<ResponseProductDetail>, t: Throwable) {
                view.onLoading(false)
            }
        })
    }

    override fun updateProduct(
        id: Long,
        name: String,
        price: String,
        description: String,
        address: String,
        latitude: String,
        longitude: String,
        image: File?,
        stock: String
    ) {
        val requestBody: RequestBody
        val multipartBody: MultipartBody.Part

        if (image != null){
            requestBody = image.asRequestBody("image/*".toMediaTypeOrNull())
            multipartBody = MultipartBody.Part.createFormData("image", image.name, requestBody)
        } else {
            requestBody = "".toRequestBody("image/*".toMediaTypeOrNull())
            multipartBody = MultipartBody.Part.createFormData("image", "", requestBody)
        }

        view.onLoading(true)
        ApiService.endPoint.updateProduct(
            id,
            name,
            price,
            description,
            address,
            latitude,
            longitude,
            multipartBody,
            stock,
            "PUT"
        ).enqueue(object : Callback<ResponseProductUpdate> {
            override fun onResponse(
                call: Call<ResponseProductUpdate>,
                response: Response<ResponseProductUpdate>
            ) {
                view.onLoading(false)
                if (response.isSuccessful) {
                    val responseProductUpdate: ResponseProductUpdate? = response.body()
                    view.onResultUpdate(responseProductUpdate!!)
                }
            }

            override fun onFailure(call: Call<ResponseProductUpdate>, t: Throwable) {
                view.onLoading(false)
            }

        })
    }
}