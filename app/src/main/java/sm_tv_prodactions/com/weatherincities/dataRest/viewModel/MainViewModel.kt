package sm_tv_prodactions.com.weatherincities.dataRest.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Response
import sm_tv_prodactions.com.weatherincities.dataRest.repository.Repository
import sm_tv_prodactions.com.weatherincities.models.ModelCities

class MainViewModel() : ViewModel() {

    val myResponseListCities: MutableLiveData<Response<ModelCities>> = MutableLiveData()
    private val repository: Repository = Repository()

    fun getCites(name: String, key: String) {
        viewModelScope.launch {
            val repository = repository.getCitiList(name, key)
            myResponseListCities.value = repository
        }
    }

    fun getCitiesByCoord(lat: Double, lon: Double, key: String) {
        viewModelScope.launch {
            val repository = repository.getCitiListByCoord(lat, lon, key)
            myResponseListCities.value = repository
        }
    }
}