package ikigaiworks.letseat.ui.view.fragments.cart;

import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

import ikigaiworks.letseat.R;
import ikigaiworks.letseat.model.ProductToCart;
import ikigaiworks.letseat.ui.view.adapters.CartAdapter;
import ikigaiworks.letseat.ui.view.adapters.ProductListAdapter;
import ikigaiworks.letseat.ui.view.customview.PriceTextView;
import ikigaiworks.letseat.utils.CommonUtils;

/**
 * Created by sergiolizanamontero on 12/11/17.
 */

@EFragment(R.layout.fragment_cart_list)
public class FragmentCartList extends Fragment  {

    CartAdapter adapter;
    ArrayList<ProductToCart> productToCarts;
    @ViewById(R.id.cart_list)
    RecyclerView mRecyclerView;
    @ViewById(R.id.total_prize)
    PriceTextView totalPrice;
    @ViewById(R.id.amount_products)
    TextView totalAmountProducts;


    @AfterViews
    void init(){
        productToCarts = CommonUtils.getCart();
        configureRecyclerView();
        totalPrice.setNumber(CommonUtils.getCartPrice());
        totalAmountProducts.setText(String.valueOf(CommonUtils.getCart().size()));
    }

    void configureRecyclerView(){
        adapter = new CartAdapter(productToCarts, getActivity().getApplicationContext(),this);
        mRecyclerView.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
    }

    public void removeItem(ProductToCart productToCart){
    int position = CommonUtils.getCartItemPosition(productToCart);
        if(position>=0) {
            ((CartAdapter) mRecyclerView.getAdapter()).removeItem(position);
        }
    }
}
