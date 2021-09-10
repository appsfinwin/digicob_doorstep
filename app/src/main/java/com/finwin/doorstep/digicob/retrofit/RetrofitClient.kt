package com.finwin.doorstep.digicob.retrofit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitClient {
    private var instance: Retrofit? = null
    private var retrofitPostOfiice: Retrofit? = null

    public fun RetrofitClient(): Retrofit? {
        if (instance == null) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.apply { interceptor.level = HttpLoggingInterceptor.Level.BODY }
            val client: OkHttpClient = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .readTimeout(15, TimeUnit.SECONDS)
                .connectTimeout(15, TimeUnit.SECONDS)
                .build()

            instance = Retrofit.Builder()
               //.baseUrl("http://doorstep.tunl.digicob.in/")// thirukochi
               //.baseUrl("http://www.finwintechnologies.com:8888/")
                .baseUrl("http://192.168.0.221:169/")//local
                //.baseUrl("http://doorstep.tunl.digicob.in/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        }
            return  instance
    }

    public fun RetrofitClientPostOfiice(): Retrofit? {
        if (retrofitPostOfiice == null) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.apply { interceptor.level = HttpLoggingInterceptor.Level.BODY }
            val client: OkHttpClient = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .readTimeout(15, TimeUnit.SECONDS)
                .connectTimeout(15, TimeUnit.SECONDS)
                .build()

            retrofitPostOfiice = Retrofit.Builder()

                .baseUrl("https://globalvalues.digicob.in/api/postoffice/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        }
            return  retrofitPostOfiice
    }

}