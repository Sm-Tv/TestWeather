package sm_tv_prodactions.com.weatherincities.dataBD.repository

import androidx.lifecycle.LiveData
import sm_tv_prodactions.com.weatherincities.dataBD.Citi
import sm_tv_prodactions.com.weatherincities.dataBD.ModelDbDao

class RepositoryDb(private val modelDbDao: ModelDbDao) {

    val readAllData: LiveData<List<Citi>> = modelDbDao.getAllLiveData()

    fun addNote(citi: Citi) {
        modelDbDao.insert(citi)
    }


    fun deleteNote(citi: Citi) {
        modelDbDao.delete(citi)
    }

    fun deleteAllNotes() {
        modelDbDao.deleteAllNotes()
    }

}