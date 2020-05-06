package br.com.ht7.androidmvvm.models

import com.google.gson.annotations.SerializedName

data class Country (
    @SerializedName("name")
    val name: String? = "",
    @SerializedName("flag")
    val flag: String? = "",
    @SerializedName("capital")
    val capital: String? = ""
)