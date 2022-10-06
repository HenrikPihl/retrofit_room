package io.shortcut.dtucourceretrofit_room

import android.os.Bundle
import android.text.Html
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import io.shortcut.dtucourceretrofit_room.datasource.webservice.EmojiApi
import io.shortcut.dtucourceretrofit_room.ui.theme.DTUCourceRetrofitRoomTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var emojiApi: EmojiApi

    val emojisState = MutableStateFlow<List<Emoji>>(emptyList())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {

            val emojiList = emojiApi.getAllEmojis()

            emojisState.emit(emojiList.map {
                val emojiFromHtml = Html.fromHtml(it.htmlCode.first(), Html.FROM_HTML_MODE_LEGACY)

                Emoji(name = it.name, emoji = emojiFromHtml.toString(), it.category, it.group)
            })
        }

        setContent {
            DTUCourceRetrofitRoomTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val emojis by emojisState.collectAsState()
                    LazyColumn(
                        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(emojis) { emoji ->
                            EmojiCard(emoji)
                        }
                    }
                }
            }
        }
    }
}

data class Emoji(val name: String, val emoji: String, val category: String, val group: String)

@Composable
fun EmojiCard(emoji: Emoji) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column {
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = emoji.category,
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.5f)
                )
                Text(
                    text = emoji.group,
                    textAlign = TextAlign.End,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.5f)
                )

            }
            Text(
                text = emoji.name,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Text(
                text = emoji.emoji,
                fontSize = 40.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

        }

    }

}

@Preview
@Composable
fun EmojiCardPreview() {
    EmojiCard(
        emoji = Emoji(
            "passport control",
            Html.fromHtml("&#128706;", Html.FROM_HTML_MODE_LEGACY).toString(),
            "symbols",
            "symbols",
        )
    )
}
