package com.midknight.stormy;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String apiKey = "5642036f00cf739dcb8696c45fb49095";
        double la = 37.8267;
        double lo = -122.423;
        String forecastUrl = "https://api.forecast.io/forecast/"+ apiKey + "/" + la + "," + lo;

        if(isNetworkAvailable()) {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url(forecastUrl).build();
            Call call = client.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    try {
                        Log.v("Main", response.body().string());
                        if (response.isSuccessful()) {
                        } else {
                            NetworkResponseFail();
                        }
                    } catch (IOException e) {
                        Log.e("Main", "Exception caught", e);
                    }
                }
            });
        }
        else{
            Toast.makeText(this,"Network is down!",Toast.LENGTH_SHORT);
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager manager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        boolean isAvailable = false;
        if(networkInfo!=null&&networkInfo.isConnected())
            isAvailable = true;
        return isAvailable;
    }

    private void NetworkResponseFail(){
        AlertDialogFragment dialog = new AlertDialogFragment();
        dialog.show(getFragmentManager(),"error_dialog");
    }

}




