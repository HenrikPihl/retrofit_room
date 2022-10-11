package io.shortcut.dtucourceretrofit_room.datasource

import androidx.room.TypeConverter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

object DatabaseTypeConverters {
    private lateinit var moshi: Moshi

    fun initialize(moshi: Moshi){
        this.moshi = moshi
    }
    private val adapter by lazy {
        val listData = Types.newParameterizedType(List::class.java, String::class.java)
        return@lazy moshi.adapter<List<String>>(listData)
    }

    @TypeConverter
    fun toJson(coordinates: List<String>) : String {
        val json = adapter.toJson(coordinates)
        return json
    }

    @TypeConverter
    fun formJson(json: String) : List<String>? {
        return adapter.fromJson(json)
    }

}