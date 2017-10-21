package ikigaiworks.letseat.ui.view.fragments.menu;

import android.databinding.BindingAdapter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import ikigaiworks.letseat.R;
import ikigaiworks.letseat.model.Category;
import ikigaiworks.letseat.model.Producto;
import ikigaiworks.letseat.ui.presenters.menu.CategoryFragmentPresenterImpl;
import ikigaiworks.letseat.ui.presenters.menu.ProductListFragmentPresenterImpl;
import ikigaiworks.letseat.ui.view.adapters.MenuAdapter;
import ikigaiworks.letseat.ui.view.adapters.MenuListAdapter;

/**
 * Created by sergiolizanamontero on 3/10/17.
 */

@EFragment(R.layout.fragment_product_list)
public class FragmentProductList extends Fragment {

    ProductListFragmentPresenterImpl presenter;

    @FragmentArg
    Category category;

    MenuListAdapter adapter;

    ArrayList<Producto> data;

    @ViewById(R.id.recycler_list_products)
    RecyclerView mRecyclerView;

    @AfterViews
    void init(){
        data = new ArrayList<>();
        configureRecyclerView();
        retrieveData();
    }

    void configureRecyclerView(){
        adapter = new MenuListAdapter(data, getActivity().getApplicationContext(), presenter);
        mRecyclerView.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
    }




    public void printData(ArrayList<Producto> data){
        this.data = data;
        adapter.updateItem(data);
    }

    private void retrieveData(){
        presenter = new ProductListFragmentPresenterImpl(category);
        presenter.setFragmentProductList(this);
        presenter.retrieveData();
    }
}
