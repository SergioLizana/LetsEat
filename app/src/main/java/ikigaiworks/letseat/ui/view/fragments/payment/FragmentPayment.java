package ikigaiworks.letseat.ui.view.fragments.payment;
import android.support.v4.app.Fragment;
import android.widget.TextView;

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
    @ViewById(R.id.total_prize_payment)
    PriceTextView total;

    @AfterViews
    void init(){
        total.setNumber(CartUtils.getCartPrice(productToCart));
    }

    @Click
    void pay(){
        FragmentCompletePayment payment = FragmentCompletePayment_.builder().products(productToCart).build();
        ((CartActivity)getActivity()).replaceFragment(payment,R.id.content_activity_cart,"paymentComplete",false,true);
    }

}
