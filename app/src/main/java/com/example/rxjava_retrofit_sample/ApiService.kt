package com.example.rxjava_retrofit_sample

import io.reactivex.Flowable
import retrofit2.Response
import retrofit2.http.GET

//https://run.mocky.io/v3/49a76827-05ff-44cd-9a8b-ee8309c095f6

interface ApiService {

    @GET("/v3/49a76827-05ff-44cd-9a8b-ee8309c095f6")
    fun getHomeList(): Flowable<Response<HomeList>>
}

data class HomeList(
    val items: List<HomeModel>
) {
    data class HomeModel(
        val id: Int,
        val title: String,
        val price: Int,
        val lat: Float,
        val lng: Float,
        val imgUrl: String
    )
}