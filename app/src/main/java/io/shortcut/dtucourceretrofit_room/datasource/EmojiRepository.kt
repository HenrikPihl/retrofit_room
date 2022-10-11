package io.shortcut.dtucourceretrofit_room.datasource

import android.text.Html
import io.shortcut.dtucourceretrofit_room.datasource.webservice.EmojiApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EmojiRepository @Inject internal constructor(private val emojiApi: EmojiApi) {

    fun getEmojis(): Flow<List<Emoji>> = flow {
        emit(
            try {
                emojiApi.getAllEmojis().map {
                    val emojiFromHtml = Html.fromHtml(it.htmlCode.first(), Html.FROM_HTML_MODE_LEGACY)

                    Emoji(name = it.name, emoji = emojiFromHtml.toString(), it.category, it.group)
                }
            } catch (e: Exception) {
                Timber.e(e) // TODO inform user via Error Ui
                emptyList()
            }
        )
    }

}

data class Emoji(val name: String, val emoji: String, val category: String, val group: String)

