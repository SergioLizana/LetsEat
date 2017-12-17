package ikigaiworks.letseat.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by sergiolizanamontero on 28/11/17.
 */

public class LastOrder implements Parcelable {

    private String fecha;
    private String name;
    private ArrayList<ProductToCart> productToCart;

    public LastOrder() {

    }

    public LastOrder(String fecha, String name, ArrayList<ProductToCart> productToCart) {
        this.fecha = fecha;
        this.name = name;
        this.productToCart = productToCart;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<ProductToCart> getProductToCart() {
        return productToCart;
    }

    public void setProductToCart(ArrayList<ProductToCart> productToCart) {
        this.productToCart = productToCart;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.fecha);
        dest.writeString(this.name);
        dest.writeTypedList(this.productToCart);
    }

    protected LastOrder(Parcel in) {
        this.fecha = in.readString();
        this.name = in.readString();
        this.productToCart = in.createTypedArrayList(ProductToCart.CREATOR);
    }

    public static final Parcelable.Creator<LastOrder> CREATOR = new Parcelable.Creator<LastOrder>() {
        @Override
        public LastOrder createFromParcel(Parcel source) {
            return new LastOrder(source);
        }

        @Override
        public LastOrder[] newArray(int size) {
            return new LastOrder[size];
        }
    };
}
