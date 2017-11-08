package ikigaiworks.letseat.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by sergiolizanamontero on 1/11/17.
 */

public class ProductToCart implements Parcelable {
    private String name;
    private double price;
    private double discount;
    private String reference;
    private String extra;
    private int id;

    public ProductToCart() {
    }

    public ProductToCart(String name, double price, double discount, String reference, String extra) {
        this.name = name;
        this.price = price;
        this.discount = discount;
        this.reference = reference;
        this.extra = extra;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeDouble(this.price);
        dest.writeDouble(this.discount);
        dest.writeString(this.reference);
        dest.writeString(this.extra);
    }


    protected ProductToCart(Parcel in) {
        this.name = in.readString();
        this.price = in.readDouble();
        this.discount = in.readDouble();
        this.reference = in.readString();
        this.extra = in.readString();
    }

    public static final Parcelable.Creator<ProductToCart> CREATOR = new Parcelable.Creator<ProductToCart>() {
        @Override
        public ProductToCart createFromParcel(Parcel source) {
            return new ProductToCart(source);
        }

        @Override
        public ProductToCart[] newArray(int size) {
            return new ProductToCart[size];
        }
    };
}
