package io.shortcut.dtucourceretrofit_room.di

import android.content.Context
import androidx.room.Room
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.shortcut.dtucourceretrofit_room.datasource.AppDatabase
import io.shortcut.dtucourceretrofit_room.datasource.DatabaseTypeConverters
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context,
        moshi: Moshi
    ): AppDatabase {
        DatabaseTypeConverters.initialize(moshi)
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "emojo.db"
        )
            .build()
    }

    @Singleton
    @Provides
    fun provideEmojiDao(database: AppDatabase) = database.emojiDao()
}