package ikigaiworks.letseat.manager;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.androidannotations.annotations.EBean;

import java.util.ArrayList;
import java.util.List;

import ikigaiworks.letseat.model.Category;
import ikigaiworks.letseat.model.Menu;
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
    public void getCategories(final Presenter presenter) {
        final List<Category> categoryList = new ArrayList<Category>();
        DatabaseReference myRef = database.getReference("CATEGORIES");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    Category category = postSnapshot.getValue(Category.class);
                    categoryList.add(category);
                }
                presenter.printData(categoryList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("test", "on cancelled");
            }
        });

    }

    @Override
    public void getSubCategories(Category category, Presenter presenter) {

    }

    @Override
    public void getProductos(Category category, final Presenter presenter) {
        DatabaseReference menu =  database.getReference("MENU").child(category.getReference());
        menu.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("succes",dataSnapshot.toString());

           //     Log.d("Firebase Category",category.getName());
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    Menu menu = postSnapshot.getValue(Menu.class);
                    presenter.printData(menu);
                }

//                if (category.getsubtype() != null) {
//                    for (String key : category.getsubtype().keySet()) {
//                        Menu menu = dataSnapshot.child(key).getValue(Menu.class);
//                        for (String keyProd: menu.getProducts().keySet()){
//                            getProductById(keyProd);
//                        }
//
//                    }
//
//                }else{
//                    Menu menu1 = dataSnapshot.child(category.getReference()).getValue(Menu.class);
//                    for (String keyProd: menu1.getProducts().keySet()){
//                        getProductById(keyProd);
//                    }
//                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("error",databaseError.toString());
            }
        });
    }

    @Override
    public void getProducto(int idProducto, Presenter presenter) {

    }
}
