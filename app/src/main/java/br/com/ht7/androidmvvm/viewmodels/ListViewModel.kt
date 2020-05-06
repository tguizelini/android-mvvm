package br.com.ht7.androidmvvm.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.ht7.androidmvvm.di.components.DaggerCountryApiComponent
import br.com.ht7.androidmvvm.models.Country
import br.com.ht7.androidmvvm.models.CountryService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ListViewModel : ViewModel() {
    @Inject
    lateinit var service: CountryService

    private val disposable = CompositeDisposable()

    val countries = MutableLiveData<List<Country>>()
    val loadingError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    init {
        DaggerCountryApiComponent.create().inject(this)
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

    fun refresh() {
        fetchCountries()
    }

    private fun fetchCountries() {
        loading.value = true
        loadingError.value = false

        disposable.add(
            service.getCountries()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<List<Country>>() {
                    override fun onSuccess(value: List<Country>?) {
                        loading.value = false

                        value?.let {
                            countries.value = value
                        }
                    }

                    override fun onError(e: Throwable?) {
                        loading.value = false
                        loadingError.value = true
                        countries.value = arrayListOf()
                    }

                })
        )

        //countries.value = mockData
    }
}