package ikigaiworks.letseat.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;

/**
 * Created by sergiolizanamontero on 1/11/17.
 */

public class ProductToCart implements Parcelable {
    private int cartId;
    private String name;
    private double price;
    private double discount;
    private String reference;
    private String extra;
    private String productId;
    private String image;
    private int extraVisibility;
    private int quantity = 1;

    public ProductToCart() {
    }

    public ProductToCart(String productId, int cartId, String name, double price, double discount, String reference, String extra, String image) {
        this.cartId = cartId;
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.discount = discount;
        this.reference = reference;
        this.extra = extra;
        this.image = image;
        this.extraVisibility = extra == null || extra.isEmpty() ? View.INVISIBLE : View.VISIBLE;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int isExtraVisibility() {
        return extraVisibility;
    }

    public void setExtraVisibility(int extraVisibility) {
        this.extraVisibility = extraVisibility;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ProductToCart) {

            if (this.getCartId() == ((ProductToCart) obj).getCartId() ||
                    this.getReference().equals(((ProductToCart) obj).getReference()) &&
                            this.extra.equals(((ProductToCart) obj).getExtra())) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
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
