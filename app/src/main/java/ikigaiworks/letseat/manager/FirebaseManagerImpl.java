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
    public void getProductos(Category category, Presenter presenter) {

    }

    @Override
    public void getProducto(int idProducto, Presenter presenter) {

    }
}
