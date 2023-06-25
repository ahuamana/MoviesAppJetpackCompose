package com.ahuaman.moviesapp.framework.network.internetconnection

import com.ahuaman.moviesapp.domain.NoConnectivityException
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class ConnectivityInterceptor @Inject constructor(
    @ApplicationContext private val context: android.content.Context
): Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        if(!isOnline()){
            throw NoConnectivityException("No hay conexión a internet")
        }
        return chain.proceed(chain.request())
    }

    private fun isOnline(): Boolean {
        val connectivityManager = context.getSystemService(android.content.Context.CONNECTIVITY_SERVICE) as android.net.ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }

}