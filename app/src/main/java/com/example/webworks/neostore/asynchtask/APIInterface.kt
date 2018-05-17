package com.example.webworks.neostore.asynchtask

import com.example.webworks.neostore.cartmodel.AddToCartModelResponse
import com.example.webworks.neostore.cartmodel.DeleteCartModel
import com.example.webworks.neostore.cartmodel.ListCartModelResponse
import com.example.webworks.neostore.descriptionmodel.DescriptionResponseModel
import com.example.webworks.neostore.productmodel.ProductResponseModel
import retrofit2.Call
import retrofit2.http.*

interface APIInterface {

    @FormUrlEncoded
    @POST("users/login")
    fun login(@Field("email") email: String, @Field("password") password: String): Call<UserResponseModel>

    @FormUrlEncoded
    @POST("users/register")
    fun register(@Field("first_name") firstName: String, @Field("last_name") lastName: String,
                 @Field("email") email: String, @Field("password") password: String,
                 @Field("confirm_password") confirmPassword: String, @Field("gender") gender: String,
                 @Field("phone_no") phoneNumber: String): Call<UserResponseModel>

    @FormUrlEncoded
    @POST("users/forgot")
    fun forgotPassword(@Field("email") email: String): Call<UserResponseModel>

    @GET("products/getList")
    fun getProduct(@Query("product_category_id") productCategoryId: Int): Call<ProductResponseModel>

    @GET("products/getDetail")
    fun getDetails(@Query("product_id") productId: Int): Call<DescriptionResponseModel>

    @FormUrlEncoded
    @POST("addToCart")
    fun addToCart(@Header("access_token") accessToken: String, @Field("product_id") productId: Int, @Field("quantity") Quantity: Int): Call<AddToCartModelResponse>

    @GET("cart")
    fun listCart(@Header("access_token") accessToken: String): Call<ListCartModelResponse>

    @FormUrlEncoded
    @POST("deleteCart")
    fun postDeleteItem(@Header("access_token") eaccessToken: String, @Field("product_id") productId: Int): Call<DeleteCartModel>

    @FormUrlEncoded
    @POST("order")
    fun saveAddress()
}