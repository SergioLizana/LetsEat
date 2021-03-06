package ikigaiworks.letseat.ui.presenters.menu;

import java.util.ArrayList;

import ikigaiworks.letseat.manager.FirebaseManagerImpl;
import ikigaiworks.letseat.model.Category;
import ikigaiworks.letseat.model.Producto;
import ikigaiworks.letseat.ui.presenters.Presenter;
import ikigaiworks.letseat.ui.view.fragments.menu.FragmentProductList;

/**
 * Created by sergiolizanamontero on 6/10/17.
 */

public class ProductListFragmentPresenterImpl implements Presenter, Presenter.OperationProducts, Presenter.OperationProduct {

    private FirebaseManagerImpl firebaseManager;
    private ArrayList<Producto> products;
    private FragmentProductList fragmentProductList;
    private Category categorySelected;

    public ProductListFragmentPresenterImpl(Category categorySelected) {
        this.categorySelected = categorySelected;
        firebaseManager = new FirebaseManagerImpl().getInstance();
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

    public void onClickEvent(Producto producto) {
        getProducto(producto.getReference());
    }

    @Override
    public void onProductsReceived(ArrayList<Producto> products) {
        fragmentProductList.printData(products);
    }

    @Override
    public void launchOperation() {
        firebaseManager.getProductos(categorySelected, this);
    }

    @Override
    public void onErrorOperation() {
        fragmentProductList.onError();
    }

    public void getProducto(String idProducto) {
        firebaseManager.getProducto(idProducto, this);
    }

    @Override
    public void onProductReceived(Producto producto) {
        fragmentProductList.showDialog(producto);
    }
}
