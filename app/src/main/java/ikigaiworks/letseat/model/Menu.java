package ikigaiworks.letseat.model;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

public class Menu {

    private Map<String,Boolean> products;

    public Menu(Map<String,Boolean> products) {
        this.products = products;
    }

    public Menu(){}

    public Map<String,Boolean> getProducts() {
        return products;
    }

    public void setProducts(Map<String,Boolean>  products) {
        this.products = products;
    }
}
