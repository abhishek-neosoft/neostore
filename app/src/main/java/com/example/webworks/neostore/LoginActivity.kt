package com.example.webworks.neostore

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.Toast
import com.example.webworks.neostore.asynchtask.APIClient
import com.example.webworks.neostore.asynchtask.APIInterface
import com.example.webworks.neostore.asynchtask.UserResponseModel
import com.example.webworks.neostore.checkinternetconnection.InternetConnection
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    var email: String = ""
    var password: String = ""
    lateinit var apiInterface: APIInterface
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val sp = getSharedPreferences("userinfo", Context.MODE_PRIVATE)
        val shareId = sp.getString("username", "")
        val sharePassword = sp.getString("password", "")
        if (sp.contains("username") && sp.contains("password")) {
            startActivity(Intent(this, DashBoardActivity::class.java))
            finish()
        } else {
            Toast.makeText(this, "no data found", Toast.LENGTH_LONG).show()
        }
        apiInterface = APIClient().getClient().create(APIInterface::class.java)
        if (btn_login.text == "LOGIN") {
            btn_login.setOnClickListener({
                if (InternetConnection.isNetworkAvailable(this)==true) {
                    email = edt_id.text.toString()
                    password = edt_password.text.toString()
                    apiInterface.login(email, password).enqueue(object : Callback<UserResponseModel> {
                        override fun onFailure(call: Call<UserResponseModel>?, t: Throwable?) {
                        }
                        override fun onResponse(call: Call<UserResponseModel>?, response: Response<UserResponseModel>?) {
                            if (response?.code() == 200) {
                                val data = getSharedPreferences("userinfo", Context.MODE_PRIVATE)
                                val editor: SharedPreferences.Editor = data.edit()
                                editor.putString("username", email)
                                editor.putString("password", password)
                                editor.putString("access_token","${response.body().data!!.accessToken}")
                                editor.putString("name", "${response.body().data!!.firstName}" + " ${response.body().data!!.lastName}")
                                editor.putString("email", "${response.body().data!!.email}")
                                editor.putString("image", "${response.body().data!!.profilePic}")
                                editor.commit()
                                var name = "${response.body().data!!.firstName}" + " ${response.body().data!!.lastName}"
                                var emailid: String = "${response.body().data!!.email}"
                                var image: String = "${response.body().data!!.profilePic}"
                                startActivity(Intent(this@LoginActivity, DashBoardActivity::class.java))
                                Toast.makeText(this@LoginActivity, "id " + email + "$password", Toast.LENGTH_LONG).show()
                                finish()

                            } else {
                                Toast.makeText(this@LoginActivity, "Plase enter valid id and password", Toast.LENGTH_LONG).show()
                            }
                        }
                    })
                }
                else
                {
                    Toast.makeText(this@LoginActivity, "Plase enter valid id and password", Toast.LENGTH_LONG).show()
                }
            })
        }
        txt_new_account.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        })
        login_forgot_password.setOnClickListener({
            edt_password.visibility = View.INVISIBLE
            edt_id.hint = "Enter your valid email id"
            btn_login.text = "SUBMIT"
            if (btn_login.text == "SUBMIT") {
                btn_login.setOnClickListener({
                    apiInterface.forgotPassword(email).enqueue(object : Callback<UserResponseModel> {
                        override fun onFailure(call: Call<UserResponseModel>?, t: Throwable?) {
                        }

                        override fun onResponse(call: Call<UserResponseModel>?, response: Response<UserResponseModel>?) {
                            Toast.makeText(this@LoginActivity, "" + response?.code(), Toast.LENGTH_LONG).show()
                            if (response?.code() == 200) {
                                AlertDialog.Builder(this@LoginActivity).setTitle("Password reset request sent ")
                                        .setMessage("check your email id to reset your account").setPositiveButton("Ok", DialogInterface.OnClickListener { dialog, which ->
                                            dialog.dismiss()
                                        }).show().create()
                            }
                        }
                    })
                })
            }
        })
    }
}