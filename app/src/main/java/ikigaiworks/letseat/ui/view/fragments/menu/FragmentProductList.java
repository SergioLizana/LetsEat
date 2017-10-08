package ikigaiworks.letseat.ui.view.fragments.menu;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;

import java.util.List;

import ikigaiworks.letseat.R;
import ikigaiworks.letseat.model.Category;
import ikigaiworks.letseat.model.Producto;
import ikigaiworks.letseat.ui.presenters.menu.CategoryFragmentPresenterImpl;
import ikigaiworks.letseat.ui.presenters.menu.ProductListFragmentPresenterImpl;

/**
 * Created by sergiolizanamontero on 3/10/17.
 */

@EFragment(R.layout.fragment_product_list)
public class FragmentProductList extends Fragment {

    ProductListFragmentPresenterImpl presenter;
    @FragmentArg
    Category category;

    @AfterViews
    void init(){
        retrieveData();
    }


    public void printData(List<Producto> productoList){

    }

    private void retrieveData(){
        presenter = new ProductListFragmentPresenterImpl(category);
        presenter.setFragmentProductList(this);
        presenter.retrieveData();
    }
}
