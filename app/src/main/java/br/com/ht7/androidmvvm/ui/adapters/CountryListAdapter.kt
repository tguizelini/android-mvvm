package br.com.ht7.androidmvvm.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.ht7.androidmvvm.R
import br.com.ht7.androidmvvm.models.Country
import br.com.ht7.androidmvvm.extensions.getProgressDrawable
import br.com.ht7.androidmvvm.extensions.loadImage
import kotlinx.android.synthetic.main.item_country.view.*

class CountryListAdapter(private val countries: ArrayList<Country>): RecyclerView.Adapter<CountryListAdapter.CountryListViewHolder>() {

    class CountryListViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val countryName = view.tv_item_country_name
        private val countryFlag = view.iv_item_country
        private val countryCapital = view.tv_item_country_capital

        private val progressDrawable =
            getProgressDrawable(view.context)

        fun bind(country: Country) {
            countryName.text = country.name
            countryCapital.text = country.capital

            countryFlag.loadImage(country.flag, progressDrawable)
        }
    }

    fun update(newCountries: List<Country>) {
        countries.clear()
        countries.addAll(newCountries)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CountryListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_country, parent, false)
        )

    override fun getItemCount(): Int = countries.size

    override fun onBindViewHolder(holder: CountryListViewHolder, position: Int) {
        holder.bind(countries[position])
    }
}