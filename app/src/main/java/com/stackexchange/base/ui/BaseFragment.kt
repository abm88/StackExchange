package com.stackexchange.base.ui

import android.content.Context
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment


abstract class BaseFragment : DaggerFragment() {

    protected abstract val contentResourceId: Int

    override fun onAttach(context: Context) {
        injectMembers()
        super.onAttach(context)
    }

    protected open fun injectMembers() {
        AndroidSupportInjection.inject(this)
    }

}