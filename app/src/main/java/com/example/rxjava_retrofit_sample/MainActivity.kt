package com.example.rxjava_retrofit_sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val apiService = getApiService()
        val disposable = getSubscribe(apiService)
    }

    private fun getApiService(): ApiService {
        return Retrofit.Builder()
            .baseUrl("https://run.mocky.io")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create()
    }

    private fun getSubscribe(apiService: ApiService): Disposable {
        return apiService.getHomeList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Log.d("테스트1", it.body().toString())
                }, {
                    Log.d("테스트2", it.toString())
                }
            )
    }

    override fun onDestroy() {
        super.onDestroy()

    }

}