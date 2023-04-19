package com.example.holidays

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.GET

// Function to retrieve data from API
fun fetchDataFromApi() {
    // Create a Retrofit instance
    val retrofit = Retrofit.Builder()
        .baseUrl("https://date.nager.at/api/v3/publicholidays/") // Replace with your API base URL
            // https://date.nager.at/api/v3/publicholidays/2023/AT
        .addConverterFactory(GsonConverterFactory.create()) // Use Gson for JSON parsing
        .build()

    // Create an API service interface
    val apiService = retrofit.create(ApiService::class.java)

    // Call the API and enqueue the request
    apiService.getData().enqueue(object : Callback<List<Model>> {
        override fun onResponse(call: Call<List<Model>>, response: Response<List<Model>>) {
            if (response.isSuccessful) {
                // Handle successful response
                val data = response.body()
                if (data != null) {
                    //val adapter = HolidayAdapter(data)
                    // ...
                }
            } else {
                // Handle error response
                // ...
            }
        }

        override fun onFailure(call: Call<List<Model>>, t: Throwable) {
            // Handle API call failure
            // ...
        }
    })
}

// Define an API service interface using Retrofit annotations
interface ApiService {
    // Define API endpoint and request method
    @GET("endpoint") // Replace with your API endpoint
    fun getData(): Call<List<Model>>
}
