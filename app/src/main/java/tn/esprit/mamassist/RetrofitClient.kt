package tn.esprit.mamassist

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tn.esprit.mamassist.data.network.ApiService

object RetrofitClient {
    private const val BASE_URL = "https://your-api-base-url.com/" // Remplacez par l'URL de votre API

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getApiService(): ApiService = retrofit.create(ApiService::class.java)
}
