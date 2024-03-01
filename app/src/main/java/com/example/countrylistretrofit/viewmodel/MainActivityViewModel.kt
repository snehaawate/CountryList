package com.example.countrylistretrofit.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.countrylistretrofit.model.CountryModel
import com.example.countrylistretrofit.retrofit.RetroServiceInterface
import com.example.countrylistretrofit.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class MainActivityViewModel: ViewModel() {

    var liveDataList: MutableLiveData<List<CountryModel>?>

    init {
        liveDataList = MutableLiveData()
    }

    fun getLiveDataObserver(): MutableLiveData<List<CountryModel>?> {
        return liveDataList
    }

    fun makeAPICall() {
        var retroInstance = RetrofitInstance.getRetrofitInstance()
        var retroService = retroInstance.create(RetroServiceInterface::class.java)
        var call = retroService.getCountryList()

        call.enqueue(object :retrofit2.Callback<List<CountryModel>> {
            override fun onResponse(
                call: Call<List<CountryModel>>,
                response: Response<List<CountryModel>>
            ) {
                liveDataList.postValue(response.body())
            }

            override fun onFailure(call: Call<List<CountryModel>>, t: Throwable) {
                liveDataList.postValue(null)
            }

        })
    }
}