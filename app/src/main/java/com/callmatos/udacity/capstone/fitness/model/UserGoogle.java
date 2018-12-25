package com.callmatos.udacity.capstone.fitness.model;

import android.os.Parcel;
import android.os.Parcelable;

public class UserGoogle implements Parcelable {

    String username;
    String email;
    String photoURL;

    public UserGoogle(){}

    public UserGoogle(Parcel parcel) {
        username = parcel.readString();
        email = parcel.readString();
        photoURL = parcel.readString();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    public static final Parcelable.Creator<UserGoogle> CREATOR = new Parcelable.Creator<UserGoogle>(){

        @Override
        public UserGoogle createFromParcel(Parcel parcel) {
            return new UserGoogle(parcel);
        }

        @Override
        public UserGoogle[] newArray(int i) {
            return new UserGoogle[i];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(username);
        parcel.writeString(email);
        parcel.writeString(photoURL);
    }

}
