package com.example.webworks.neostore.asynchtask

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
}