package ikigaiworks.letseat.model;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by sergiolizanamontero on 28/11/17.
 */

public class OrderModel implements Serializable{
    private HashMap<String,LastOrders> orders;

    public OrderModel(){
    }

    public OrderModel(HashMap<String, LastOrders> orders) {
        this.orders = orders;
    }

    public HashMap<String, LastOrders> getOrders() {
        return orders;
    }

    public void setOrders(HashMap<String, LastOrders> orders) {
        this.orders = orders;
    }
}
