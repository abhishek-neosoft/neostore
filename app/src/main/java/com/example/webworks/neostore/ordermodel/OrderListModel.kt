package com.example.webworks.neostore.ordermodel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import retrofit2.http.Field


class OrderListModel {


    @SerializedName("id")
    @Expose
    val id: Int? = null
    @SerializedName("cost")
    @Expose
    val cost: Int? = null
    @SerializedName("created")
    @Expose
    val created: String? = null


}