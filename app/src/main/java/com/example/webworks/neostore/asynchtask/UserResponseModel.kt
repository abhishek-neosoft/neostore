package com.example.webworks.neostore.asynchtask

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class UserResponseModel : Serializable{

    @SerializedName("status")
    @Expose
    val status: Int = 0

    @SerializedName("data")
    @Expose
    var data: UserModel? = null

    @SerializedName("message")
    @Expose
    val message: String? = null

    @SerializedName("user_msg")
    @Expose
    val userMsg: String? = null
}

