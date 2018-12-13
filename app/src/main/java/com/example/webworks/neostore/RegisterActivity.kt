package com.example.webworks.neostore

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.example.webworks.neostore.R.id.reg_radio_group
import com.example.webworks.neostore.asynchtask.APIClient
import com.example.webworks.neostore.asynchtask.APIInterface
import com.example.webworks.neostore.asynchtask.UserResponseModel
import com.example.webworks.neostore.checkinternetconnection.InternetConnection
import kotlinx.android.synthetic.main.activity_register.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {

    var genderSelected = "male"

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        var apiInterface: APIInterface
        apiInterface = APIClient().getClient().create(APIInterface::class.java)

        reg_radio_group.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                reg_male.id -> genderSelected = "male"
                reg_female.id -> genderSelected = "female"
            }
            Toast.makeText(applicationContext, " On checked change : $genderSelected",
                    Toast.LENGTH_SHORT).show()
        }

        reg_button.setOnClickListener {

            if (InternetConnection.isNetworkAvailable(this) == true) {

                apiInterface.register(edt_reg_first_name.text.toString(), edt_reg_last_name.text.toString(),
                        edt_reg_email.text.toString(), edt_reg_password.text.toString(), edt_reg_conf_password.text.toString(),
                        genderSelected, edt_reg_phone.text.toString()).enqueue(object : Callback<UserResponseModel> {

                    override fun onFailure(call: Call<UserResponseModel>?, t: Throwable?) {

                    }

                    override fun onResponse(call: Call<UserResponseModel>?, response: Response<UserResponseModel>?) {
                        Toast.makeText(this@RegisterActivity, "${response?.code()}", Toast.LENGTH_LONG).show()
                        if (response?.code() == 404) {

                            Toast.makeText(this@RegisterActivity, "Alredy register", Toast.LENGTH_LONG).show()
                        }
                        if (response?.code() == 200) {
                            Toast.makeText(this@RegisterActivity, "register sucessfull", Toast.LENGTH_LONG).show()
                        }
                    }
                })
            } else {
                Toast.makeText(this, "Please Check Your INternet Connection", Toast.LENGTH_LONG).show()
                startActivity(Intent(Settings.ACTION_WIFI_SETTINGS))
            }
        }
    }
}
