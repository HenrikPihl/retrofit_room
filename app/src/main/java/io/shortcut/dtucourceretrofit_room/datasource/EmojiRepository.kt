package io.shortcut.dtucourceretrofit_room.datasource

import io.shortcut.dtucourceretrofit_room.datasource.webservice.EmojiApi
import io.shortcut.dtucourceretrofit_room.datasource.webservice.model.EmojiDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EmojiRepository @Inject internal constructor(private val emojiApi: EmojiApi) {

    fun getEmojis(): Flow<List<EmojiDto>> = flow {
        emit(
            try {
                emojiApi.getAllEmojis()
            } catch (e: Exception) {
                Timber.e(e) // TODO inform user via Error Ui
                emptyList()
            }
        )
    }

}