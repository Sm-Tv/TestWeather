package sm_tv_prodactions.com.weatherincities.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Cities_table")
data class Citi(
    @PrimaryKey(autoGenerate = true)
    val uid: Int,

    @ColumnInfo(name = "name")
    val name: String,

    //@ColumnInfo(name = "temp")
    //val temp: String,

    //@ColumnInfo(name = "icon")
    //val icon: String

)
