package ikigaiworks.letseat.ui.presenters.menu;

import java.util.ArrayList;
import java.util.List;

import ikigaiworks.letseat.manager.FirebaseManagerImpl;
import ikigaiworks.letseat.model.Category;
import ikigaiworks.letseat.model.Producto;
import ikigaiworks.letseat.ui.presenters.Presenter;
import ikigaiworks.letseat.ui.view.fragments.menu.FragmentCategory;
import ikigaiworks.letseat.ui.view.fragments.menu.FragmentProductList;

/**
 * Created by sergiolizanamontero on 6/10/17.
 */

public class ProductListFragmentPresenterImpl implements Presenter {

    private FirebaseManagerImpl firebaseManager;
    private ArrayList<Producto> products;
    private FragmentProductList fragmentProductList;
    private Category categorySelected;

    public ProductListFragmentPresenterImpl(Category categorySelected){
        this.categorySelected = categorySelected;
        firebaseManager = new FirebaseManagerImpl();
    }

    public ArrayList<Producto> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Producto> products) {
        this.products = products;
    }

    public FragmentProductList getFragmentProductList() {
        return fragmentProductList;
    }

    public void setFragmentProductList(FragmentProductList fragmentProductList) {
        this.fragmentProductList = fragmentProductList;
    }

    @Override
    public void retrieveData() {
        firebaseManager.getProductos(categorySelected,this);
    }

    @Override
    public void printData(Object object) {
        fragmentProductList.printData((List<Producto>)object);
    }
}
