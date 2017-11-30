package ikigaiworks.letseat.ui.view.fragments.cart;

import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

import ikigaiworks.letseat.R;
import ikigaiworks.letseat.app.BaseActivity;
import ikigaiworks.letseat.model.ProductToCart;
import ikigaiworks.letseat.ui.view.activities.CartActivity;
import ikigaiworks.letseat.ui.view.adapters.CartAdapter;
import ikigaiworks.letseat.ui.view.customview.PriceTextView;
import ikigaiworks.letseat.ui.view.fragments.payment.FragmentPayment;
import ikigaiworks.letseat.ui.view.fragments.payment.FragmentPayment_;
import ikigaiworks.letseat.utils.CartUtils;

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
    @ViewById
    Button pay;


    @AfterViews
    void init(){
        productToCarts = CartUtils.getCart();
        configureRecyclerView();
        refreshCartFooter();
    }

    void configureRecyclerView(){
        adapter = new CartAdapter(productToCarts, getActivity().getApplicationContext(),this);
        mRecyclerView.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
    }

    public void removeItem(ProductToCart productToCart){
    int position = CartUtils.getCartItemPosition(productToCart);
        if(position>=0) {
            ((CartAdapter) mRecyclerView.getAdapter()).removeItem(position);
            refreshCartFooter();
        }
    }

    @Click(R.id.pay)
    void paid(){
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            FragmentPayment payment = FragmentPayment_.builder().productToCart(CartUtils.getCart()).build();
            ((CartActivity) getActivity()).replaceFragment(payment, R.id.content_activity_cart, "payment", false, true);
        }else{
            Toast.makeText(getActivity().getApplicationContext(),"Es necesario Autenticarse en Lets Eat! para realizar un pedido",Toast.LENGTH_LONG).show();
            ((BaseActivity)getActivity()).manageIntents(R.id.login);
        }
    }

    public void addToQuantity(ProductToCart productToCart){
        CartUtils.addToQuantity(productToCart);
        adapter.updateItems(CartUtils.getCart());
        refreshCartFooter();
    }

    public void removeFromQuantity(ProductToCart productToCart){
        CartUtils.removeFromQuantity(productToCart);
        adapter.updateItems(CartUtils.getCart());
        refreshCartFooter();
    }


    public void refreshCartFooter(){
        totalPrice.setNumber(CartUtils.getCartPrice());
        totalAmountProducts.setText(String.valueOf(CartUtils.getCartSize()));
    }

}
