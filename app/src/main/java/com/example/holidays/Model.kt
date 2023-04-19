package com.example.holidays

import com.google.gson.annotations.SerializedName

data class Model(
    @SerializedName("date") val date: String,
    @SerializedName("localName")val localName: String,
    @SerializedName("name")val name: String,
    @SerializedName("countryCode")val countryCode: String,
    @SerializedName("counties")val counties: ArrayList<String>,
    @SerializedName("fixed")val fixed: Boolean,
    @SerializedName("global")val global: Boolean,
    @SerializedName("types")val types: ArrayList<String>,
)
