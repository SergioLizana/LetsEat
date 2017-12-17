package ikigaiworks.letseat.ui.presenters.lastorder;

import java.util.ArrayList;

import ikigaiworks.letseat.model.FavOrder;
import ikigaiworks.letseat.model.LastOrder;
import ikigaiworks.letseat.model.ProductToCart;
import ikigaiworks.letseat.ui.view.fragments.lastorder.FragmentFavOrderList;

/**
 * Created by sergiolizanamontero on 30/11/17.
 */

public class LastOrderPresenterImpl {

    FragmentFavOrderList frag;

    public LastOrderPresenterImpl(FragmentFavOrderList frag) {
        this.frag = frag;
    }


    public ArrayList<LastOrder> getOrders() {
        LastOrder order1 = new LastOrder();
        order1.setName("Americano Completo");
        order1.setFecha("Realizado: 21/11/2017");


        ProductToCart productToCart1 = new ProductToCart();
        productToCart1.setQuantity(3);
        productToCart1.setCartId(0);
        productToCart1.setDiscount(0.0);
        productToCart1.setExtra("Café Vienes");
        productToCart1.setExtraVisibility(0);
        productToCart1.setImage("https://firebasestorage.googleapis.com/v0/b/letseat-7fc44.appspot.com/o/products%2F05.jpg?alt=media&token=ef03ed4e-e8d7-4d51-92fc-c7b2331e7ab6");
        productToCart1.setName("Desayuno Americano");
        productToCart1.setPrice(5);
        productToCart1.setReference("5");


        ProductToCart productToCart2 = new ProductToCart();
        productToCart2.setQuantity(4);
        productToCart2.setCartId(1);
        productToCart2.setDiscount(0.0);
        productToCart2.setExtra("Zumo de Naranja");
        productToCart2.setExtraVisibility(0);
        productToCart2.setImage("https://firebasestorage.googleapis.com/v0/b/letseat-7fc44.appspot.com/o/products%2F04.jpg?alt=media&token=92fde2ca-07ff-4498-b5d6-a61de8711d38");
        productToCart2.setName("Bikini");
        productToCart2.setPrice(3.5);
        productToCart2.setReference("4");


        ProductToCart productToCart3 = new ProductToCart();
        productToCart3.setQuantity(1);
        productToCart3.setCartId(2);
        productToCart3.setDiscount(0.0);
        productToCart3.setExtra("Americano");
        productToCart3.setExtraVisibility(0);
        productToCart3.setImage("https://firebasestorage.googleapis.com/v0/b/letseat-7fc44.appspot.com/o/products%2F07.jpg?alt=media&token=940c01b2-6f7f-4bdd-8848-751a52483451");
        productToCart3.setName("Continental");
        productToCart3.setPrice(4.99);
        productToCart3.setReference("10");


        ProductToCart productToCart4 = new ProductToCart();
        productToCart4.setQuantity(3);
        productToCart4.setCartId(3);
        productToCart4.setDiscount(0.0);
        productToCart4.setExtra("Capuccino");
        productToCart4.setExtraVisibility(0);
        productToCart4.setImage("https://firebasestorage.googleapis.com/v0/b/letseat-7fc44.appspot.com/o/products%2F07.jpg?alt=media&token=940c01b2-6f7f-4bdd-8848-751a52483451");
        productToCart4.setName("Iberico");
        productToCart4.setPrice(2.5);
        productToCart4.setReference("7");

        ArrayList<ProductToCart> ps1 = new ArrayList<>();
        ps1.add(productToCart1);
        ps1.add(productToCart2);
        ps1.add(productToCart3);
        ps1.add(productToCart4);

        order1.setProductToCart(ps1);


        LastOrder order2 = new LastOrder();
        order2.setName("Despues de Correr");
        order2.setFecha("Realizado: 17/10/2017");


        ProductToCart productToCart5 = new ProductToCart();
        productToCart5.setQuantity(3);
        productToCart5.setCartId(0);
        productToCart5.setDiscount(0.0);
        productToCart5.setExtra("Café Vienes");
        productToCart5.setExtraVisibility(0);
        productToCart5.setImage("https://firebasestorage.googleapis.com/v0/b/letseat-7fc44.appspot.com/o/products%2F05.jpg?alt=media&token=ef03ed4e-e8d7-4d51-92fc-c7b2331e7ab6");
        productToCart5.setName("Desayuno Americano");
        productToCart5.setPrice(5);
        productToCart5.setReference("5");


        ProductToCart productToCart6 = new ProductToCart();
        productToCart6.setQuantity(4);
        productToCart6.setCartId(1);
        productToCart6.setDiscount(0.0);
        productToCart6.setExtra("Zumo de Naranja");
        productToCart6.setExtraVisibility(0);
        productToCart6.setImage("https://firebasestorage.googleapis.com/v0/b/letseat-7fc44.appspot.com/o/products%2F04.jpg?alt=media&token=92fde2ca-07ff-4498-b5d6-a61de8711d38");
        productToCart6.setName("Bikini");
        productToCart6.setPrice(3.5);
        productToCart6.setReference("4");


        ProductToCart productToCart7 = new ProductToCart();
        productToCart7.setQuantity(1);
        productToCart7.setCartId(2);
        productToCart7.setDiscount(0.0);
        productToCart7.setExtra("Americano");
        productToCart7.setExtraVisibility(0);
        productToCart7.setImage("https://firebasestorage.googleapis.com/v0/b/letseat-7fc44.appspot.com/o/products%2F07.jpg?alt=media&token=940c01b2-6f7f-4bdd-8848-751a52483451");
        productToCart7.setName("Continental");
        productToCart7.setPrice(4.99);
        productToCart7.setReference("10");


        ProductToCart productToCart8 = new ProductToCart();
        productToCart8.setQuantity(3);
        productToCart8.setCartId(3);
        productToCart8.setDiscount(0.0);
        productToCart8.setExtra("Capuccino");
        productToCart8.setExtraVisibility(0);
        productToCart8.setImage("https://firebasestorage.googleapis.com/v0/b/letseat-7fc44.appspot.com/o/products%2F07.jpg?alt=media&token=940c01b2-6f7f-4bdd-8848-751a52483451");
        productToCart8.setName("Iberico");
        productToCart8.setPrice(2.5);
        productToCart8.setReference("7");

        ArrayList<ProductToCart> ps2 = new ArrayList<>();
        ps2.add(productToCart5);
        ps2.add(productToCart6);
        ps2.add(productToCart7);
        ps2.add(productToCart8);

        order2.setProductToCart(ps2);

        LastOrder order3 = new LastOrder();
        order3.setName("Merienda rica");
        order3.setFecha("Realizado: 20/11/2017");


        ProductToCart productToCart9 = new ProductToCart();
        productToCart9.setQuantity(3);
        productToCart9.setCartId(0);
        productToCart9.setDiscount(0.0);
        productToCart9.setExtra("Café Vienes");
        productToCart9.setExtraVisibility(0);
        productToCart9.setImage("https://firebasestorage.googleapis.com/v0/b/letseat-7fc44.appspot.com/o/products%2F05.jpg?alt=media&token=ef03ed4e-e8d7-4d51-92fc-c7b2331e7ab6");
        productToCart9.setName("Desayuno Americano");
        productToCart9.setPrice(5);
        productToCart9.setReference("5");


        ProductToCart productToCart10 = new ProductToCart();
        productToCart10.setQuantity(4);
        productToCart10.setCartId(1);
        productToCart10.setDiscount(0.0);
        productToCart10.setExtra("Zumo de Naranja");
        productToCart10.setExtraVisibility(0);
        productToCart10.setImage("https://firebasestorage.googleapis.com/v0/b/letseat-7fc44.appspot.com/o/products%2F04.jpg?alt=media&token=92fde2ca-07ff-4498-b5d6-a61de8711d38");
        productToCart10.setName("Bikini");
        productToCart10.setPrice(3.5);
        productToCart10.setReference("4");


        ProductToCart productToCart11 = new ProductToCart();
        productToCart11.setQuantity(1);
        productToCart11.setCartId(2);
        productToCart11.setDiscount(0.0);
        productToCart11.setExtra("Americano");
        productToCart11.setExtraVisibility(0);
        productToCart11.setImage("https://firebasestorage.googleapis.com/v0/b/letseat-7fc44.appspot.com/o/products%2F07.jpg?alt=media&token=940c01b2-6f7f-4bdd-8848-751a52483451");
        productToCart11.setName("Continental");
        productToCart11.setPrice(4.99);
        productToCart11.setReference("10");


        ProductToCart productToCart12 = new ProductToCart();
        productToCart12.setQuantity(3);
        productToCart12.setCartId(3);
        productToCart12.setDiscount(0.0);
        productToCart12.setExtra("Capuccino");
        productToCart12.setExtraVisibility(0);
        productToCart12.setImage("https://firebasestorage.googleapis.com/v0/b/letseat-7fc44.appspot.com/o/products%2F07.jpg?alt=media&token=940c01b2-6f7f-4bdd-8848-751a52483451");
        productToCart12.setName("Iberico");
        productToCart12.setPrice(2.5);
        productToCart12.setReference("7");

        ArrayList<ProductToCart> ps3 = new ArrayList<>();
        ps3.add(productToCart9);
        ps3.add(productToCart10);
        ps3.add(productToCart11);
        ps3.add(productToCart12);
        order3.setProductToCart(ps3);

        ArrayList<LastOrder> lastOrders = new ArrayList<>();
        lastOrders.add(order1);
        lastOrders.add(order2);
        lastOrders.add(order3);

        return lastOrders;
    }

    public void onClickEvent(FavOrder favOrder) {
        frag.goToCart(favOrder);
    }
}
