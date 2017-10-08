package ikigaiworks.letseat.model;

import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Menu implements Serializable{

    private Map<String,Producto> products;

    public Menu(Map<String,Producto> products) {
        this.products = products;
    }

    public Menu(){}

    public Map<String,Producto>getProducts() {
        return products;
    }

    public void setProducts(Map<String,Producto>  products) {
        this.products = products;
    }
}
