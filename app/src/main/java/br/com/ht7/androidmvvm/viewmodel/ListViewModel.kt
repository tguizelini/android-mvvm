package br.com.ht7.androidmvvm.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.ht7.androidmvvm.model.Country
import br.com.ht7.androidmvvm.model.CountryService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class ListViewModel : ViewModel() {
    private val service: CountryService = CountryService()
    private val disposable = CompositeDisposable()

    val countries = MutableLiveData<List<Country>>()
    val loadingError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

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
                        Log.d("ListVM", e?.message)
                        loading.value = false
                        loadingError.value = true
                        countries.value = listOf()
                    }

                })
        )

        //countries.value = mockData
    }
}