package com.example.webworks.neostore.addaddress

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import com.example.webworks.neostore.R
import com.example.webworks.neostore.sqldatabase.SqlDatabaseManage
import kotlinx.android.synthetic.main.add_address_activity.*

class AddAddress : AppCompatActivity() {

    var count: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_address_activity)



        tv_back.setOnClickListener { finish() }

        val sp = getSharedPreferences("userinfo", MODE_PRIVATE)

        val accessToken = sp.getString("access_token","")
        Log.e("ACCESS TOKEN",accessToken)
        btn_save_address.setOnClickListener {

            if (isValidated()) {

                val allAddress = "${edt_address.text} ${edt_landmark.text} ${edt_city.text} ${edt_state.text}" +
                        "${edt_zip_code.text} ${edt_country.text}"

                Log.e("ACCESSTOKEN",accessToken.toString())
                //instance of DATABASE
                val sqlDatabaseManage = SqlDatabaseManage(this)


                //DATABASE insert method calling
                val isInserted = sqlDatabaseManage.insertData(
                       accessToken ,allAddress)

                if (isInserted)
                {
                    Toast.makeText(this,"data inserted",Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this,AddressList::class.java))
                }

            }
        }
    }

    fun isValidated(): Boolean {
        if (TextUtils.isEmpty(edt_address.text))
            edt_address.setError("Please enter address")
        else if (TextUtils.isEmpty(edt_landmark.text))
            edt_landmark.error = "Please enter valid landmar"
        else if (TextUtils.isEmpty(edt_city.text))
            edt_city.error = "Please enter city"
        else if (TextUtils.isEmpty(edt_state.text))
            edt_state.error = "Please enter state"
        else if (TextUtils.isEmpty(edt_zip_code.text))
            edt_zip_code.error = "Please enter zipcode"
        else if (TextUtils.isEmpty(edt_country.text))
            edt_country.error = "Please enter country"
        else
            return true
        return false
    }
}
