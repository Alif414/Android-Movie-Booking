package com.example.moviebooking;

import android.os.Parcel;
import android.os.Parcelable;

public class Booking implements Parcelable {
    private String movie;

    public String getMovie() {
        return movie;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getTicket() {
        return ticket;
    }

    private String date;
    private String time;
    private String ticket;

    public int getImage() {
        return image;
    }

    private int image;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(movie);
        dest.writeString(date);
        dest.writeString(time);
        dest.writeString(ticket);
        dest.writeInt(image);
    }

    public Booking (String movie, String date, String time, String ticket, int image){
        this.movie = movie;
        this.date = date;
        this.time = time;
        this.ticket = ticket;
        this.image = image;
    }

    //Making the items parcelable
    public Booking(Parcel parcel){
        movie = parcel.readString();
        date = parcel.readString();
        time = parcel.readString();
        ticket = parcel.readString();
        image = parcel.readInt();
    }

    public static final Parcelable.Creator<Booking> CREATOR = new Parcelable.Creator<Booking>() {

        @Override
        public Booking createFromParcel(Parcel parcel) {
            return new Booking(parcel);
        }

        @Override
        public Booking[] newArray(int size) {
            return new Booking[0];
        }
    };
}
