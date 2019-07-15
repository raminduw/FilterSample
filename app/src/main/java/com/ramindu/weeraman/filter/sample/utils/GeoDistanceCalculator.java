package com.ramindu.weeraman.filter.sample.utils;

import android.location.Location;

import com.ramindu.weeraman.filter.sample.data.model.UserItem;

import javax.inject.Inject;

import dagger.Module;

@Module
public class GeoDistanceCalculator {

    @Inject
    public GeoDistanceCalculator() {

    }

    public int calculateDistanceToUser(UserItem loggedUser, UserItem userItem) {

        Location loggedUserLocation = new Location("");
        loggedUserLocation.setLatitude(loggedUser.getCity().getLat());
        loggedUserLocation.setLongitude(loggedUser.getCity().getLon());

        Location userLocation = new Location("");
        userLocation.setLatitude(userItem.getCity().getLat());
        userLocation.setLongitude(userItem.getCity().getLon());

        float distanceInMeters = loggedUserLocation.distanceTo(userLocation);

        return Math.round(distanceInMeters / 1000);
    }

}
