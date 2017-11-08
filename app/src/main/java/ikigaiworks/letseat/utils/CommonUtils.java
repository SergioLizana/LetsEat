package ikigaiworks.letseat.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import ikigaiworks.letseat.model.Extra;
import ikigaiworks.letseat.model.ProductToCart;
import ikigaiworks.letseat.model.Producto;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by sergiolizanamontero on 1/11/17.
 */

public class CommonUtils {

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



    public static void removeFromCart(Producto p){

    }

    public static ArrayList<ProductToCart> getCart(){
        SharedPreferences prefs = App.getAppContext().getSharedPreferences("cart",Context.MODE_PRIVATE);
        Type listType = new TypeToken<ArrayList<ProductToCart>>(){}.getType();

        ArrayList<ProductToCart> yourClassList = new Gson().fromJson(prefs.getString("listCart",""), listType);

        return yourClassList;
    }

    public static ProductToCart parseProductToCart(Producto p,String extra){
        return new ProductToCart(p.getName(),p.getPrice(),p.getDiscount(),p.getReference(),extra);
    }


}
