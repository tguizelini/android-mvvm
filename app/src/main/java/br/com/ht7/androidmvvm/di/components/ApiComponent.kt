package br.com.ht7.androidmvvm.di.components

import br.com.ht7.androidmvvm.di.modules.ApiModule
import br.com.ht7.androidmvvm.models.CountryService
import dagger.Component

@Component(modules = [ApiModule::class])
interface ApiComponent {
    fun inject(service: CountryService)
}