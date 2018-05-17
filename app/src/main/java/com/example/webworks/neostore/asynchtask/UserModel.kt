package com.example.webworks.neostore.asynchtask

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable

class UserModel : Serializable {

    @SerializedName("id")
    @Expose
    val id: Int = 0

    @SerializedName("role_id")
    @Expose
    val roleId:Int= 0

    @SerializedName("first_name")
    @Expose
    val  firstName :String?=null

    @SerializedName("last_name")
    @Expose
    val  lastName:String?=null

    @SerializedName("email")
    @Expose
    val  email:String?=null

    @SerializedName("username")
    @Expose
    val  username:String?=null

    @SerializedName("profile_pic")
    @Expose
    val  profilePic:String?=null

    @SerializedName("country_id")
    @Expose
    val  countryId:String?=null

    @SerializedName("gender")
    @Expose
    val  gender:String?=null

    @SerializedName("phone_no")
    @Expose
    val  phoneNo:String?=null

    @SerializedName("dob")
    @Expose
    val dob:String?=null

    @SerializedName("is_active")
    @Expose
    val isActive: Boolean = false

    @SerializedName("created")
    @Expose
    val  created:String?=null

    @SerializedName("modified")
    @Expose
    val  modified:String?=null

    @SerializedName("access_token")
    @Expose
    val  accessToken:String?=null


}

