package com.example.webworks.neostore.Product

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.Toast
import com.example.webworks.neostore.R
import com.example.webworks.neostore.asynchtask.APIClient
import com.example.webworks.neostore.asynchtask.APIInterface
import com.example.webworks.neostore.productmodel.ProductModel
import com.example.webworks.neostore.productmodel.ProductResponseModel
import kotlinx.android.synthetic.main.activity_products.*
import kotlinx.android.synthetic.main.toolbar_nav.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductActivity : AppCompatActivity() {

    lateinit var apiInterface: APIInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)

        tv_back.setOnClickListener {
            finish()
        }
        val id = intent.getIntExtra("id",0)
        var catagory = intent.getStringExtra("catagoryName")

        apiInterface = APIClient().getClient().create(APIInterface::class.java)

        apiInterface.getProduct(id).enqueue(object : Callback<ProductResponseModel> {
            override fun onFailure(call: Call<ProductResponseModel>?, t: Throwable?) {
                Toast.makeText(this@ProductActivity,"Failed"+t!!.message,Toast.LENGTH_LONG).show()
                Log.e("TAG",""+t.message)
            }
            override fun onResponse(call: Call<ProductResponseModel>?, response: Response<ProductResponseModel>?) {

                toolbar_txt.text=catagory
                val data : List<ProductModel>? = response!!.body().data
                val recyclerView = recycler_view

                recyclerView.layoutManager = LinearLayoutManager(this@ProductActivity)
                recyclerView.adapter = ProductAdapter(data!!,this@ProductActivity,catagory)
            }
        })
    }
}