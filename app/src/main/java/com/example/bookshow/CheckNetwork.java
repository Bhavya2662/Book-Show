package com.example.bookshow;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkRequest;
import android.os.Build;

import androidx.annotation.RequiresApi;

public class CheckNetwork {
    private final Context context;

    public CheckNetwork(Context context) {
        this.context = context;
    }

    // Network Check

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void registerNetworkCallback()
    {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkRequest.Builder builder = new NetworkRequest.Builder();

            connectivityManager.registerDefaultNetworkCallback(new ConnectivityManager.NetworkCallback(){
                                                                   @Override
                                                                   public void onAvailable(Network network) {
                                                                       Variables.isNetworkConnected = true; // Global Static Variable
                                                                   }
                                                                   @Override
                                                                   public void onLost(Network network) {
                                                                       Variables.isNetworkConnected = false; // Global Static Variable
                                                                   }
                                                               }

            );
            Variables.isNetworkConnected = false;
        }catch (Exception e){
            Variables.isNetworkConnected = false;
        }
    }

}
