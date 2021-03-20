package by.andrei.firstproject.homework_9.data.networkCalls

import by.andrei.firstproject.homework_9.data.WeatherDataFromAPIImpl
import by.andrei.firstproject.homework_9.data.WeatherFromAPI
import by.andrei.firstproject.homework_9.data.networkCalls.APIController.RetrofitHolder.retrofit
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class APIController : WeatherDataFromAPIImpl{
    override fun getWeather(country: String, units: String, appKey: String): Single<WeatherFromAPI> =
            retrofit.create(WeatherAPI::class.java).getCountryWeather(country, units, appKey)
                    .subscribeOn(Schedulers.io())

    private object RetrofitHolder {
        val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }
}