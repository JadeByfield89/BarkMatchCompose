package com.demo.barkmatchcompose.application

import android.app.Application
import com.demo.barkmatchcompose.api.DogBreedAPI
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class BarkMatchComposeApplication: Application() {
    var api: DogBreedAPI? = null

    // Let's use the singleton pattern here since we only need to initialize the RetroFit object once
    companion object {
        private lateinit var instance: BarkMatchComposeApplication


        fun getInstance(): BarkMatchComposeApplication {
            return instance
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        var retrofit = Retrofit.Builder()
            .baseUrl("https://dog.ceo/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

        api = retrofit.create(DogBreedAPI::class.java);
    }

    fun getAPI(): DogBreedAPI? {
        return api
    }
}