package com.mohamedmenasy.backbasetask.core.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Coord implements Parcelable {
    @JsonProperty("lon")
    private Double lon;
    @JsonProperty("lat")
    private Double lat;

    public Coord(Double lon, Double lat) {
        this.lon = lon;
        this.lat = lat;
    }

    @JsonProperty("lon")
    public Double getLon() {
        return lon;
    }

    @JsonProperty("lon")
    public void setLon(Double lon) {
        this.lon = lon;
    }

    @JsonProperty("lat")
    public Double getLat() {
        return lat;
    }

    @JsonProperty("lat")
    public void setLat(Double lat) {
        this.lat = lat;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.lon);
        dest.writeValue(this.lat);
    }

    public Coord() {
    }

    protected Coord(Parcel in) {
        this.lon = (Double) in.readValue(Double.class.getClassLoader());
        this.lat = (Double) in.readValue(Double.class.getClassLoader());
    }

    public static final Parcelable.Creator<Coord> CREATOR = new Parcelable.Creator<Coord>() {
        @Override
        public Coord createFromParcel(Parcel source) {
            return new Coord(source);
        }

        @Override
        public Coord[] newArray(int size) {
            return new Coord[size];
        }
    };
}
