package com.lyj.githubsearchapp.common.extension.android

import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView
import com.google.android.material.tabs.TabLayout
import io.reactivex.rxjava3.core.Observable

fun EditText.searchButtonActionObserver(): Observable<Unit> =
    Observable.create<Unit> { emiiter ->
        setOnEditorActionListener { textView, actionId, keyEvent ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                emiiter.onNext(Unit)
                true
            } else {
                false
            }
        }
    }.doOnDispose { setOnEditorActionListener(null) }

