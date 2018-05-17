package com.example.webworks.neostore.addaddress

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.webworks.neostore.R
import kotlinx.android.synthetic.main.add_address_activity.*

class AddAddress : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_address_activity)

        /*tv_back.setOnClickListener { finish() }


        btn_save_address.setOnClickListener {

            startActivity(Intent(this,AddressList::class.java)
                    .putExtra("address",edt_address.text)
                    .putExtra("landmark",edt_landmark.text)
                    .putExtra("city",edt_city.text)
                    .putExtra("state",edt_state.text)
                    .putExtra("zip_code",edt_zip_code.text)
                    .putExtra("",edt_country.text))

        }*/
    }
}
