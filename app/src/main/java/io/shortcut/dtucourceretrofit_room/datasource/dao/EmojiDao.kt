package io.shortcut.dtucourceretrofit_room.datasource.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.shortcut.dtucourceretrofit_room.datasource.model.EmojiEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface EmojiDao {
    @Insert
    suspend fun insert(emoji: EmojiEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(emojis: List<EmojiEntity>)

    @Delete
    suspend fun delete(emoji: EmojiEntity)

    @Query("SELECT * FROM emoji")
    fun getEmojis(): Flow<List<EmojiEntity>>
}