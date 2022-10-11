package io.shortcut.dtucourceretrofit_room.datasource.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "emoji")
data class EmojiEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Long,
    val name: String,
    val category: String,
    val group: String,
    val emojis: List<String>
)