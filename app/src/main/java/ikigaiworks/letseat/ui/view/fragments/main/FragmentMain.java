package ikigaiworks.letseat.ui.view.fragments.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;
import com.yarolegovich.discretescrollview.DiscreteScrollView;
import com.yarolegovich.discretescrollview.InfiniteScrollAdapter;
import com.yarolegovich.discretescrollview.Orientation;
import com.yarolegovich.discretescrollview.transform.ScaleTransformer;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import ikigaiworks.letseat.R;
import ikigaiworks.letseat.model.Carrusel;
import ikigaiworks.letseat.model.CarruselSlide;
import ikigaiworks.letseat.ui.presenters.main.MainFragmentPresenter;
import ikigaiworks.letseat.ui.presenters.main.MainFragmentPresenterImpl;
import ikigaiworks.letseat.ui.view.adapters.CarruselAdapter;
import ikigaiworks.letseat.utils.DiscreteScrollViewOptions;


public class FragmentMain extends Fragment implements DiscreteScrollView.OnItemChangedListener{

    Unbinder unbind;

    @BindView(R.id.picker)
    DiscreteScrollView scrollView;
    @BindView(R.id.titleCarrusel)
    TextView title;

    MainFragmentPresenter presenter;

    private InfiniteScrollAdapter infiniteAdapter;
    private CarruselAdapter adapter;
    FirebaseDatabase database;
    ArrayList<CarruselSlide> data;


    public static FragmentMain newInstance(){
        FragmentMain fragmentMain = new FragmentMain();
        return fragmentMain;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null){
            data = new ArrayList<CarruselSlide>();
        }else{
            data = savedInstanceState.getParcelableArrayList("slides");
        }
        presenter = new MainFragmentPresenterImpl();
        presenter.setMainFragmentActivity(this);

        getActivity().setTitle("Taste Bakery");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main,container,false);
        unbind  = ButterKnife.bind(this,view);
       // database = FirebaseDatabase.getInstance();
        initCarruselConf();

        return view;

    }

    private void initCarruselConf(){
        scrollView.setOrientation(Orientation.HORIZONTAL);
        scrollView.addOnItemChangedListener(this);

        adapter = new CarruselAdapter(data, new CarruselAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(CarruselSlide item) {
                Toast.makeText(getActivity().getApplicationContext(),"test",Toast.LENGTH_LONG).show();
            }
        },getActivity().getApplicationContext(),presenter);


        infiniteAdapter = InfiniteScrollAdapter.wrap(adapter);
        scrollView.setAdapter(infiniteAdapter);
        scrollView.setItemTransitionTimeMillis(DiscreteScrollViewOptions.getTransitionTime());
        scrollView.setItemTransformer(new ScaleTransformer.Builder()
                .setMinScale(0.8f)
                .build());
        presenter.retrieveSlides();
    }

    public void printCarrusel(Carrusel carrusel){
        data =  carrusel.getSlides();
        adapter.updateCarrusel(data);
        infiniteAdapter.notifyDataSetChanged();
//        onItemChanged(data.get(0));
    }

    private void onItemChanged(CarruselSlide item) {
        title.setText(item.getTitle());
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
    public void onCurrentItemChanged(@Nullable RecyclerView.ViewHolder viewHolder, int adapterPosition) {
        int positionInDataSet = infiniteAdapter.getRealPosition(adapterPosition);
        onItemChanged(data.get(positionInDataSet));
    }


}
