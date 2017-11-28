package ikigaiworks.letseat.ui.view.fragments.payment;
import android.support.v4.app.Fragment;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;

import java.util.ArrayList;

import ikigaiworks.letseat.R;
import ikigaiworks.letseat.model.ProductToCart;
import ikigaiworks.letseat.ui.view.activities.CartActivity;

/**
 * Created by sergiolizanamontero on 26/11/17.
 */

@EFragment(R.layout.fragment_payment)
public class FragmentPayment extends Fragment {

    @FragmentArg
    ArrayList<ProductToCart> productToCart;

    @AfterViews
    void init(){
    }

    @Click
    void pay(){
        FragmentCompletePayment payment = FragmentCompletePayment_.builder().products(productToCart).build();
        ((CartActivity)getActivity()).replaceFragment(payment,R.id.content_activity_cart,"paymentComplete",false,true);
    }
}
