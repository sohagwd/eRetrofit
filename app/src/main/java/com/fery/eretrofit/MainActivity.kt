package com.fery.eretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {
    lateinit var txtdata: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtdata = findViewById(R.id.txtData)
//        getUserList()

        fun getUserList() {
            var retrofit = RetrofitClient.getInstance()
            var apiInterface = retrofit.create(ApiInterfaceApi::class.java)
            lifecycleScope.launchWhenCreated {
                try {
                    val response = apiInterface.getAllUsers()
                    if (response.isSuccessful()) {
                        //your code for handaling success response
                        var json = Gson.toJson(response.body())
                        if (response.body()?.data?.size!! < 0){
                            Toast.makeText(
                                this@MainActivity,
                                "No Data",
                            Toast.LENGTH_LONG
                            ).show()
                        } else{
                            txtdata.setText(json)
                        }

                    } else {
                        Toast.makeText(
                            this@MainActivity,
                            response.errorBody().toString(),
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }catch (Ex:Exception){
                    Log.e("Error",Ex.localizedMessage)
                }
            }

        }

    }
}