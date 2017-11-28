package ikigaiworks.letseat.model;

import java.util.HashMap;

/**
 * Created by sergiolizanamontero on 28/11/17.
 */

public class LastOrders {
    private HashMap<String,LastOrder> orders2;

    public LastOrders(){}

    public LastOrders( HashMap<String, LastOrder> orders2) {
        this.orders2 = orders2;
    }

    public HashMap<String, LastOrder> getOrders() {
        return orders2;
    }

    public void setOrders(HashMap<String, LastOrder> orders) {
        this.orders2 = orders;
    }
}
