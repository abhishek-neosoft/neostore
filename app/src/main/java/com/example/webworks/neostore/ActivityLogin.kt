package com.example.webworks.neostore

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*


class ActivityLogin : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btn_login.setOnClickListener(View.OnClickListener {

            startActivity(Intent(this,ActivityDashBoard::class.java))
        })

        txt_new_account.setOnClickListener(View.OnClickListener {

            val id = edt_id.text.toString()

            Toast.makeText(this,id,Toast.LENGTH_LONG).show()

        })
    }
}
