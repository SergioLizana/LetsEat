package ikigaiworks.letseat.ui.view.fragments;

import android.animation.ArgbEvaluator;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.yarolegovich.discretescrollview.DiscreteScrollView;
import com.yarolegovich.discretescrollview.InfiniteScrollAdapter;
import com.yarolegovich.discretescrollview.transform.ScaleTransformer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import ikigaiworks.letseat.R;
import ikigaiworks.letseat.model.Category;
import ikigaiworks.letseat.model.Menu;
import ikigaiworks.letseat.model.Producto;
import ikigaiworks.letseat.ui.view.adapters.CarruselAdapter;
import ikigaiworks.letseat.utils.DiscreteScrollViewOptions;
import ikigaiworks.letseat.utils.ImageGallery;


public class FragmentMain extends Fragment implements
        DiscreteScrollView.ScrollListener<CarruselAdapter.ViewHolder>,
        DiscreteScrollView.OnItemChangedListener<CarruselAdapter.ViewHolder>,
        View.OnClickListener  {

    Unbinder unbind;

    @BindView(R.id.picker)
    DiscreteScrollView scrollView;
    private ArgbEvaluator evaluator;
    private int currentOverlayColor;
    private int overlayColor;
    private InfiniteScrollAdapter infiniteAdapter;
    FirebaseDatabase database;


    public static FragmentMain newInstance(){
        FragmentMain fragmentMain = new FragmentMain();
        return fragmentMain;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main,container,false);
        unbind  = ButterKnife.bind(this,view);
        database = FirebaseDatabase.getInstance();
        evaluator = new ArgbEvaluator();
        currentOverlayColor = ContextCompat.getColor(getActivity(), R.color.galleryCurrentItemOverlay);
        overlayColor = ContextCompat.getColor(getActivity(), R.color.galleryItemOverlay);
        ImageGallery gallery = new ImageGallery(getActivity());
        infiniteAdapter = InfiniteScrollAdapter.wrap(new CarruselAdapter(gallery.getData(), new CarruselAdapter.OnItemClickListener(){
            @Override public void onItemClick(Integer item) {
                Toast.makeText(getContext(), "Item Clicked "+item, Toast.LENGTH_LONG).show();
            }
        }));
        scrollView.setAdapter(infiniteAdapter);
        scrollView.addScrollListener(this);
        scrollView.addOnItemChangedListener(this);
        scrollView.scrollToPosition(0);
        scrollView.setItemTransitionTimeMillis(DiscreteScrollViewOptions.getTransitionTime());
        scrollView.setItemTransformer(new ScaleTransformer.Builder()
                .setMinScale(0.8f)
                .build());
   //     getCategories();

        return view;

    }

/*    public void getCategories(){

        DatabaseReference myRef = database.getReference("CATEGORIES");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    Category category = postSnapshot.getValue(Category.class);
                    Log.e("FirebaseCategory", category.getName());
                    if (category.getReference().equals("C2")) {
                        getMenuByCategory(category);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("test", "on cancelled");
            }
        });

    }

    public void getMenuByCategory (final Category category){
        DatabaseReference menu =  database.getReference("MENU");
        menu.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Log.d("Firebase Category",category.getName());
                if (category.getsubtype() != null) {
                    for (String key : category.getsubtype().keySet()) {
                        Menu menu = dataSnapshot.child(key).getValue(Menu.class);
                        for (String keyProd: menu.getProducts().keySet()){
                            getProductById(keyProd);
                        }

                    }

                }else{
                    Menu menu1 = dataSnapshot.child(category.getReference()).getValue(Menu.class);
                    for (String keyProd: menu1.getProducts().keySet()){
                        getProductById(keyProd);
                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    public void getProductById (final String key){
        DatabaseReference menu =  database.getReference("PRODUCTS");

        menu.child(key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Producto p = dataSnapshot.getValue(Producto.class);
                Log.d("firebase producto",p.getName());
                if (p.getItems()!= null) {
                    for (Map<String, Boolean> items : p.getItems().values()) {
                        for (String key : items.keySet()) {
                            getProductById(key);
                        }
                    }
                }
                Log.d("SEPARACION" , "-------------------- /n");


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }*/


    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        unbind.unbind();
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(getContext(),"Click",Toast.LENGTH_LONG);
    }

    @Override
    public void onCurrentItemChanged(@Nullable CarruselAdapter.ViewHolder viewHolder, int adapterPosition) {
        //viewHolder will never be null, because we never remove items from adapter's list
        if (viewHolder != null) {
            viewHolder.setOverlayColor(currentOverlayColor);
        }
    }

    @Override
    public void onScroll(float scrollPosition, @NonNull CarruselAdapter.ViewHolder currentHolder, @NonNull CarruselAdapter.ViewHolder newCurrent) {
        float position = Math.abs(scrollPosition);
        currentHolder.setOverlayColor(interpolate(position, currentOverlayColor, overlayColor));
        newCurrent.setOverlayColor(interpolate(position, overlayColor, currentOverlayColor));
    }


    private int interpolate(float fraction, int c1, int c2) {
        return (int) evaluator.evaluate(fraction, c1, c2);
    }


//    @OnClick(R.id.button_carta)
//    public void initCarta(View view) {
//        ((MainActivity) getActivity()).replaceFragment(FragmentLogin.newInstance());
//    }
}
