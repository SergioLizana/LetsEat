package ikigaiworks.letseat.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by sergiolizanamontero on 13/9/17.
 */

public class CarruselSlide implements Parcelable {

    private String title;
    private int id;
    private String text;
    private int icon;

    public CarruselSlide() {
    }

    public CarruselSlide(String title, int id, String text, int icon) {
        this.title = title;
        this.id = id;
        this.text = text;
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeInt(this.id);
        dest.writeString(this.text);
        dest.writeInt(this.icon);
    }

    protected CarruselSlide(Parcel in) {
        this.title = in.readString();
        this.id = in.readInt();
        this.text = in.readString();
        this.icon = in.readInt();
    }

    public static final Parcelable.Creator<CarruselSlide> CREATOR = new Parcelable.Creator<CarruselSlide>() {
        @Override
        public CarruselSlide createFromParcel(Parcel source) {
            return new CarruselSlide(source);
        }

        @Override
        public CarruselSlide[] newArray(int size) {
            return new CarruselSlide[size];
        }
    };
}
