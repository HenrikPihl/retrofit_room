package io.shortcut.dtucourceretrofit_room.di

import javax.inject.Qualifier

// https://medium.com/androiddevelopers/create-an-application-coroutinescope-using-hilt-dd444e721528

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class DefaultDispatcher

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class MainDispatcher

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class IoDispatcher

