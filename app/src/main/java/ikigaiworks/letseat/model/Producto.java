package ikigaiworks.letseat.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sergiolizanamontero on 25/8/17.
 */
@IgnoreExtraProperties
public class Producto implements Parcelable {

    private String description;
    private Boolean disabled;
    private Double discount;
    private Boolean es_visible;
    private String image;
    private String name;
    private Double price;
    private String reference;
    private Map<String,Extra> extra;

    public Producto(String description, Boolean disabled, Double discount, Boolean es_visible, String image, Map<String,Extra> extra, String name, Double price, String reference) {
        this.description = description;
        this.disabled = disabled;
        this.discount = discount;
        this.es_visible = es_visible;
        this.image = image;
        this.extra = extra;
        this.name = name;
        this.price = price;
        this.reference = reference;
    }



    public Producto(){}

    public Map<String, Extra> getExtra() {
        return extra;
    }

    public void setExtra(Map<String, Extra> extra) {
        this.extra = extra;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Boolean isEs_visible() {
        return es_visible;
    }

    public void setEs_visible(Boolean es_visible) {
        this.es_visible = es_visible;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "description='" + description + '\'' +
                ", disabled=" + disabled +
                ", discount='" + discount + '\'' +
                ", es_visible=" + es_visible +
                ", image='" + image + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", reference='" + reference + '\'' +
                ", items=" + extra +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.description);
        dest.writeValue(this.disabled);
        dest.writeValue(this.discount);
        dest.writeValue(this.es_visible);
        dest.writeString(this.image);
        dest.writeString(this.name);
        dest.writeValue(this.price);
        dest.writeString(this.reference);
        dest.writeInt(this.extra.size());
        for (Map.Entry<String, Extra> entry : this.extra.entrySet()) {
            dest.writeString(entry.getKey());
            dest.writeParcelable(entry.getValue(), flags);
        }
    }

    protected Producto(Parcel in) {
        this.description = in.readString();
        this.disabled = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.discount = (Double) in.readValue(Double.class.getClassLoader());
        this.es_visible = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.image = in.readString();
        this.name = in.readString();
        this.price = (Double) in.readValue(Double.class.getClassLoader());
        this.reference = in.readString();
        int extraSize = in.readInt();
        this.extra = new HashMap<String, Extra>(extraSize);
        for (int i = 0; i < extraSize; i++) {
            String key = in.readString();
            Extra value = in.readParcelable(Extra.class.getClassLoader());
            this.extra.put(key, value);
        }
    }

    public static final Parcelable.Creator<Producto> CREATOR = new Parcelable.Creator<Producto>() {
        @Override
        public Producto createFromParcel(Parcel source) {
            return new Producto(source);
        }

        @Override
        public Producto[] newArray(int size) {
            return new Producto[size];
        }
    };
}
