package io.shortcut.dtucourceretrofit_room.datasource.webservice

import io.shortcut.dtucourceretrofit_room.datasource.webservice.model.EmojiDto
import retrofit2.http.GET

interface EmojiApi {

    @GET("api/all")
    suspend fun getAllEmojis(): List<EmojiDto>

    @GET("api/random")
    suspend fun getRandomEmoji(): EmojiDto

}