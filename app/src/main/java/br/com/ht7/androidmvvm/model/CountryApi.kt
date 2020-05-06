package br.com.ht7.androidmvvm.model

import io.reactivex.Single
import retrofit2.http.GET

interface CountryApi {
    @GET("all")
    fun all(): Single<List<Country>>
}