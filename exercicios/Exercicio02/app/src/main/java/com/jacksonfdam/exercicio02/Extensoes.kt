package com.jacksonfdam.exercicio02

import android.app.Activity
import android.support.annotation.IdRes
import android.view.View

fun <T : View> Activity.bind(@IdRes idRes: Int): Lazy<T> {
    @Suppress("UNCHECKED_CAST")
    return unsafeLazy { findViewById(idRes) as T }
}

fun <T : View> View.bind(@IdRes idRes: Int): Lazy<T> {
    @Suppress("UNCHECKED_CAST")
    return unsafeLazy { findViewById(idRes) as T }
}

private fun <T> unsafeLazy(initializer: () -> T) = lazy(LazyThreadSafetyMode.NONE, initializer)
/*
FONTE: https://medium.com/@quiro91/improving-findviewbyid-with-kotlin-4cf2f8f779bb

https://www.bignerdranch.com/blog/kotlin-when-to-use-lazy-or-lateinit/
https://antonioleiva.com/kotlin-android-extensions/
*/