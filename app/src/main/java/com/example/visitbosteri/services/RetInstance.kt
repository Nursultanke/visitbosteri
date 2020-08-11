package com.example.visitbosteri.services

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetInstance{

    companion object{

        val url = "https://bosteri.herokuapp.com/"
        fun getRetrofitInstance(): Retrofit {

            return Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build()
        }
    }

}