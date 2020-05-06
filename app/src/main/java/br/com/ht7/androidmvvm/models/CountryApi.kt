package br.com.ht7.androidmvvm.models

import io.reactivex.Single
import retrofit2.http.GET

interface CountryApi {
    @GET("all")
    fun all(): Single<List<Country>>
}