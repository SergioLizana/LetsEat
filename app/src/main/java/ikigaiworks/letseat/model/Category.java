package ikigaiworks.letseat.model;

import java.util.Map;

/**
 * Created by sergiolizanamontero on 25/8/17.
 */

public class Category {
    private Double discount;
    private String name;
    private String reference;
    private Map<String, Category> subtype;

    public Category(Double discount, String name, String reference, Map<String, Category> subtype) {

        this.discount = discount;
        this.name = name;
        this.reference = reference;
        this.subtype = subtype;
    }

    public Category(){

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

    public Map<String, Category> getsubtype() {
        return subtype;
    }

    public void setsubtype(Map<String, Category> subtype) {
        this.subtype = subtype;
    }
}
