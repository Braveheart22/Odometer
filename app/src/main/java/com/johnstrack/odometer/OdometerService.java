package com.johnstrack.odometer;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.content.ContextCompat;

import java.util.Random;

public class OdometerService extends Service {

    public OdometerService() {
    }

    private final IBinder binder = new OdometerBinder();
    private final Random random = new Random();
    private LocationListener listener;
    private LocationManager locManager;

    public static final String PERMISSION_STRING = Manifest.permission.ACCESS_FINE_LOCATION;

    @Override
    public void onCreate() {
        super.onCreate();
        listener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }
        };

        locManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ContextCompat.checkSelfPermission(this, PERMISSION_STRING) == PackageManager.PERMISSION_GRANTED) {
            String provider = locManager.getBestProvider(new Criteria(), true);
            if (provider != null) {
                locManager.requestLocationUpdates(provider, 1000, 1, listener);
            }
        }
    }

    public class OdometerBinder extends Binder {
        OdometerService getOdometer () {
            return OdometerService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return  binder;
    }

    public double getDistance() {
        return random.nextDouble();
    }
}