package com.example.webworks.neostore.productmodel

import com.example.webworks.neostore.asynchtask.UserModel
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ProductResponseModel {

    @SerializedName("status")
    @Expose
    val status: Int = 0

    @SerializedName("data")
    @Expose
    var data : List<ProductModel>? = null


}