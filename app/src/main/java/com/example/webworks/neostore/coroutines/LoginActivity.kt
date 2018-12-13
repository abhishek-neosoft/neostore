package com.example.webworks.neostore.coroutines

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.example.webworks.neostore.DashBoardActivity
import com.example.webworks.neostore.R
import com.example.webworks.neostore.asynchtask.APIClient
import com.example.webworks.neostore.databinding.ActivityLoginCoBinding
import kotlinx.android.synthetic.main.activity_login_co.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch

class LoginActivity : AppCompatActivity(),View.OnClickListener {

    override fun onClick(v: View?) {
        toast("clicked")
    }

    /* fun performButtonClick(view:View) {
         toast("Clicked")
     }*/

    lateinit var mBinding: ActivityLoginCoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_login_co)

        mBinding.btnLogin.setOnClickListener(this)

        val service = APIClient().getRetrofitService()
        btn_login.setOnClickListener {

            launch(UI) {
        try {
                    val request = service.getLogin(edt_id.text.toString(), edt_password.text.toString())
                    val response = request.await()
                    if (response.isSuccessful) {
                        toast("Login Sucess")
                        navigate()
                    } else toast("Failed")
                } catch (ex: Exception) {
                    toast(ex.message.toString())
                }
            }
    }

    }

    fun navigate() {
        startActivity(Intent(this, DashBoardActivity::class.java))
    }

    private fun toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}