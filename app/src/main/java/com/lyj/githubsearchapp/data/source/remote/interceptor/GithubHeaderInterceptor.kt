package com.lyj.githubsearchapp.data.source.remote.interceptor

import okhttp3.Interceptor
import okhttp3.Response

/**
 * Github Api 요청시 파라미터로 정의 해둔 Header를 추가하여 전송
 * @param headerMap 요청시 변경할 Header 명세
 */
class GithubHeaderInterceptor(
    private val headerMap : Map<String,String>
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response =
            chain.request().newBuilder().run {
                headerMap.forEach { headerParam ->
                    header(headerParam.key,headerParam.value)
                }
                chain.proceed(build())
            }

}