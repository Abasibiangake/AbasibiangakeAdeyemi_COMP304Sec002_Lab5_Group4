package com.example.abasibiangakeadeyemi_comp304sec002_lab5;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable;

import com.google.firebase.database.Exclude;

public class Laptop implements Parcelable {
    private String laptopbrand;
    private String laptopRAM;
    private String laptopprocessor;
    private String laptopcost;
    private String laptopname;
    private String laptopImage;
    private String laptopkey;

    //create empty constructor
    public Laptop() {
    }

    //create a new constructor with 7parameters
    public Laptop(String laptopbrand, String laptopRAM, String laptopprocessor,
                  String laptopcost, String laptopname, String laptopImage, String laptopkey) {
        this.laptopbrand = laptopbrand;
        this.laptopRAM = laptopRAM;
        this.laptopprocessor = laptopprocessor;
        this.laptopcost = laptopcost;
        this.laptopname = laptopname;
        this.laptopImage = laptopImage;
        this.laptopkey = laptopkey;
    }

    //implement parelable methods
    protected Laptop(Parcel in) {
        laptopbrand = in.readString();
        laptopRAM = in.readString();
        laptopprocessor = in.readString();
        laptopcost = in.readString();
        laptopname = in.readString();
        laptopImage = in.readString();
        laptopkey = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(laptopbrand);
        dest.writeString(laptopRAM);
        dest.writeString(laptopprocessor);
        dest.writeString(laptopcost);
        dest.writeString(laptopname);
        dest.writeString(laptopImage);
        dest.writeString(laptopkey);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Laptop> CREATOR = new Creator<Laptop>() {
        @Override
        public Laptop createFromParcel(Parcel in) {
            return new Laptop(in);
        }

        @Override
        public Laptop[] newArray(int size) {
            return new Laptop[size];
        }
    };

    //generate getters and setters
    public String getLaptopbrand() {
        return laptopbrand;
    }

    public void setLaptopbrand(String laptopbrand) {
        this.laptopbrand = laptopbrand;
    }

    public String getLaptopRAM() {
        return laptopRAM;
    }

    public void setLaptopRAM(String laptopRAM) {
        this.laptopRAM = laptopRAM;
    }

    public String getLaptopprocessor() {
        return laptopprocessor;
    }

    public void setLaptopprocessor(String laptopprocessor) {
        this.laptopprocessor = laptopprocessor;
    }

    public String getLaptopcost() {
        return laptopcost;
    }

    public void setLaptopcost(String laptopcost) {
        this.laptopcost = laptopcost;
    }

    public String getLaptopname() {
        return laptopname;
    }

    public void setLaptopname(String laptopname) {
        this.laptopname = laptopname;
    }

    public String getLaptopImage() {
        return laptopImage;
    }

    public void setLaptopImage(String laptopImage) {
        this.laptopImage = laptopImage;
    }

    public String getLaptopkey() {
        return laptopkey;
    }

    public void setLaptopkey(String laptopkey) {
        this.laptopkey = laptopkey;
    }
}
