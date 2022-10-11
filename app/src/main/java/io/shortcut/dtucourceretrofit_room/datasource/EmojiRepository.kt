package io.shortcut.dtucourceretrofit_room.datasource

import android.text.Html
import io.shortcut.dtucourceretrofit_room.datasource.dao.EmojiDao
import io.shortcut.dtucourceretrofit_room.datasource.model.EmojiEntity
import io.shortcut.dtucourceretrofit_room.datasource.webservice.EmojiApi
import io.shortcut.dtucourceretrofit_room.di.ApplicationIoScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EmojiRepository @Inject internal constructor(
    private val emojiApi: EmojiApi,
    private val emojiDao: EmojiDao,
    @ApplicationIoScope private val applicationIoScope: CoroutineScope
) {

    init {
        applicationIoScope.launch {
            val emojiEntities = emojiApi.getAllEmojis().mapIndexed { index, item ->

                val emojisFromHtml = item.htmlCode.map {
                    Html.fromHtml(it, Html.FROM_HTML_MODE_LEGACY).toString()

                }
                EmojiEntity(
                    index.toLong(),
                    item.name,
                    category = item.category,
                    group = item.group,
                    emojisFromHtml
                )
            }
            emojiDao.insert(emojiEntities)
        }

    }

    fun getEmojis(): Flow<List<Emoji>> = flow {
        emit(
            try {
                emojiApi.getAllEmojis().map {
                    val emojiFromHtml =
                        Html.fromHtml(it.htmlCode.first(), Html.FROM_HTML_MODE_LEGACY)

                    Emoji(name = it.name, emoji = emojiFromHtml.toString(), it.category, it.group)
                }
            } catch (e: Exception) {
                Timber.e(e) // TODO inform user via Error Ui
                emptyList()
            }
        )
    }

    fun getEmojisFromDb(): Flow<List<Emoji>> = emojiDao.getEmojis().map { list ->
        list.map {
            Emoji(it.name, it.emojis.first(), it.category, it.group)
        }
    }

}

data class Emoji(val name: String, val emoji: String, val category: String, val group: String)

