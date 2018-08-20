package com.example.webworks.neostore.addaddress

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.example.webworks.neostore.R
import com.example.webworks.neostore.asynchtask.APIClient
import com.example.webworks.neostore.asynchtask.APIInterface
import com.example.webworks.neostore.myorder.MyOrderActivity
import com.example.webworks.neostore.ordermodel.PlacedOrderResponse
import com.example.webworks.neostore.sqldatabase.SqlDatabaseManage
import kotlinx.android.synthetic.main.address_list_activity.*
import kotlinx.android.synthetic.main.recycler_view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddressList : AppCompatActivity(), AddListAdapter.CallBackAdapterAddress {



    var positionAddress:String?=null
    lateinit var apiInterface: APIInterface
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.address_list_activity)

        apiInterface = APIClient().getClient().create(APIInterface::class.java)

        tv_back.setOnClickListener { finish() }
        val sp = getSharedPreferences("userinfo", MODE_PRIVATE)

        val accessToken = sp.getString("access_token", "")

        val sqlDatabaseManage=SqlDatabaseManage(this)

        val result=sqlDatabaseManage.fetchData(accessToken)
        if (result.count==0)
        {
            Toast.makeText(this, "no data found", Toast.LENGTH_LONG).show()
        }

        val listData:ArrayList<String> = ArrayList()
        val listId:ArrayList<Int> = ArrayList()
        while (result.moveToNext())
        {
            listData.add(result.getString(2))
            listId.add(result.getInt(1))
        }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter =AddListAdapter(this,listData,accessToken)


        btnOrderNow.setOnClickListener {
            if (positionAddress==null){
                Toast.makeText(this,"please select address",Toast.LENGTH_SHORT).show()
            }else {


                apiInterface.saveAddress(accessToken, positionAddress!!).enqueue(object : Callback<PlacedOrderResponse> {
                    override fun onFailure(call: Call<PlacedOrderResponse>?, t: Throwable?) {

                    }

                    override fun onResponse(call: Call<PlacedOrderResponse>?, response: Response<PlacedOrderResponse>?) {
                        if (response!!.code() == 200) {
                            startActivity(Intent(this@AddressList, MyOrderActivity::class.java)
                                    .putExtra("address", positionAddress)
                                    .putExtra("access_token", accessToken))
                        }
                    }
                })
            }
        }
    }

    override fun onClickPositionAddress(positionAddress: String) {

        this.positionAddress=positionAddress

    }
}