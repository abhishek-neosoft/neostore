package com.example.webworks.neostore.coroutines

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.example.webworks.neostore.DashBoardActivity
import com.example.webworks.neostore.R
import com.example.webworks.neostore.asynchtask.APIClient
import kotlinx.android.synthetic.main.activity_login_co.*
import kotlinx.coroutines.experimental.launch

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_co)

        val service = APIClient().getRetrofitService()
        btn_login.setOnClickListener {

            launch {
                val request = service.getLogin(edt_id.text.toString(), edt_password.text.toString())
                val response = request.await()
                if (response.isSuccessful) {
                    toast("Sucess")
                    navigator()
                } else {
                    toast("Failed")
                }
            }
        }
    }

    private fun navigator() {
        startActivity(Intent(this, DashBoardActivity::class.java))
    }

    private fun toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}