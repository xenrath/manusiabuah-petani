package com.xenrath.manusiabuahpetani.ui.login

import com.xenrath.manusiabuahpetani.data.DataUser
import com.xenrath.manusiabuahpetani.data.ResponseLogin
import com.xenrath.manusiabuahpetani.data.database.PrefManager

interface LoginContract {

    interface Presenter {
        fun doLogin(email: String, password: String, level: String)
        fun setPref(prefManager: PrefManager, dataUser: DataUser)
    }

    interface View {
        fun initActivity()
        fun initListener()
        fun onLoading(loading: Boolean)
        fun onResult(responseLogin: ResponseLogin)
        fun showMessage(message: String)
    }

}