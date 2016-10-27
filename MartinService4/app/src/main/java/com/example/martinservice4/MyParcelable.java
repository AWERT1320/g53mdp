package com.example.martinservice4;

import android.os.Parcel;
import android.os.Parcelable;

public class MyParcelable implements Parcelable
{
    int x;
    int y;
    String name;

    public MyParcelable()
    {

    }

    public MyParcelable(Parcel in)
    {
        readFromParcel(in);
    }

    @Override
    public int describeContents()
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags)
    {
        out.writeInt(this.x);
        out.writeInt(this.y);
        out.writeString(name);

    }

    private void readFromParcel(Parcel in)
    {
        this.x = in.readInt();
        this.y = in.readInt();
        this.name = in.readString();

    }

    public static final Parcelable.Creator<MyParcelable> CREATOR = new Parcelable.Creator<MyParcelable>() {

        public MyParcelable createFromParcel(Parcel in) {
            return new MyParcelable(in);
        }

        public MyParcelable[] newArray(int size) {
            return new MyParcelable[size];
        }

    };

}