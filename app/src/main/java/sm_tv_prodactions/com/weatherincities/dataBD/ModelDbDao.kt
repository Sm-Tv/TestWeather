package sm_tv_prodactions.com.weatherincities.dataBD

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface ModelDbDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg citi: Citi)

    @Query("SELECT * FROM Cities_table ORDER BY uid DESC")
    fun getAllLiveData(): LiveData<List<Citi>>

    @Delete
    fun delete(citi: Citi)

    @Query("DELETE FROM Cities_table")
    fun deleteAllNotes()
}