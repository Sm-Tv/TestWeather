package sm_tv_prodactions.com.weatherincities.dataRest.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import sm_tv_prodactions.com.weatherincities.dataRest.repository.Repository

class MainViewModelFactory (private var repository: Repository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }
}