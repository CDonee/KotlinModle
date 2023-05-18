package com.dj.kotlinm.api

import com.dj.kotlinm.entity.LoginResponse
import com.dj.kotlinm.entity.LoginResponseWrapper
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


/**
 * @author djc
 *
 * @time 2023/5/18/018  23:03
 *
 * @desc the API
 *
 **/
interface DJWanAndroidApi {

    @POST("/user/login")
    @FormUrlEncoded
    fun login(@Field("username") username:String,@Field("password") password:String):Observable<LoginResponseWrapper<LoginResponse>>
}