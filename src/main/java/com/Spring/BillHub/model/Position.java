package com.Spring.BillHub.model;

import org.hibernate.annotations.DynamicUpdate;


import javax.persistence.OneToOne;

@DynamicUpdate
public class Position {
    private long latitude;
    private long longitude;


    public Position() {
    }

    public Position(long latitude, long longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public long getLatitude() {
        return latitude;
    }

    public void setLatitude(long latitude) {
        this.latitude = latitude;
    }

    public long getLongitude() {
        return longitude;
    }

    public void setLongitude(long longitude) {
        this.longitude = longitude;
    }
}
