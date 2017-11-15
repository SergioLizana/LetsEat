package ikigaiworks.letseat.manager;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

import ikigaiworks.letseat.model.Category;
import ikigaiworks.letseat.model.Menu;
import ikigaiworks.letseat.model.Producto;
import ikigaiworks.letseat.ui.presenters.Presenter;

/**
 * Created by sergiolizanamontero on 21/9/17.
 */
public class FirebaseManagerImpl implements FirebaseManager {

     FirebaseDatabase database;


    public FirebaseManagerImpl(){
        database = FirebaseDatabase.getInstance();
    }

    @Override
    public void getCategories(final Presenter.OperationCategories listener) {
        final ArrayList<Category> categoryList = new ArrayList<Category>();
        DatabaseReference myRef = database.getReference("CATEGORIES");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    Category category = postSnapshot.getValue(Category.class);
                    categoryList.add(category);
                }
                listener.onCategoriesReceived(categoryList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("test", "on cancelled");
            }
        });

    }

    @Override
    public void getSubCategories(Category category, final Presenter.OperationCategories listener) {

    }

    @Override
    public void getProductos(Category category, final Presenter.OperationProducts listener) {
        DatabaseReference menu =  database.getReference("MENU").child(category.getReference());
        menu.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Menu menu = dataSnapshot.getValue(Menu.class);
                listener.onProductsReceived(getProductByMap(menu.getProducts()));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("error",databaseError.toString());
            }
        });
    }

    private ArrayList<Producto> getProductByMap(Map<String,Producto> map){

        ArrayList<Producto> productos = new ArrayList<>();
        for (Producto p : map.values()) {
            productos.add(p);
        }

        return productos;
    }

    @Override
    public void getProducto(String idProducto, final Presenter.OperationProduct listener) {
        DatabaseReference menu =  database.getReference("PRODUCTS").child(idProducto);
        menu.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Producto producto = dataSnapshot.getValue(Producto.class);
                listener.onProductReceived(producto);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("error",databaseError.toString());
            }
        });
    }
}
