package ikigaiworks.letseat.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by sergiolizanamontero on 29/10/17.
 */

public class Extra implements Parcelable {


    public Extra(String name) {
        this.name = name;
    }

    public Extra(){}

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
    }

    protected Extra(Parcel in) {
        this.name = in.readString();
    }

    public static final Parcelable.Creator<Extra> CREATOR = new Parcelable.Creator<Extra>() {
        @Override
        public Extra createFromParcel(Parcel source) {
            return new Extra(source);
        }

        @Override
        public Extra[] newArray(int size) {
            return new Extra[size];
        }
    };
}
