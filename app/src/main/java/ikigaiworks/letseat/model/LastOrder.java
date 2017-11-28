package ikigaiworks.letseat.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * Created by sergiolizanamontero on 28/11/17.
 */

public class LastOrder implements Serializable {
    private Date date;
    private Map<String,ProductToCart> cartProducts;

    public LastOrder(){
    }

    public LastOrder(Date date, Map<String, ProductToCart> cartProducts) {
        this.date = date;
        this.cartProducts = cartProducts;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Map<String, ProductToCart> getCartProducts() {
        return cartProducts;
    }

    public void setCartProducts(Map<String, ProductToCart> cartProducts) {
        this.cartProducts = cartProducts;
    }
}
