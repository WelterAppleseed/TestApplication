package com.example.testapplication.data.modules

import com.example.testapplication.BuildConfig
import com.example.testapplication.domain.models.remote.Api
import com.example.testapplication.util.objs.FoodConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FoodModule {
    @Singleton
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.apply {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        }
        return httpLoggingInterceptor
    }

    @Singleton
    @Provides
   fun provideBaseInterceptor(): Interceptor {
        return Interceptor.invoke { chain ->
            val newUrl = chain
                .request()
                .url
                .newBuilder()
                .build()

            val request = chain
                .request()
                .newBuilder()
                .url(newUrl)
                .build()

            return@invoke chain.proceed(request)
        }
    }

    @Singleton
    @Provides
    fun provideClient(): OkHttpClient {
        return OkHttpClient
            .Builder()
            .addInterceptor(provideLoggingInterceptor())
            .addInterceptor(provideBaseInterceptor())
            .addInterceptor(object : Interceptor {
                override fun intercept(chain: Interceptor.Chain): Response {
                    var request: Request = chain.request()
                    val url: HttpUrl =
                        request.url.newBuilder().addQueryParameter("apiKey", BuildConfig.API_KEY)
                            .build()
                    request = request.newBuilder().url(url).build()
                    return chain.proceed(request)
                }

            })
            .build()
    }

    @Singleton
    @Provides
    fun provideFoodApi(): Api {
        return Retrofit.Builder()
            .baseUrl(FoodConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(provideClient())
            .build()
            .create(Api::class.java)
    }

}