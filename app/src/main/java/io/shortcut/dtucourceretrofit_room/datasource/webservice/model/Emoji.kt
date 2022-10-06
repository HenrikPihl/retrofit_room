package io.shortcut.dtucourceretrofit_room.datasource.webservice.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EmojiDto(
    val name: String,
    val category: String,
    val group: String,
    val htmlCode: List<String>,
    val unicode: List<String>
)

/*
{
    "name": "cat face with tears of joy",
    "category": "smileys and people",
    "group": "cat face",
    "htmlCode": [
      "&#128569;"
    ],
    "unicode": [
      "U+1F639"
    ]
  }
 */