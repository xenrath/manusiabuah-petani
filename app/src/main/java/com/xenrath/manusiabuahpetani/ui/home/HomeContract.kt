package com.xenrath.manusiabuahpetani.ui.home

import com.xenrath.manusiabuahpetani.data.ResponseProduct

interface HomeContract {

    interface Presenter {
        fun getProduct()
        fun searchProduct(keyword: String)
    }

    interface View {
        fun initListener(view: android.view.View)
        fun onResult(responseProduct: ResponseProduct)
        fun onLoading(loading: Boolean)
    }

}