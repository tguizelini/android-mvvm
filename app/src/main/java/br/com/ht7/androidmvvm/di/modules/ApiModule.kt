package br.com.ht7.androidmvvm.di.modules

import br.com.ht7.androidmvvm.models.CountryApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ApiModule {
    private val baseUrl: String = "https://restcountries.eu/rest/v2/"

    @Provides
    fun countryApi(): CountryApi = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(CountryApi::class.java)
}