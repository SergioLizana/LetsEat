package ikigaiworks.letseat.ui.view.fragments.payment;

import android.support.v4.app.Fragment;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

import ikigaiworks.letseat.R;
import ikigaiworks.letseat.model.ProductToCart;
import ikigaiworks.letseat.ui.view.activities.CartActivity;
import ikigaiworks.letseat.ui.view.customview.PriceTextView;
import ikigaiworks.letseat.utils.CartUtils;

/**
 * Created by sergiolizanamontero on 26/11/17.
 */

@EFragment(R.layout.fragment_payment)
public class FragmentPayment extends Fragment {

    @FragmentArg
    ArrayList<ProductToCart> productToCart;
    @FragmentArg
    boolean isFav;
    @ViewById(R.id.total_prize_payment)
    PriceTextView total;

    @AfterViews
    void init() {
        total.setNumber(CartUtils.getCartPrice(productToCart));
    }

    @Click
    void pay() {
        FragmentCompletePayment payment = FragmentCompletePayment_.builder().isFav(isFav).products(productToCart).build();
        ((CartActivity) getActivity()).replaceFragment(payment, R.id.content_activity_cart, getString(R.string.tag_name_payment_complete), false, true);
    }

}
