package by.andrei.firstproject.homework_9.data

import by.andrei.firstproject.homework_9.data.networkCalls.APIController
import io.reactivex.Single

class WeatherRepository {
    private val newsDataSource: WeatherDataFromAPIImpl = APIController()

    fun getWeather(country: String): Single<WeatherFromAPI> =
            newsDataSource.getWeather(country, "metric", "235c69b35023827c6ac676515cbc005a")
}