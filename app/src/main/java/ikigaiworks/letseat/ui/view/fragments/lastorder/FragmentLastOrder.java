package ikigaiworks.letseat.ui.view.fragments.lastorder;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

import ikigaiworks.letseat.R;
import ikigaiworks.letseat.model.Category;
import ikigaiworks.letseat.model.LastOrder;
import ikigaiworks.letseat.model.beans.ProductsBean;
import ikigaiworks.letseat.ui.presenters.lastorder.LastOrderPresenterImpl;
import ikigaiworks.letseat.ui.presenters.menu.CategoryFragmentPresenterImpl;
import ikigaiworks.letseat.ui.view.activities.CartActivity_;
import ikigaiworks.letseat.ui.view.activities.ProductTabActivity_;
import ikigaiworks.letseat.ui.view.adapters.LastOrderAdapter;
import ikigaiworks.letseat.ui.view.adapters.MenuAdapter;
import ikigaiworks.letseat.utils.CartUtils;

/**
 * Created by sergiolizanamontero on 30/11/17.
 */

@EFragment(R.layout.fragment_menu_categorias)
public class FragmentLastOrder extends Fragment {
    ArrayList<LastOrder> data;
    LastOrderAdapter adapter;

    @ViewById(R.id.recyler_menu_categorias)
    protected RecyclerView recyclerView;
    @ViewById(R.id.progressbar)
    protected ProgressBar progressBar;
    LastOrderPresenterImpl presenter;



    @AfterViews
    void init() {
        data = new ArrayList<>();
        presenter = new LastOrderPresenterImpl(this);
        data = presenter.getOrders();
        configureRecyclerView();

    }

    private void configureRecyclerView(){
        adapter = new LastOrderAdapter(data,getActivity(),presenter);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    public void goToCart(LastOrder order){


        CartUtils.deleteCart();
        CartUtils.updateCart(order.getProductToCart());
        Intent intent = CartActivity_.intent(this).get();
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

}
