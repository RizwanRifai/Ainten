package com.example.app.api

import com.example.fragmen_kotlin.api.SupabaseApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    private const val BASE_URL = "https://ynrxgkbvrpnnzczeaakl.supabase.co/rest/v1/"

    fun create(): SupabaseApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SupabaseApi::class.java)
    }
}
