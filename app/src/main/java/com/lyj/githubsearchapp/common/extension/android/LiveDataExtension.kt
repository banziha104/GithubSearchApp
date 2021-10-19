package com.lyj.githubsearchapp.common.extension.android

import androidx.lifecycle.LiveData

/**
 * LiveData는 초기화가 안된 경우를 제외하고는 value가 null이 아님으로 강제 unwrapping
 * 초기화 문제의 경우 개발단계에서 인지하기 쉬움으로 에러 발생
 */
val <T> LiveData<T>.unwrappedValue
       get() = this.value ?: throw LiveDataNotInitializedException()

class LiveDataNotInitializedException(msg : String = "LiveData가 초기화 되지 않았습니다") : RuntimeException(msg)