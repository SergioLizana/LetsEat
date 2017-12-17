package ikigaiworks.letseat.model;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by sergiolizanamontero on 25/8/17.
 */

public class Category implements Serializable {

    private Double discount;
    private String name;
    private String reference;
    private int order;
    private String image;
    private String header;
    private Map<String, Category> subtype;

    public Category(Double discount, String name, String reference, Map<String, Category> subtype) {

        this.discount = discount;
        this.name = name;
        this.reference = reference;
        this.subtype = subtype;
    }

    public Category(Double discount, String name, String reference, int order, String image, String header, Map<String, Category> subtype) {
        this.discount = discount;
        this.name = name;
        this.reference = reference;
        this.order = order;
        this.image = image;
        this.header = header;
        this.subtype = subtype;
    }

    public Category() {

    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Map<String, Category> getSubtype() {
        return subtype;
    }

    public void setSubtype(Map<String, Category> subtype) {
        this.subtype = subtype;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }
}
