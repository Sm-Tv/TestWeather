package sm_tv_prodactions.com.weatherincities.dataBD.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import sm_tv_prodactions.com.weatherincities.dataBD.DataBaseCities
import sm_tv_prodactions.com.weatherincities.dataBD.repository.RepositoryDb
import sm_tv_prodactions.com.weatherincities.dataBD.Citi

class ViewModelDb(application: Application) : AndroidViewModel(application) {

    val readAllData: LiveData<List<Citi>>
    private val repository: RepositoryDb

    init {
        val modelDbDao = DataBaseCities.getDatabase(application).modelDbDao()
        repository = RepositoryDb(modelDbDao)
        readAllData = repository.readAllData
    }

    fun addCiti(citi: Citi) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addNote(citi)
        }
    }

    fun deleteNote(citi: Citi) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteNote(citi)
        }
    }

    fun deleteAllNotes() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllNotes()
        }
    }
}
