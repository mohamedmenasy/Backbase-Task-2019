package com.mohamedmenasy.backbasetask.core.data;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class City implements Parcelable {
    @JsonProperty("country")
    private String country;
    @JsonProperty("name")
    private String name;
    @JsonProperty("_id")
    private Integer id;
    @JsonProperty("coord")
    private Coord coord;

    @JsonCreator
    public City(@JsonProperty("country") String country, @JsonProperty("name") String name, @JsonProperty("_id") Integer id, @JsonProperty("coord") Coord coord) {
        this.country = country;
        this.name = name;
        this.id = id;
        this.coord = coord;
    }

    @JsonProperty("country")
    public String getCountry() {
        return country;
    }

    @JsonProperty("country")
    public void setCountry(String country) {
        this.country = country;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("_id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("_id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("coord")
    public Coord getCoord() {
        return coord;
    }

    @JsonProperty("coord")
    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.country);
        dest.writeString(this.name);
        dest.writeValue(this.id);
        dest.writeParcelable(this.coord, flags);
    }

    protected City(Parcel in) {
        this.country = in.readString();
        this.name = in.readString();
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.coord = in.readParcelable(Coord.class.getClassLoader());
    }

    public static final Creator<City> CREATOR = new Creator<City>() {
        @Override
        public City createFromParcel(Parcel source) {
            return new City(source);
        }

        @Override
        public City[] newArray(int size) {
            return new City[size];
        }
    };

    @NonNull
    @Override
    public String toString() {
        return "Id : " + getId() + "\n" +
                "County : " + getCountry() + "\n" +
                "City : " + getName() + "\n" +
                "Longitude : " + getCoord().getLon() + "\n" +
                "Latitude : " + getCoord().getLat() + "\n";
    }
}
