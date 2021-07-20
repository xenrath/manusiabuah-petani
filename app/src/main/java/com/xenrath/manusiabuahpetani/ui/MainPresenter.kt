package com.xenrath.manusiabuahpetani.ui

class MainPresenter(val view: MainContract.View) {

    init {
        view.initListener()
    }

}