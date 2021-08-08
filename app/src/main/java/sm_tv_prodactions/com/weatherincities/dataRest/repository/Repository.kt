package sm_tv_prodactions.com.weatherincities.dataRest.repository

import retrofit2.Response
import sm_tv_prodactions.com.weatherincities.dataRest.RetrofitInstance
import sm_tv_prodactions.com.weatherincities.models.ModelCities

class Repository {
    suspend fun getCitiList(name:String, key: String): Response<ModelCities>{
        return RetrofitInstance.api.getAddCiti(name, key)
    }
    suspend fun getCitiListByCoord(lat:Double, lon:Double, key: String): Response<ModelCities>{
        return RetrofitInstance.api.getAddCitiByCoord(lat, lon, key)
    }
}