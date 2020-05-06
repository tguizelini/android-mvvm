package br.com.ht7.androidmvvm.models

import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class CountryService {
    private val baseUrl: String = "https://restcountries.eu/rest/v2/"
    private val api: CountryApi

    init {
        api = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(CountryApi::class.java)
    }

    fun getCountries(): Single<List<Country>> = api.all()
}