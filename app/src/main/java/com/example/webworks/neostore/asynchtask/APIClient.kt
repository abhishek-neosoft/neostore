package com.example.webworks.neostore.asynchtask

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class APIClient{

    val BASE_URL = "http://staging.php-dev.in:8844/trainingapp/api/"
    private var retrofit: Retrofit? = null


    fun getClient(): Retrofit {

        if (retrofit == null) {

            retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
        }
        return retrofit!!
    }

// kotlin coroutines retrofit
    fun getRetrofitService(): APIInterface {

        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(APIInterface::class.java)
    }
}