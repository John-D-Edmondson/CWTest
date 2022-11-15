package com.comp2000.cwtest;

import android.os.Parcel;
import android.os.Parcelable;

public class User_info  {
    private String name;
    private String address;
    private String phone_number;
    private String holiday_remaining;


    public User_info(String name, String address, String phone_number, String holiday_remaining){
        this.name = name;
        this.address = address;
        this.phone_number = phone_number;
        this.holiday_remaining = holiday_remaining;
    }

    public String getName() {return name;}
    public String getAddress() {return address;}
    public String getPhone_number() {return phone_number;}
    public String getHoliday_remaining(){return holiday_remaining;}
    // Parcelling part
    public User_info(Parcel in){
        String[] data = new String[3];

        in.readStringArray(data);
        // the order needs to be the same as in writeToParcel() method
        this.name = data[0];
        this.address = data[1];
        this.phone_number = data[2];
    }


    public int describeContents(){
        return 0;
    }


    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[] {this.name,
                this.address,
                this.phone_number});
    }
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public User_info createFromParcel(Parcel in) {
            return new User_info(in);
        }

        public User_info[] newArray(int size) {
            return new User_info[size];
        }
    };

}
