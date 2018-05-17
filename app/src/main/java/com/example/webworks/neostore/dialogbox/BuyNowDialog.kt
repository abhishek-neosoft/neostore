package com.example.webworks.neostore.dialogbox

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.app.AppCompatDialogFragment
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import com.bumptech.glide.Glide
import com.example.webworks.neostore.R
import com.example.webworks.neostore.cartmodel.AddToCartModelResponse
import com.example.webworks.neostore.asynchtask.APIClient
import com.example.webworks.neostore.asynchtask.APIInterface
import com.example.webworks.neostore.descriptionmodel.DescriptionResponseModel
import com.example.webworks.neostore.mycart.MyCartActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
@SuppressLint("ValidFragment")
class BuyNowDialog(var positon: Int?, var descriptionResponseModel: DescriptionResponseModel, var productId: Int) : AppCompatDialogFragment() {

    lateinit var apiInterface: APIInterface
    var builder: AlertDialog.Builder? = null
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        apiInterface = APIClient().getClient().create(APIInterface::class.java)
        val inflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.buy_now_dialogbox, null)
        val buyNowTitle = view.findViewById<TextView>(R.id.buy_now_title)
        val buyNowImage = view.findViewById<ImageView>(R.id.buy_now_image)
        val buyNowQty = view.findViewById<EditText>(R.id.buy_now_qty)
        val buyButton = view.findViewById<Button>(R.id.buy_now_button)
        buyNowTitle.text = descriptionResponseModel.data!!.name
        val sp = context!!.getSharedPreferences("userinfo", AppCompatActivity.MODE_PRIVATE)
        val accessToken = sp.getString("access_token","")
        if (positon == null) {
            Glide.with(context).load(descriptionResponseModel.data!!.productImage!![0].image).into(buyNowImage)
        } else {
            Glide.with(context).load(descriptionResponseModel.data!!.productImage!![positon!!].image).into(buyNowImage)
        }
        buyButton.setOnClickListener {
            val qty = Integer.parseInt(buyNowQty.text.toString())
            if (qty > 0 && qty < 9) {

            apiInterface.addToCart(accessToken, productId, qty).enqueue(object : Callback<AddToCartModelResponse> {
                override fun onFailure(call: Call<AddToCartModelResponse>?, t: Throwable?) {
                }
                override fun onResponse(call: Call<AddToCartModelResponse>?, response: Response<AddToCartModelResponse>?) {

                    if (response!!.code()==200)
                    {
                        Toast.makeText(context,"${response.body().userMsg}",Toast.LENGTH_LONG).show()
                    }
                    else
                    {
                        Toast.makeText(context,"Not Added to Your Cart",Toast.LENGTH_LONG).show()
                    }
                    startActivity(Intent(context, MyCartActivity()::class.java))
                    dismiss()
                }
            })
        }
            else
            {
                Toast.makeText(context,"Out of Limit.. Quantity must be 1 to 8",Toast.LENGTH_LONG).show()
            }
        }
        builder = AlertDialog.Builder(context)
        builder!!.setView(view)
        return builder!!.create()
    }
}