package com.xenrath.manusiabuahpetani.ui.profile

import com.xenrath.manusiabuahpetani.data.database.PrefManager

interface ProfileContract {

    interface Presenter {
        fun doLogin(prefManager: PrefManager)
        fun doLogout(prefManager: PrefManager)
    }

    interface View {
        fun initListener(view: android.view.View)
        fun onResultLogin(prefManager: PrefManager)
        fun onResultLogout()
        fun showMessage(message: String)
    }
}