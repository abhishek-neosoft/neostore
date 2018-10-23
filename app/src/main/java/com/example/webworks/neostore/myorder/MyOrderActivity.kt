package com.example.webworks.neostore.myorder

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.example.webworks.neostore.R

import com.example.webworks.neostore.asynchtask.APIClient
import com.example.webworks.neostore.asynchtask.APIInterface
import com.example.webworks.neostore.ordermodel.OrderListModelResponse
import kotlinx.android.synthetic.main.my_order.*
import kotlinx.android.synthetic.main.recycler_view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MyOrderActivity:AppCompatActivity(){

    lateinit var apiInterface: APIInterface
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.my_order)

        apiInterface=APIClient().getClient().create(APIInterface::class.java)
        val address = intent.getStringExtra("address")
        val accessToken = intent.getStringExtra("access_token")
        Toast.makeText(this,""+address,Toast.LENGTH_SHORT).show()
        tv_back.setOnClickListener { finish() }

        apiInterface.orderList(accessToken).enqueue(object:Callback<OrderListModelResponse>{
            override fun onFailure(call: Call<OrderListModelResponse>?, t: Throwable?) {

            }

            override fun onResponse(call: Call<OrderListModelResponse>?, response: Response<OrderListModelResponse>?) {
              if (response!!.code()==200)
              {

                  recyclerView.layoutManager = LinearLayoutManager(this@MyOrderActivity)
                  recyclerView.adapter =MyOrderAdapter(response.body()!!.data)


              }
            }
        })
    }

}