package com.stackexchange.util

import okhttp3.Interceptor
import okhttp3.Response
import java.util.concurrent.TimeUnit

class NetworkInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(chain.withReadTimeout(4, TimeUnit.SECONDS).request())
    }
}

