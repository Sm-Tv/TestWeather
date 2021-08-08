package sm_tv_prodactions.com.weatherincities.models

data class ModelCities(
    val coord: Coord,
    val name: String,
    val weather: ArrayList<Weather>,
    val main: Main,
    val wind: Wind
)



data class Coord(
    val lon: Double,
    val lat: Double
)

data class Weather(
    val description: String,
    val icon: String
)

data class Main(
    val temp: Double,
    val feels_like: Double
)

data class Wind(
    val speed: Double,
    val deg: Int
)