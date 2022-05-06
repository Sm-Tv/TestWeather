package sm_tv_prodactions.com.weatherincities.dataBD

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Citi::class], version = 9)
abstract class DataBaseCities : RoomDatabase() {
    abstract fun modelDbDao(): ModelDbDao


    companion object {
        @Volatile
        private var INSTANCE: DataBaseCities? = null

        fun getDatabase(context: Context): DataBaseCities {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DataBaseCities::class.java,
                    "user_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }

}