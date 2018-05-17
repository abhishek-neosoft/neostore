package com.example.webworks.neostore.descriptionmodel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class DescriptionResponseImages{

    @SerializedName("id")
    @Expose
    val id: Int? = null
    @SerializedName("product_id")
    @Expose
    val productId: Int? = null
    @SerializedName("image")
    @Expose
    val image: String? = null
    @SerializedName("created")
    @Expose
    val created: String? = null
    @SerializedName("modified")
    @Expose
    val modified: String? = null
}