package br.com.ht7.androidmvvm.models

import br.com.ht7.androidmvvm.di.components.DaggerCountryApiComponent
import io.reactivex.Single
import javax.inject.Inject

class CountryService {
    @Inject
    lateinit var api: CountryApi

    init {
        DaggerCountryApiComponent.create().inject(this)
    }

    fun getCountries(): Single<List<Country>> = api.all()
}