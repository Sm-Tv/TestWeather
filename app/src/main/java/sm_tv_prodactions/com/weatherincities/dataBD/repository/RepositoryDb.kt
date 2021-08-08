package sm_tv_prodactions.com.weatherincities.dataBD.repository

import androidx.lifecycle.LiveData
import sm_tv_prodactions.com.weatherincities.dataBD.ModelDbDao
import sm_tv_prodactions.com.weatherincities.models.Citi

class RepositoryDb(private val modelDbDao: ModelDbDao) {

    val readAllData: LiveData<List<Citi>> = modelDbDao.getAllLiveData()
    //val readAllDataCompleted: LiveData<List<Note>> = noteDao.getAllLiveDataCompleted()

    suspend fun addNote(citi: Citi){
        modelDbDao.insert(citi)
    }


    suspend fun deleteNote(citi:Citi){
        modelDbDao.delete(citi)
    }

    suspend fun deleteAllNotes(){
        modelDbDao.deleteAllNotes()
    }

}