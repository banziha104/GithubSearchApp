package com.lyj.githubsearchapp.data.source.remote.interceptor

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.widget.Toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response


/***
 * Remote API 요청 시 마다 호출, 현재 네트워크가 가용한지 체크
 * @param context
 * @param onCheckNetworkConnection network가 가용한지 체크 될떄마다 전달되는 inline class 기반 고차 함수
 */
class NetworkConnectionInterceptor(
    private val context: Context,
    private val onCheckNetworkConnection: OnCheckNetworkConnection
) : Interceptor {


    override fun intercept(chain: Interceptor.Chain): Response {

        onCheckNetworkConnection.callBack(isConnected)

        return chain.proceed( chain.request().newBuilder().build())
    }

    private val isConnected: Boolean
        get() {
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                val nw = connectivityManager.activeNetwork ?: return false
                val actNw = connectivityManager.getNetworkCapabilities(nw) ?: return false
                return when {
                    actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                    else -> false
                }
            } else {
                val nwInfo = connectivityManager.activeNetworkInfo ?: return false
                return nwInfo.isConnected
            }
        }

    @JvmInline
    value class OnCheckNetworkConnection(val callBack : (isConnected: Boolean) -> Unit)
}