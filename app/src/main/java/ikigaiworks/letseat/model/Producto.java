package ikigaiworks.letseat.model;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by sergiolizanamontero on 25/8/17.
 */
@IgnoreExtraProperties
public class Producto {

    private String description;
    private Boolean disabled;
    private Double discount;
    private Boolean es_visible;
    private String image;
    private String name;
    private Double price;
    private String reference;
    private Map<String,Map<String,Boolean>> items;

    public Producto(String description, Boolean disabled, Double discount, Boolean es_visible, String image, Map<String,Map<String,Boolean>> items, String name, Double price, String reference) {
        this.description = description;
        this.disabled = disabled;
        this.discount = discount;
        this.es_visible = es_visible;
        this.image = image;
        this.items = items;
        this.name = name;
        this.price = price;
        this.reference = reference;
    }

    public Producto(){}


    public Map<String,Map<String,Boolean>> getItems() {
        return items;
    }

    public void setItems(Map<String,Map<String,Boolean>> items) {
        this.items = items;
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
                ", items=" + items +
                '}';
    }
}
