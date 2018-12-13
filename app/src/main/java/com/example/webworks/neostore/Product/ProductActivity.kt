package com.example.webworks.neostore.Product

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.DividerItemDecoration.VERTICAL
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.Toast
import com.example.webworks.neostore.R
import com.example.webworks.neostore.asynchtask.APIClient
import com.example.webworks.neostore.description.ProductDescriptionActivity
import com.example.webworks.neostore.productmodel.ProductModel
import kotlinx.android.synthetic.main.activity_products.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch


class ProductActivity : AppCompatActivity(), CoProductAdapter.CallbackAdapter {

    //lateinit var apiInterface: APIInterface
    var data: List<ProductModel>? = null
    var catagory: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)

        tv_back.setOnClickListener { finish() }
        val id = intent.getIntExtra("id", 0)
        catagory = intent.getStringExtra("catagoryName")

        val service = APIClient().getRetrofitService()

        launch(UI) {
            val request = service.getProducts(id)
            val response = request.await()
            if (response.isSuccessful) {

                Log.i("Success", response.message())
                toolbar_txt.text = catagory
                data = response.body()!!.data
                val recyclerView = recycler_view

                recyclerView.layoutManager = LinearLayoutManager(this@ProductActivity, LinearLayoutManager.VERTICAL, false)
                val decoration = DividerItemDecoration(this@ProductActivity, VERTICAL)
                recyclerView.addItemDecoration(decoration)
                recyclerView.adapter = CoProductAdapter(this@ProductActivity, data!!, this@ProductActivity)

            } else {
                Log.e("Failure", "failed")
            }
        }
    }

    override fun adapterPosition(position: Int) {
        Toast.makeText(this@ProductActivity, "adapter " + position, Toast.LENGTH_SHORT).show()
        startActivity(Intent(this@ProductActivity, ProductDescriptionActivity::class.java)
                .putExtra("product_id", data!![position].id)
                .putExtra("catagoryName", catagory))
    }
}

/* apiInterface = APIClient().getClient().create(APIInterface::class.java)

 apiInterface.getProduct(id).enqueue(object : Callback<ProductResponseModel> {
     override fun onFailure(call: Call<ProductResponseModel>?, t: Throwable?) {
         Toast.makeText(this@ProductActivity,"Failed"+t!!.message,Toast.LENGTH_LONG).show()
         Log.e("TAG",""+t.message)
     }
     override fun onResponse(call: Call<ProductResponseModel>?, response: Response<ProductResponseModel>?) {

         toolbar_txt.text=catagory
         val data : List<ProductModel>? = response!!.body()!!.data
         val recyclerView = recycler_view

         recyclerView.layoutManager = LinearLayoutManager(this@ProductActivity)
         recyclerView.adapter = ProductAdapter(data!!,this@ProductActivity,catagory)
     }
 })*/
