package com.example.countrylistretrofit.retrofit

import com.example.countrylistretrofit.model.CountryModel
import retrofit2.Call
import retrofit2.http.GET

interface RetroServiceInterface {

    @GET("all")
    fun getCountryList(): Call<List<CountryModel>>
}