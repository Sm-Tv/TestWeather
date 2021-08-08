package sm_tv_prodactions.com.weatherincities.dataRest.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Response
import sm_tv_prodactions.com.weatherincities.dataRest.repository.Repository
import sm_tv_prodactions.com.weatherincities.models.ModelCities
import java.security.Key

class MainViewModel(private val repository: Repository): ViewModel() {

    val myResponseListCities: MutableLiveData<Response<ModelCities>> = MutableLiveData()

    fun getListDataCities(name: String,key:String){
        viewModelScope.launch {
            val repository = repository.getCitiList(name,key)
            myResponseListCities.value = repository
        }
    }

    fun getListDataCitiesByCoord(lat: Double,lon: Double, key:String){
        viewModelScope.launch {
            val repository = repository.getCitiListByCoord(lat,lon,key)
            myResponseListCities.value = repository
        }
    }
}