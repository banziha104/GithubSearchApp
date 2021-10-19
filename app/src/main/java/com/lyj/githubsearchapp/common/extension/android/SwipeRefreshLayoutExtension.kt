package com.lyj.githubsearchapp.common.extension.android

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import io.reactivex.rxjava3.core.Observable


fun SwipeRefreshLayout.refreshObserver(): Observable<Unit> =
    Observable.create<Unit> {
        this
            .setOnRefreshListener {
                it.onNext(Unit)
            }
    }
        .doOnDispose { this.setOnRefreshListener(null) }


