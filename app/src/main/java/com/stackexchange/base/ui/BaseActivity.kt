package com.stackexchange.base.ui

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.stackexchange.base.BaseEvent
import com.stackexchange.base.ViewModelState
import com.stackexchange.base.viewmodel.BaseFlowViewModel
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity<STATE : ViewModelState, EVENT: BaseEvent, VIEWMODEL : BaseFlowViewModel<STATE, EVENT>> :
    DaggerAppCompatActivity() {

    protected abstract val contentResourceId: Int

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    val viewModel: VIEWMODEL by lazy {
        viewModelFactory.create(getViewModelClass())
    }

    abstract fun getViewModelClass(): Class<VIEWMODEL>


    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(contentResourceId)
        initView()
    }

    open fun initView() {}
}