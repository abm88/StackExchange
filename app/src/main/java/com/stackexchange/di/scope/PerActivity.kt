package com.stackexchange.di.scope

import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import javax.inject.Scope

/**
 * Dagger activity scope. Used in activity modules to indicate that the scope of the injected object
 * is an activity.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
annotation class PerActivity