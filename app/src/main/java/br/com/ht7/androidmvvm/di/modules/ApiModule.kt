package br.com.ht7.androidmvvm.di.modules

import br.com.ht7.androidmvvm.models.CountryApi
import br.com.ht7.androidmvvm.models.CountryService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ApiModule {
    private val baseUrl: String = "https://restcountries.eu/rest/v2/"

    private lateinit var retrofit: Retrofit

    init {
        retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Provides
    fun countryApi(): CountryApi = retrofit.create(CountryApi::class.java)

    @Provides
    fun countryService(): CountryService = CountryService()
}