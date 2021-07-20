package com.xenrath.manusiabuahpetani.ui.home

import com.xenrath.manusiabuahpetani.data.ResponseProduct
import com.xenrath.manusiabuahpetani.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomePresenter(val view: HomeContract.View): HomeContract.Presenter {

    override fun getProduct() {
//        view.onLoading(true)
//        ApiService.endPoint.getProduct().enqueue(object : Callback<ResponseProduct> {
//            override fun onResponse(
//                call: Call<ResponseProduct>,
//                response: Response<ResponseProduct>
//            ) {
//                view.onLoading(false)
//                if (response.isSuccessful){
//                    val responseProduct: ResponseProduct? = response.body()
//                    view.onResult(responseProduct!!)
//                }
//            }
//
//            override fun onFailure(call: Call<ResponseProduct>, t: Throwable) {
//
//            }
//
//        })
    }

    override fun searchProduct(keyword: String) {
//        view.onLoading(true)
//        ApiService.endPoint.searchProduct(keyword).enqueue(object : Callback<ResponseProduct> {
//            override fun onResponse(
//                call: Call<ResponseProduct>,
//                response: Response<ResponseProduct>
//            ) {
//                view.onLoading(false)
//                if (response.isSuccessful) {
//                    val responseProduct: ResponseProduct? = response.body()
//                    view.onResult(responseProduct!!)
//                }
//            }
//
//            override fun onFailure(call: Call<ResponseProduct>, t: Throwable) {
//                view.onLoading(false)
//            }
//
//        })
    }
}