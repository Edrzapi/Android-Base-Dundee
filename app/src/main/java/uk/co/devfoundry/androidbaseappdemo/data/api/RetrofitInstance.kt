package uk.co.devfoundry.androidbaseappdemo.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitObject {
    const val url = "https://pokeapi.co/api/v2/"

    val instance: Retrofit by lazy {
        Retrofit.Builder().baseUrl(
            url
        ).addConverterFactory(GsonConverterFactory.create()).build()
    }

}
