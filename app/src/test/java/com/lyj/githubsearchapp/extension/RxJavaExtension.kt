package com.lyj.githubsearchapp.extension

import io.reactivex.rxjava3.core.*
import java.util.concurrent.TimeUnit


fun <T> Observable<T>.testWithAwait(seconds: Long = 3L) = this
    .test()
    .awaitDone(seconds, TimeUnit.SECONDS)

fun <T> Maybe<T>.testWithAwait(seconds: Long = 3L) = this
    .test()
    .awaitDone(seconds, TimeUnit.SECONDS)


fun <T> Single<T>.testWithAwait(seconds: Long = 3L) = this
    .test()
    .awaitDone(seconds, TimeUnit.SECONDS)


fun <T> Flowable<T>.testWithAwait(seconds: Long = 3L) = this
    .test()
    .awaitDone(seconds, TimeUnit.SECONDS)

fun Completable.testWithAwait(seconds: Long = 3L) = this
    .test()
    .awaitDone(seconds, TimeUnit.SECONDS)
