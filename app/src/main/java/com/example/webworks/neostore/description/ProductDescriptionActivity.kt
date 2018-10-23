package com.example.webworks.neostore.description

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.webworks.neostore.R
import com.example.webworks.neostore.asynchtask.APIClient
import com.example.webworks.neostore.asynchtask.APIInterface
import com.example.webworks.neostore.descriptionmodel.DescriptionResponseImages
import com.example.webworks.neostore.descriptionmodel.DescriptionResponseModel
import com.example.webworks.neostore.dialogbox.BuyNowDialog
import com.example.webworks.neostore.dialogbox.RateDialog
import kotlinx.android.synthetic.main.activity_prduct_description.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductDescriptionActivity : AppCompatActivity(), ProductAdapterActivity.CallbackAdapterPosition {

    lateinit var descriptionResponseModel: DescriptionResponseModel

    var positon: Int = 0
    override fun onClickPosition(position: Int) {
        this.positon = position
        Glide.with(this).load(descriptionResponseModel.data!!.productImage!![position].image).into(img_product_image)
    }

    lateinit var apiInterface: APIInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prduct_description)

        setSupportActionBar(desp_toolbar)

        tv_back.setOnClickListener(View.OnClickListener {
            this.finish()
        })
        var imageData:List<DescriptionResponseImages>?= null
        val productId = intent.getIntExtra("product_id", 0)
        val catagory = intent.getStringExtra("catagoryName")
        apiInterface = APIClient().getClient().create(APIInterface::class.java)
        apiInterface.getDetails(productId).enqueue(object : Callback<DescriptionResponseModel> {

            override fun onFailure(call: Call<DescriptionResponseModel>?, t: Throwable?) {
                Toast.makeText(this@ProductDescriptionActivity, "failed", Toast.LENGTH_LONG).show()
            }
            override fun onResponse(call: Call<DescriptionResponseModel>?, response: Response<DescriptionResponseModel>?) {

                Glide.with(this@ProductDescriptionActivity).load(response!!.body()!!.data!!.productImage!![0].image).into(img_product_image)
                toolbar_txt.text = response.body()!!.data!!.name
                txt_title.text = response.body()!!.data!!.name
                txt_decp.text = "Catagoty $catagory"
                txt_product_name.text = response.body()!!.data!!.producer
                txt_description.text = response.body()!!.data!!.description

                descriptionResponseModel = response.body()!!

                txt_cost.text = "RS. ${response.body()!!.data!!.cost}"
                imageData= response.body()!!.data!!.productImage

                recycler_view.layoutManager = LinearLayoutManager(this@ProductDescriptionActivity, LinearLayout.HORIZONTAL, false)
                recycler_view.adapter = ProductAdapterActivity(this@ProductDescriptionActivity, imageData!!)
            }
        })

        btn_rate.setOnClickListener {
            val dialog: RateDialog = RateDialog(positon,descriptionResponseModel)
            dialog.isCancelable = true
            dialog.show(supportFragmentManager, "dialog")
        }

        btn_buynow.setOnClickListener {

            val dialog=BuyNowDialog(positon,descriptionResponseModel,productId)
            dialog.isCancelable = true
            dialog.show(supportFragmentManager,"dialog")
        }
    }
}