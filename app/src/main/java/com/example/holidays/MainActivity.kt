package com.example.holidays

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.holidays.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private val myDataList: MutableList<Model> = mutableListOf()
    val adapter = HolidayAdapter(myDataList)
    private val baseUrl = "https://date.nager.at/api/v3/publicholidays/2023/DE/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.holidayRecycler.adapter = adapter
        binding.holidayRecycler.layoutManager = LinearLayoutManager(this)

        fetchDataFromApi()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable("recyclerViewState",binding.holidayRecycler.layoutManager?.onSaveInstanceState())
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val savedRecyclerState: Parcelable? = savedInstanceState.getParcelable("recyclerViewState")
        binding.holidayRecycler.layoutManager?.onRestoreInstanceState(savedRecyclerState)
    }

    /*override fun onPause() {
        super.onPause()
        mBundleRecyclerState = Bundle()
        val listState: Parcelable? = binding.holidayRecycler.layoutManager?.onSaveInstanceState()
        mBundleRecyclerState.putParcelable(KEY_RECYCLER_STATE, listState)
    }

    override fun onResume() {
        super.onResume()
        val listState: Parcelable? = mBundleRecyclerState.getParcelable(KEY_RECYCLER_STATE)
        binding.holidayRecycler.layoutManager?.onRestoreInstanceState(listState)
    }*/

    // Function to retrieve data from API
    private fun fetchDataFromApi() {
        // Create a Retrofit instance
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create()) // Use Gson for JSON parsing
            .build()

        // Create an API service interface
        val apiService = retrofit.create(ApiService::class.java)
        Log.v("Inside","Below apiService")

        // Call the API and enqueue the request
        apiService.getData().enqueue(object : Callback<List<Model>> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(call: Call<List<Model>>, response: Response<List<Model>>) {
                if (response.isSuccessful) {
                    // Handle successful response
                    Log.v("Inside","Inside response.isSuccessful")
                    val data = response.body()
                    myDataList.clear()
                    myDataList.addAll(data?: emptyList())
                    adapter.notifyDataSetChanged()
                } else {
                    // Handle error response
                    Toast.makeText(this@MainActivity,response.errorBody().toString(),Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<List<Model>>, t: Throwable) {
                // Handle API call failure
                Toast.makeText(this@MainActivity,t.message.toString(),Toast.LENGTH_LONG).show()
                // Log.e("Exception",t.message.toString())
            }
        })
    }

    // Define an API service interface using Retrofit annotations
    interface ApiService {
        // Define API endpoint and request method
        @GET("https://date.nager.at/api/v3/publicholidays/2023/DE/") // Replace with your API endpoint
        fun getData(): Call<List<Model>>
    }
}
