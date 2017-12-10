package ikigaiworks.letseat.ui.view.fragments.payment;

import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

import ikigaiworks.letseat.R;
import ikigaiworks.letseat.model.ProductToCart;
import ikigaiworks.letseat.ui.view.adapters.SuccessAdapter;
import ikigaiworks.letseat.ui.view.animation.LikeButtonView;
import ikigaiworks.letseat.ui.view.customview.PriceTextView;
import ikigaiworks.letseat.utils.CartUtils;

/**
 * Created by sergiolizanamontero on 28/11/17.
 */

@EFragment(R.layout.fragment_payment_complete)
public class FragmentCompletePayment extends Fragment {

    @ViewById(R.id.date)
    TextView mDateText;
    @ViewById(R.id.ticket_cart)
    RecyclerView mRecyclerView;
    @ViewById(R.id.total)
    PriceTextView mPriceTextView;
    @ViewById(R.id.items)
    RelativeLayout mItemsToShow;
    @ViewById(R.id.ocultar)
    TextView ocultar;
    @FragmentArg
    ArrayList<ProductToCart> products;
    SuccessAdapter adapter;
    @ViewById(R.id.fav_button)
    LikeButtonView likeButtonView;

    @Click(R.id.ocultar)
    void onClickOcultar(){
        if (mItemsToShow.getVisibility() == View.GONE){
            ocultar.setText(getString(R.string.hide));
            mItemsToShow.setVisibility(View.VISIBLE);
        }else{
            mItemsToShow.setVisibility(View.GONE);
            ocultar.setText(getString(R.string.show));
        }
    }

    @AfterViews
    void init(){
        configureRecyclerView();
        mPriceTextView.setNumber(CartUtils.getCartPrice(products));
    }

    void configureRecyclerView(){
        adapter = new SuccessAdapter(products, getActivity().getApplicationContext());
        mRecyclerView.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
