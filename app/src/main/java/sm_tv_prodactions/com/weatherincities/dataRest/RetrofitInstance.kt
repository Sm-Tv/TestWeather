package sm_tv_prodactions.com.weatherincities.dataRest

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import sm_tv_prodactions.com.weatherincities.utils.Constants.BASE_URL

object RetrofitInstance {

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val api: SimpleApi by lazy {
        retrofit.create(SimpleApi::class.java)
    }
}