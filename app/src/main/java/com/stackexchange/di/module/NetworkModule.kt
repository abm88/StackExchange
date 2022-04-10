package com.stackexchange.di.module

import android.content.Context
import androidx.annotation.NonNull
import com.stackexchange.BuildConfig
import com.stackexchange.data.network.GetUsersAPI
import com.stackexchange.error.ErrorContainer
import com.stackexchange.error.GeneralHandlerImpl
import com.stackexchange.util.GliderImageLoaderImpl
import com.stackexchange.util.ImageLoader
import com.stackexchange.util.NetworkInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideHttpClient(context: Context): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(NetworkInterceptor())
            .cache(null)
            .readTimeout(10, TimeUnit.SECONDS)
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }).build()

    @Provides
    @Singleton
    fun provideRetrofit(
        @NonNull client: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    fun provideErrorHandler(errorHandler: GeneralHandlerImpl): ErrorContainer = errorHandler

    @Provides
    fun provideImageLoader(imageLoader: GliderImageLoaderImpl): ImageLoader = imageLoader

    @Provides
    fun provideGetUsersAPIService(@NonNull retrofit: Retrofit): GetUsersAPI =
        retrofit.create(GetUsersAPI::class.java)


}