package com.itis.androiditis_hw_2.di.modules

import androidx.viewbinding.BuildConfig
import com.itis.androiditis_hw_2.data.api.SeriesApi
import com.itis.androiditis_hw_2.di.qualifiers.LoggingInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://www.breakingbadapi.com/api/"

@Module
@InstallIn(SingletonComponent::class)
class NetModule {

    @Provides
    @LoggingInterceptor
    fun provideLoggingInterceptor(): Interceptor {
        return HttpLoggingInterceptor()
            .setLevel(
                HttpLoggingInterceptor.Level.BODY
            )
    }
    @Provides
    fun provideOkhttp(
        @LoggingInterceptor loggingInterceptor: Interceptor,
    ): OkHttpClient =
        OkHttpClient.Builder()
            .also {
                if (BuildConfig.DEBUG) {
                    it.addInterceptor(loggingInterceptor)
                }
            }
            .build()

    @Provides
    fun provideGsonConverter(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    fun provideApi(
        okHttpClient: OkHttpClient,
        gsonConverter: GsonConverterFactory
    ): SeriesApi =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonConverter)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
            .create(SeriesApi::class.java)
}
