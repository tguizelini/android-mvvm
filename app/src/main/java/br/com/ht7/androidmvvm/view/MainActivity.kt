package br.com.ht7.androidmvvm.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.ht7.androidmvvm.R
import br.com.ht7.androidmvvm.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: ListViewModel

    private val countryListAdapter = CountryListAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)
        viewModel.refresh()

        rv_countries.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = countryListAdapter
        }

        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = false
            viewModel.refresh()
        }

        viewModelObservers()
    }

    private fun viewModelObservers() {
        viewModel.countries.observe(this, Observer { countries ->
            countries?.let {
                countryListAdapter.update(it)
                rv_countries.visibility = View.VISIBLE
            }
        })

        viewModel.loading.observe(this, Observer {isLoading: Boolean? ->
            isLoading?.let {
                loading_view.visibility = if (it) View.VISIBLE else View.GONE

                if (it) {
                    rv_countries.visibility = View.GONE
                    tv_error.visibility = View.GONE
                }
            }
        })

        viewModel.loadingError.observe(this, Observer {isError: Boolean? ->
            isError?.let {
                tv_error.visibility = if (it) View.VISIBLE else View.GONE
            }
        })
    }
}
