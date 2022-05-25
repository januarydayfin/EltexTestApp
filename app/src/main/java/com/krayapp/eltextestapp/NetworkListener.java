package com.krayapp.eltextestapp;

import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;

public class NetworkListener {

    private ConnectivityManager connectivityManager;

    public NetworkListener(ConnectivityManager connectivityManager) {
        this.connectivityManager = connectivityManager;
    }

    public Boolean isOnline() {
        if (connectivityManager != null) {
            NetworkCapabilities capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    return true;
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    return true;
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                    return true;
                }
            }
        }
        return false;
    }
}