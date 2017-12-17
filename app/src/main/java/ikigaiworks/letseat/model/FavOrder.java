package ikigaiworks.letseat.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by sergiolizanamontero on 16/12/17.
 */

public class FavOrder implements Parcelable {
    private Date dateOrder;
    private ArrayList<ProductToCart> products;
    private String name;
    private String dateFormated;

    public FavOrder() {
    }

    public FavOrder(Date dateOrder, ArrayList<ProductToCart> products, String name) {
        this.dateOrder = dateOrder;
        this.products = products;
        this.name = name;
    }

    public String getDateFormated() {
        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");
        String date = DATE_FORMAT.format(dateOrder);
        return date;
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public Date getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(Date dateOrder) {
        this.dateOrder = dateOrder;
    }

    public ArrayList<ProductToCart> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<ProductToCart> products) {
        this.products = products;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.dateOrder != null ? this.dateOrder.getTime() : -1);
        dest.writeTypedList(this.products);
        dest.writeString(this.name);
    }

    protected FavOrder(Parcel in) {
        long tmpDateOrder = in.readLong();
        this.dateOrder = tmpDateOrder == -1 ? null : new Date(tmpDateOrder);
        this.products = in.createTypedArrayList(ProductToCart.CREATOR);
        this.name = in.readString();
    }

    public static final Creator<FavOrder> CREATOR = new Creator<FavOrder>() {
        @Override
        public FavOrder createFromParcel(Parcel source) {
            return new FavOrder(source);
        }

        @Override
        public FavOrder[] newArray(int size) {
            return new FavOrder[size];
        }
    };
}
