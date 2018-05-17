package com.example.webworks.neostore.descriptionmodel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

 class DescriptionModel {

    @SerializedName("id")
    @Expose
    val  id:Int = 0

    @SerializedName("product_category_id")
    @Expose
    val productCategoryId:Int=0

    @SerializedName("name")
    @Expose
    val name:String?=null

    @SerializedName("producer")
    @Expose
    val producer: String?=null

    @SerializedName("description")
    @Expose
    val description:String?=null

    @SerializedName("cost")
    @Expose
    val cost:Int=0

    @SerializedName("rating")
    @Expose
    val  rating:Int=0

    @SerializedName("view_count")
    @Expose
    val viewCount:Int=0

    @SerializedName("created")
    @Expose
    val created:String?=null

    @SerializedName("modified")
    @Expose
    val modified:String?=null

    @SerializedName("product_images")
    @Expose
    var productImage: List<DescriptionResponseImages>? = null
}