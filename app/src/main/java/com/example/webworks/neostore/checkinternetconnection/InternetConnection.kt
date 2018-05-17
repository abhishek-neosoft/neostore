package com.example.webworks.neostore.checkinternetconnection

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

class InternetConnection {


    companion object {


        fun isNetworkAvailable(context: Context): Boolean {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            if (cm != null) {
                val info = cm.activeNetworkInfo

                if (info != null) {
                    if (info.state == NetworkInfo.State.CONNECTED) {
                        return true
                    }
                }
            }
            return false
        }
    }

}