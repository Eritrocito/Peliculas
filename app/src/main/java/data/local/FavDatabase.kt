package data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import data.model.FavEntity
import data.model.MovieEntity

@Database(entities = [FavEntity::class],version=1)
abstract class FavDatabase: RoomDatabase() {

    abstract fun favDao():FavDao

    companion object{
        private var INSTANCE: FavDatabase? = null

        fun getDatabase(context: Context): FavDatabase{
            INSTANCE=INSTANCE ?: Room.databaseBuilder(
                context.applicationContext,
                FavDatabase::class.java,
                "Fav_table"
            ).build()
            return INSTANCE!! //El !! es para manejar la nulabilidad
        }
        fun destroyInstance(){
            INSTANCE=null
        }
    }


}