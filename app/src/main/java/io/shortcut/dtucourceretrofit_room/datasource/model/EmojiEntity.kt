package io.shortcut.dtucourceretrofit_room.datasource.model

import androidx.room.Entity
import androidx.room.Fts4
import androidx.room.FtsOptions
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

@Entity(tableName = "emoji_fts")
@Fts4(contentEntity = EmojiEntity::class, notIndexed = ["id"], tokenizer = FtsOptions.TOKENIZER_ICU, tokenizerArgs = ["da_DK"])
data class EmojiFTSEntity(
    val id: Long,
    val name: String
)
