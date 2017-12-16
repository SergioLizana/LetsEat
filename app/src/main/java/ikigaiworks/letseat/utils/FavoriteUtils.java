package ikigaiworks.letseat.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import ikigaiworks.letseat.R;
import ikigaiworks.letseat.model.FavOrder;
import ikigaiworks.letseat.model.ProductToCart;
import ikigaiworks.letseat.model.Producto;

import static ikigaiworks.letseat.app.LetsEatConstants.SHARED_PREFERENCES_FAV_LIST;

/**
 * Created by sergiolizanamontero on 15/12/17.
 */

public class FavoriteUtils {

    public static void addToFav(LinkedHashMap<String,FavOrder> favItem){
        SharedPreferences mPrefs = App.getAppContext().getSharedPreferences(SHARED_PREFERENCES_FAV_LIST
                , Context.MODE_PRIVATE);

        LinkedHashMap<String,FavOrder> favList = new LinkedHashMap<>(getFavList());
        favList.putAll(favItem);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(favList);
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.putString(SHARED_PREFERENCES_FAV_LIST,json);
        editor.commit();
    }

    public static LinkedHashMap<String,FavOrder> getFavList(){
        SharedPreferences prefs = App.getAppContext().getSharedPreferences(SHARED_PREFERENCES_FAV_LIST,Context.MODE_PRIVATE);
        Type hashType = new TypeToken<LinkedHashMap<String,FavOrder>>(){}.getType();
        LinkedHashMap<String,FavOrder> favList =
                new Gson().fromJson(prefs.getString(SHARED_PREFERENCES_FAV_LIST,""), hashType);
        return favList!=null?favList:new  LinkedHashMap<String,FavOrder>();
    }

    public static ArrayList<ProductToCart> getFavByName(String name){
        return null;
    }

    public static void deleteFavByName(String name){

    }


}
