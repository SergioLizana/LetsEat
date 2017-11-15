package ikigaiworks.letseat.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;

import ikigaiworks.letseat.model.Extra;
import ikigaiworks.letseat.model.ProductToCart;
import ikigaiworks.letseat.model.Producto;

/**
 * Created by sergiolizanamontero on 1/11/17.
 */

public class CommonUtils {

    ArrayList<ProductToCart> cartList;

    public static ArrayList<String> getExtraAsString(Map<String,Extra> extras) {
        ArrayList<Extra> ext = new ArrayList<Extra>(extras.values());
        ArrayList _return = new ArrayList<String>();
        for (Extra extra: ext){
            _return.add(extra.getName());
        }
        return _return;
    }

    public static void addToCart(ProductToCart p){
        SharedPreferences mPrefs = App.getAppContext().getSharedPreferences("cart",Context.MODE_PRIVATE);
        ArrayList<ProductToCart> productos = new ArrayList<ProductToCart>(getCart());
        productos.add(p);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(productos);
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.putString("listCart",json);
        editor.commit();

    }
    public static void updateCart(ArrayList<ProductToCart> productToCarts){
        deleteCart();
        SharedPreferences mPrefs = App.getAppContext().getSharedPreferences("cart",Context.MODE_PRIVATE);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(productToCarts);
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.putString("listCart",json);
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
        SharedPreferences prefs = App.getAppContext().getSharedPreferences("cart",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear();
        editor.commit();
    }

    public static ArrayList<ProductToCart> getCart(){
        SharedPreferences prefs = App.getAppContext().getSharedPreferences("cart",Context.MODE_PRIVATE);
        Type listType = new TypeToken<ArrayList<ProductToCart>>(){}.getType();

        ArrayList<ProductToCart> cartList = new Gson().fromJson(prefs.getString("listCart",""), listType);


        return cartList!=null?cartList:new ArrayList<ProductToCart>();
    }

    public static double getCartPrice(){
        ArrayList<ProductToCart> cart = getCart();
        double price = 0.0;
        if (cart!=null && cart.size()>0){
            for(ProductToCart productToCart : cart){
                price = price + productToCart.getPrice();
            }
            return price;
        }else{
            return price;
        }
    }

    public static int getCartItemPosition(ProductToCart productToCart){
        ArrayList<ProductToCart> cart = getCart();
       return cart.size()>0?getIndexByProperty(productToCart.getId()):-1;
    }

    private static int getIndexByProperty(int id) {
        for (int i = 0; i < getCart().size(); i++) {
            if ( getCart().get(i).getId() == id) {
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
                if(cart.get(i).getId()>lastId){ //
                    lastId = cart.get(i).getId();
                }
            }
            return lastId+1;
        }
    }

    public static ProductToCart parseProductToCart(Producto p,String extra){
        return new ProductToCart(getLastId(),p.getName(),p.getPrice(),p.getDiscount(),p.getReference(),extra,p.getImage());
    }


}
