package ikigaiworks.letseat.ui.view.fragments.lastorder;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import ikigaiworks.letseat.R;
import ikigaiworks.letseat.model.FavOrder;
import ikigaiworks.letseat.model.LastOrder;
import ikigaiworks.letseat.ui.presenters.lastorder.LastOrderPresenterImpl;
import ikigaiworks.letseat.ui.view.activities.CartActivity_;
import ikigaiworks.letseat.ui.view.activities.MainActivity_;
import ikigaiworks.letseat.ui.view.adapters.LastOrderAdapter;
import ikigaiworks.letseat.utils.CartUtils;
import ikigaiworks.letseat.utils.FavoriteUtils;

import static ikigaiworks.letseat.app.LetsEatConstants.REQ_CODE_WIDGET_FLOW;

/**
 * Created by sergiolizanamontero on 30/11/17.
 */

@EFragment(R.layout.fragment_menu_categorias)
public class FragmentFavOrderList extends Fragment {
    LinkedHashMap<String, FavOrder> data;
    LastOrderAdapter adapter;


    @ViewById(R.id.recyler_menu_categorias)
    protected RecyclerView recyclerView;
    @ViewById(R.id.progressbar)
    protected ProgressBar progressBar;
    LastOrderPresenterImpl presenter;


    @AfterViews
    void init() {
        presenter = new LastOrderPresenterImpl(this);
        data = FavoriteUtils.getFavList();
        configureRecyclerView();

    }

    private void configureRecyclerView() {
        adapter = new LastOrderAdapter(data, getActivity(), presenter);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    public void goToCart(FavOrder order) {
        CartUtils.deleteCart();
        CartUtils.updateCart(order.getProducts());
        CartActivity_.intent(this)
                .isFav(true)
                .flags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                .startForResult(REQ_CODE_WIDGET_FLOW);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==REQ_CODE_WIDGET_FLOW){
           MainActivity_.intent(this).flags(Intent.FLAG_ACTIVITY_CLEAR_TOP).start();

        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
