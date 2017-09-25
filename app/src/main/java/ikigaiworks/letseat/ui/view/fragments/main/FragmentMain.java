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

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.InstanceState;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

import butterknife.Unbinder;
import ikigaiworks.letseat.R;
import ikigaiworks.letseat.model.Carrusel;
import ikigaiworks.letseat.model.CarruselSlide;
import ikigaiworks.letseat.ui.presenters.main.MainFragmentPresenter;
import ikigaiworks.letseat.ui.presenters.main.MainFragmentPresenterImpl;
import ikigaiworks.letseat.ui.view.adapters.CarruselAdapter;
import ikigaiworks.letseat.ui.view.fragments.main.bean.MainBean;
import ikigaiworks.letseat.utils.DiscreteScrollViewOptions;


@EFragment(R.layout.fragment_main)
public class FragmentMain extends Fragment implements DiscreteScrollView.OnItemChangedListener{


    @ViewById(R.id.picker)
    DiscreteScrollView scrollView;
    @ViewById(R.id.titleCarrusel)
    TextView title;

    MainFragmentPresenterImpl presenter;

    private InfiniteScrollAdapter infiniteAdapter;
    private CarruselAdapter adapter;


    @Bean
    MainBean mainBean;


    public static FragmentMain newInstance(){
        FragmentMain fragmentMain = new FragmentMain();
        return fragmentMain;
    }

    @AfterViews
    protected void init(){
        presenter = new MainFragmentPresenterImpl();
        presenter.setFragmentMain(this);
        getActivity().setTitle("Taste Bakery");
        initCarruselConf();
    }

    private void initCarruselConf(){
        adapter = new CarruselAdapter(mainBean.getData(), new CarruselAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(CarruselSlide item) {
                Toast.makeText(getActivity().getApplicationContext(),"test",Toast.LENGTH_LONG).show();
            }
        },getActivity().getApplicationContext(),presenter);
        infiniteAdapter = InfiniteScrollAdapter.wrap(adapter);
        buildScrollView();
        presenter.retrieveData();
    }

    private void buildScrollView(){
        scrollView.setOrientation(Orientation.HORIZONTAL);
        scrollView.addOnItemChangedListener(this);
        scrollView.setAdapter(infiniteAdapter);
        scrollView.setItemTransitionTimeMillis(DiscreteScrollViewOptions.getTransitionTime());
        scrollView.setItemTransformer(new ScaleTransformer.Builder()
                .setMinScale(0.8f)
                .build());

    }

    public void printCarrusel(Carrusel carrusel){
        mainBean.setData(carrusel.getSlides());
        adapter.updateCarrusel(mainBean.getData());
        infiniteAdapter.notifyDataSetChanged();
//        onItemChanged(data.get(0));
    }

    private void onItemChanged(CarruselSlide item) {
        title.setText(item.getTitle());
    }

/*    public void getCategories(){


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
        super.onDestroy();
    }


    @Override
    public void onCurrentItemChanged(@Nullable RecyclerView.ViewHolder viewHolder, int adapterPosition) {
        int positionInDataSet = infiniteAdapter.getRealPosition(adapterPosition);
        onItemChanged(mainBean.getData().get(positionInDataSet));
    }


}
