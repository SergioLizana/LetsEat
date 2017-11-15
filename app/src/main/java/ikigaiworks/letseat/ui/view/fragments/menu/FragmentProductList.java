package ikigaiworks.letseat.ui.view.fragments.menu;

import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

import ikigaiworks.letseat.R;
import ikigaiworks.letseat.model.Category;
import ikigaiworks.letseat.model.Producto;
import ikigaiworks.letseat.ui.presenters.menu.ProductListFragmentPresenterImpl;
import ikigaiworks.letseat.ui.view.activities.ProductTabActivity;
import ikigaiworks.letseat.ui.view.adapters.ProductListAdapter;

/**
 * Created by sergiolizanamontero on 3/10/17.
 */

@EFragment(R.layout.fragment_product_list)
public class FragmentProductList extends Fragment {

    ProductListFragmentPresenterImpl presenter;

    @FragmentArg
    Category category;

    ProductListAdapter adapter;

    ArrayList<Producto> data;

    @ViewById(R.id.recycler_list_products)
    RecyclerView mRecyclerView;

    @AfterViews
    void init(){
        data = new ArrayList<>();
        presenter = new ProductListFragmentPresenterImpl(category);
        presenter.setFragmentProductList(this);
        configureRecyclerView();
        retrieveData();
    }

    void configureRecyclerView(){
        adapter = new ProductListAdapter(data, getActivity().getApplicationContext(), presenter);
        mRecyclerView.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
    }


    public void showDialog(Producto producto){

        ((ProductTabActivity)getActivity()).showDialogProductos(producto);
    }

    public void printData(ArrayList<Producto> data){
        this.data = data;
        adapter.updateItem(data);
    }


    private void retrieveData(){
        presenter.launchOperation();
    }
}
