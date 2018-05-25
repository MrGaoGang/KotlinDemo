package com.mrwho.kotlindemo.presenter

import com.mrwho.kotlindemo.base.BasePresenter
import com.mrwho.kotlindemo.beans.DataBean
import com.mrwho.kotlindemo.callback.MainDataCallback
import com.mrwho.kotlindemo.extendsion.toast
import com.mrwho.kotlindemo.fragment.NormalFragment
import com.mrwho.kotlindemo.model.MainModelImpl
import kotlin.properties.Delegates

class MainPresenterImpl : BasePresenter<NormalFragment>(), MainPresenter {
    private var mainModel: MainModelImpl by Delegates.notNull()
    private var currentPage = 1

    init {
        mainModel = MainModelImpl()
    }

    /**
     * 加载数据
     */
    override fun loadData(type: String) {
        mainModel.loadData(type, currentPage, object : MainDataCallback {
            override fun success(dataBean: DataBean) {
                getIView()?.adapter?.addAll(dataBean)
            }

            override fun error(message: String) {
                getIView()?.toast(message)
            }
        })
    }


    override fun onDestroy() {
    }
}