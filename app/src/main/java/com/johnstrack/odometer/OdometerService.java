package com.johnstrack.odometer;

import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;

import java.util.Random;

public class OdometerService extends Service {

    public OdometerService() {
    }

    private final IBinder binder = new OdometerBinder();
    private final Random random = new Random();
    private LocationListener listener;

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