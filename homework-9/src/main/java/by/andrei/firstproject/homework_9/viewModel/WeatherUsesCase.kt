package by.andrei.firstproject.homework_9.viewModel

import by.andrei.firstproject.homework_9.data.WeatherFromAPI

class WeatherUsesCase {
    fun filterTemp(weatherData: WeatherFromAPI): Boolean = weatherData.list[0].main.temp < 50.0
}