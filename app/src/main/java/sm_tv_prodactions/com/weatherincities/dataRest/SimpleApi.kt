package sm_tv_prodactions.com.weatherincities.dataRest

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import sm_tv_prodactions.com.weatherincities.models.ModelCities

interface SimpleApi {

    @GET("./data/2.5/weather")
    suspend fun getAddCiti(
        @Query("q",) citiName: String,
        @Query("appid") keyId: String
    ): Response<ModelCities>

    @GET("./data/2.5/weather")
    suspend fun getAddCitiByCoord(
        @Query("lat",) lat: Double,
        @Query("lon",) lon: Double,
        @Query("appid") keyId: String
    ): Response<ModelCities>

}