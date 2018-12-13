package com.example.webworks.neostore.descriptionmodel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class DescriptionResponseModel{


    @SerializedName("status")
    @Expose
    var status: Int? = null
    @SerializedName("data")
    @Expose
    var data: DescriptionModel? = null
}