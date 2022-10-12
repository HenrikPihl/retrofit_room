package io.shortcut.dtucourceretrofit_room.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import io.shortcut.dtucourceretrofit_room.datasource.dao.EmojiDao
import io.shortcut.dtucourceretrofit_room.datasource.model.EmojiEntity
import io.shortcut.dtucourceretrofit_room.datasource.model.EmojiFTSEntity

@Database(
    entities = [EmojiEntity::class, EmojiFTSEntity::class],
    autoMigrations = [
    ],
    exportSchema = true, version = 2)

@TypeConverters(DatabaseTypeConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun emojiDao(): EmojiDao
}