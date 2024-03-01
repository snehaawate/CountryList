package com.example.countrylistretrofit

import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.countrylistretrofit.adapter.CountryListAdapter
import com.example.countrylistretrofit.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity() {

    lateinit var recyclerAdapter: CountryListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecyclerView()
        initViewModel()
    }

    private fun initRecyclerView() {
        countryRV.layoutManager = LinearLayoutManager(this)
        recyclerAdapter = CountryListAdapter(this)
        countryRV.adapter = recyclerAdapter
    }

    private fun initViewModel() {
        val viewModel: MainActivityViewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]
        viewModel.getLiveDataObserver().observe(this, Observer {
            if (it != null){
                recyclerAdapter.setCountryList(it)
                recyclerAdapter.notifyDataSetChanged()
            }else {
                Toast.makeText(this, "Error Error", Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.makeAPICall()
    }
}