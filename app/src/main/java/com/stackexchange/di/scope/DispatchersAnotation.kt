package com.stackexchange.di.scope

import javax.inject.Qualifier

@Qualifier
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
annotation class MainDispatcher


@Qualifier
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
annotation class IODispatcher

@Qualifier
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
annotation class DefaultDispatcher

@Qualifier
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
annotation class UnconfinedDispatcher