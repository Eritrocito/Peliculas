package data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import data.model.FavEntity
import data.model.MovieEntity

@Dao
interface FavDao {
    @Insert(onConflict= OnConflictStrategy.REPLACE)
    suspend fun saveFavId(favId: FavEntity)

    //@Query("SELECT * FROM faventity")
    //suspend fun getFavId():FavEntity

}