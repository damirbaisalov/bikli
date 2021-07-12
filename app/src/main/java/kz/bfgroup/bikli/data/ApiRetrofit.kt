package kz.bfgroup.bikli.data

import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

object ApiRetrofit {

    const val BASE_URL = "http://bikli.kz/apiBikli/"

    fun getApiClient(): ApiClient {
        val apiRetrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(JacksonConverterFactory.create())
            .build()

        return apiRetrofit.create(ApiClient::class.java)
    }

}