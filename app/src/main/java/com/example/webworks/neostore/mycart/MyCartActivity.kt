package com.example.webworks.neostore.mycart

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.widget.Toast
import com.example.webworks.neostore.R
import com.example.webworks.neostore.addaddress.AddAddress
import com.example.webworks.neostore.asynchtask.APIClient
import com.example.webworks.neostore.asynchtask.APIInterface
import com.example.webworks.neostore.cartmodel.DeleteCartModel
import com.example.webworks.neostore.cartmodel.ListCartModel
import com.example.webworks.neostore.cartmodel.ListCartModelResponse
import kotlinx.android.synthetic.main.my_cart_activity.*
import kotlinx.android.synthetic.main.recycler_view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyCartActivity : AppCompatActivity(), MyCartItemTouchHelper.MyCartItemTouchHelperListener {

    lateinit var apiInterface: APIInterface
    lateinit var myCartAdapter: MyCartAdapter
    var accessToken: String = ""
    var position: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.my_cart_activity)

        val sp = getSharedPreferences("userinfo", Context.MODE_PRIVATE)
        accessToken = sp.getString("access_token", "")

        apiInterface = APIClient().getClient().create(APIInterface::class.java)
        apiInterface.listCart(accessToken).enqueue(object : Callback<ListCartModelResponse> {

            override fun onFailure(call: Call<ListCartModelResponse>?, t: Throwable?) {

            }
            override fun onResponse(call: Call<ListCartModelResponse>?, response: Response<ListCartModelResponse>?) {

                if (response!!.code() == 200) {

                    val linearLayoutManager: LinearLayoutManager = LinearLayoutManager(this@MyCartActivity)

                    recyclerView.layoutManager = linearLayoutManager

                    val mDividerItemDecoration = DividerItemDecoration(recyclerView.getContext(),
                            linearLayoutManager.orientation)

                    recyclerView.addItemDecoration(mDividerItemDecoration)

                    myCartAdapter = MyCartAdapter(this@MyCartActivity, response.body().data as ArrayList<ListCartModel>, accessToken)
                    recyclerView.adapter = myCartAdapter

                    val simpleCallback = MyCartItemTouchHelper(0, ItemTouchHelper.LEFT, this@MyCartActivity)
                    ItemTouchHelper(simpleCallback).attachToRecyclerView(recyclerView)
                }
            }
        })

        btnOrderNow.setOnClickListener {

            startActivity(Intent(this@MyCartActivity, AddAddress::class.java))
        }

    }

    fun deleteItemFromCart(accessToken: String, productId: Int) {
        apiInterface.postDeleteItem(this.accessToken, productId).enqueue(object : Callback<DeleteCartModel> {
            override fun onFailure(call: Call<DeleteCartModel>?, t: Throwable?) {

            }

            override fun onResponse(call: Call<DeleteCartModel>?, response: Response<DeleteCartModel>?) {
                if (response!!.code() == 200) {
                    Toast.makeText(this@MyCartActivity, "Connected", Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int, position: Int) {

        this.position = position
        myCartAdapter.onItemRemove(position)

    }
}
