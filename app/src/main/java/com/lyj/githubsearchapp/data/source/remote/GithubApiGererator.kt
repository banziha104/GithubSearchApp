package com.lyj.githubsearchapp.data.source.remote

import okhttp3.OkHttpClient
import retrofit2.CallAdapter
import retrofit2.Converter

/**
 * Github 관련 API 서비스를 생성하는 구현 객체
 */
class GithubApiGenerator(
    private val client: OkHttpClient,
    private val callAdapter: CallAdapter.Factory,
    private val converter: Converter.Factory
) : ServiceGenerator {

    /**
     * Github 에서 사용 할 상수
     * @property HEADER_MAP HeaderInterceptor 에서 사용
     * @property BASE_URL Github Api의 Base URL
     */
    companion object {
        val HEADER_MAP: Map<String, String> = mapOf(
            "Accept" to "application/vnd.github.v3+json"
        )
        const val BASE_URL = "https://api.github.com"
    }

    override fun <T> generateService(
        service: Class<T>,
    ): T = TODO()
}

/**
 * Github 관련 API 서비스를 생성하는 추상 객체
 */
interface ServiceGenerator {
    fun <T> generateService(service: Class<T>): T
}
