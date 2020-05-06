package br.com.ht7.androidmvvm.di.components

import br.com.ht7.androidmvvm.di.modules.ApiModule
import br.com.ht7.androidmvvm.models.CountryService
import br.com.ht7.androidmvvm.viewmodels.ListViewModel
import dagger.Component

@Component(modules = [ApiModule::class])
interface CountryApiComponent {
    fun inject(service: CountryService)
    fun inject(viewModel: ListViewModel)
}