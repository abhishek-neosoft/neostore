package com.example.webworks.neostore.cartmodel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CartProductResponse{
        @SerializedName("id")
        @Expose
        val id:Int=0
        @SerializedName("name")
        @Expose
        val name:String?=null
        @SerializedName("cost")
        @Expose
        val cost:Int=0
        @SerializedName("product_category")
        @Expose
        val productCategory:String?=null
        @SerializedName("product_images")
        @Expose
        val productImages:String?=null
        @SerializedName("sub_total")
        @Expose
        val subTotal:Int=0
}