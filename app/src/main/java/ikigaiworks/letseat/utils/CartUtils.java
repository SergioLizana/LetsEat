package ikigaiworks.letseat.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;

import ikigaiworks.letseat.R;
import ikigaiworks.letseat.model.Extra;
import ikigaiworks.letseat.model.ProductToCart;
import ikigaiworks.letseat.model.Producto;

/**
 * Created by sergiolizanamontero on 1/11/17.
 */

public class CartUtils {

    public static ArrayList<String> getExtraAsString(Map<String,Extra> extras) {
        ArrayList<Extra> ext = new ArrayList<>(extras.values());
        ArrayList _return = new ArrayList<String>();
        for (Extra extra: ext){
            _return.add(extra.getName());
        }
        return _return;
    }

    public static int getCartSize(){
        ArrayList<ProductToCart> cart = getCart();
        int size = 0;
        for(ProductToCart productToCart: cart){
            size = size + productToCart.getQuantity();
        }
        return size;

    }

    public static ArrayList<Producto> getProductByMap(Map<String,Producto> map){

        ArrayList<Producto> productos = new ArrayList<>();
        productos.addAll(map.values());

        return productos;
    }

    public static void addToCart(ProductToCart p){
        boolean productAdded = false;
        SharedPreferences mPrefs = App.getAppContext().getSharedPreferences(App.getAppContext().getString(R.string.shared_name_cart)
                ,Context.MODE_PRIVATE);
        ArrayList<ProductToCart> productsInCart = new ArrayList<>(getCart());
        for(ProductToCart productInCart : productsInCart){
            if (productInCart.equals(p)){
                productInCart.setQuantity(productInCart.getQuantity()+1);
                productAdded = true;
                break;
            }
        }
        if (!productAdded) {
            productsInCart.add(p);
        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(productsInCart);
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.putString(App.getAppContext().getString(R.string.shared_name_list),json);
        editor.commit();

    }
    public static void updateCart(ArrayList<ProductToCart> productToCarts){
        deleteCart();
        SharedPreferences mPrefs = App.getAppContext().getSharedPreferences(App.getAppContext().getString(R.string.shared_name_cart),Context.MODE_PRIVATE);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(productToCarts);
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.putString(App.getAppContext().getString(R.string.shared_name_list),json);
        editor.commit();

    }

    public static void removeFromCart(Producto p){

    }

    public static void removeFromCartPosition(int position){
        ArrayList<ProductToCart> cart = getCart();
        if (!cart.isEmpty()){
            cart.remove(position);
            updateCart(cart);
        }
    }

    public static void deleteCart(){
        SharedPreferences prefs = App.getAppContext().getSharedPreferences(App.getAppContext().getString(R.string.shared_name_cart),Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear();
        editor.commit();
    }

    public static ArrayList<ProductToCart> getCart(){
        SharedPreferences prefs = App.getAppContext()
                .getSharedPreferences(App.getAppContext().getString(R.string.shared_name_cart),Context.MODE_PRIVATE);
        Type listType = new TypeToken<ArrayList<ProductToCart>>(){}.getType();

        ArrayList<ProductToCart> cartList =
                new Gson().fromJson(prefs.getString(App.getAppContext().getString(R.string.shared_name_list),""), listType);


        return cartList!=null?cartList:new ArrayList<ProductToCart>();
    }

    public static double getCartPrice(){
        ArrayList<ProductToCart> cart = getCart();
        double price = 0.0;
        if (cart!=null && cart.size()>0){
            for(ProductToCart productToCart : cart){
                price = price +(productToCart.getPrice()*productToCart.getQuantity());
            }
            return price;
        }else{
            return price;
        }
    }

    public static double getCartPrice(ArrayList<ProductToCart> cart){
        double price = 0.0;
        if (cart!=null && cart.size()>0){
            for(ProductToCart productToCart : cart){
                price = price +(productToCart.getPrice()*productToCart.getQuantity());
            }
            return price;
        }else{
            return price;
        }
    }

    public static int getCartItemPosition(ProductToCart productToCart){
        ArrayList<ProductToCart> cart = getCart();
       return cart.size()>0?getIndexByProperty(productToCart.getCartId()):-1;
    }

    public static void addToQuantity(ProductToCart productToCart){
        ArrayList<ProductToCart> cart = getCart();
        for(ProductToCart product: cart){
            if (product.equals(productToCart)){
                product.setQuantity(product.getQuantity()+1);
                break;
            }
        }
        updateCart(cart);
    }

    public static void removeFromQuantity(ProductToCart productToCart){
        ArrayList<ProductToCart> cart = getCart();
        for(ProductToCart product: cart){
            if (product.equals(productToCart)){
                if (product.getQuantity()>1) {
                    product.setQuantity(product.getQuantity() -1);
                }
                break;
            }
        }
        updateCart(cart);
    }

    private static int getIndexByProperty(int id) {
        for (int i = 0; i < getCart().size(); i++) {
            if ( getCart().get(i).getCartId() == id) {
                return i;
            }
        }
        return -1;// not there is list
    }

    private static int getLastId(){
        int lastId = 0;
        ArrayList<ProductToCart> cart = getCart();
        if(cart.isEmpty()) {
            return lastId;
        }else{
            for(int i=0; i<cart.size(); i++){
                if(cart.get(i).getCartId()>lastId){ //
                    lastId = cart.get(i).getCartId();
                }
            }
            return lastId+1;
        }
    }

    public static ProductToCart parseProductToCart(Producto p,String extra){
        return new ProductToCart(p.getReference(),getLastId()
                ,p.getName(),p.getPrice(),p.getDiscount(),p.getReference(),extra,p.getImage());
    }


}
