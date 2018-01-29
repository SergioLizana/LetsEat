package ikigaiworks.letseat.ui.view.fragments.payment;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import ikigaiworks.letseat.R;
import ikigaiworks.letseat.app.BaseActivity;
import ikigaiworks.letseat.app.LetsEatConstants;
import ikigaiworks.letseat.manager.NotificationBackgroundService;
import ikigaiworks.letseat.model.FavOrder;
import ikigaiworks.letseat.model.ProductToCart;
import ikigaiworks.letseat.ui.view.adapters.SuccessAdapter;
import ikigaiworks.letseat.ui.view.animation.LikeButtonView;
import ikigaiworks.letseat.ui.view.customview.PriceTextView;
import ikigaiworks.letseat.ui.view.dialog.FavoriteDialogFragment;
import ikigaiworks.letseat.ui.view.dialog.FavoriteDialogFragment_;
import ikigaiworks.letseat.utils.CartUtils;
import ikigaiworks.letseat.utils.FavoriteUtils;
import ikigaiworks.letseat.widget.WidgetProvider;

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
    @FragmentArg
    boolean isFav;

    public static final int DIALOG_FRAGMENT = 1;

    @Click(R.id.ocultar)
    void onClickOcultar() {
        if (mItemsToShow.getVisibility() == View.GONE) {
            ocultar.setText(getString(R.string.hide));
            mItemsToShow.setVisibility(View.VISIBLE);
        } else {
            mItemsToShow.setVisibility(View.GONE);
            ocultar.setText(getString(R.string.show));
        }
    }

    @AfterViews
    void init() {
        configureRecyclerView();
        mPriceTextView.setNumber(CartUtils.getCartPrice(products));
        if (isFav) {
            likeButtonView.showAnimation();
        }
        Intent intent = new Intent(Intent.ACTION_SYNC, null, getActivity(), NotificationBackgroundService.class);
        getActivity().startService(intent);

    }

    void configureRecyclerView() {
        adapter = new SuccessAdapter(products, getActivity().getApplicationContext());
        mRecyclerView.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
    }

    @Click(R.id.fav_button)
    void onClick() {
        if(!((Activity) getActivity()).isFinishing()) {
            if (!isFav) {

                FragmentManager fm = getFragmentManager();
                FavoriteDialogFragment dialogFragment = FavoriteDialogFragment_.builder().build();
                dialogFragment.setAllowEnterTransitionOverlap(true);
                dialogFragment.setAllowReturnTransitionOverlap(true);
                dialogFragment.setTargetFragment(this, DIALOG_FRAGMENT);
                dialogFragment.show(fm, "Sample Fragment");


            } else {
                likeButtonView.showAnimation();
                Toast.makeText(getActivity().getApplicationContext(), getString(R.string.error_validation_is_fav), Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case DIALOG_FRAGMENT:

                if (resultCode == Activity.RESULT_OK) {
                    String name = data.getStringExtra(LetsEatConstants.TAG_SAVE_FAV);
                    LinkedHashMap<String, FavOrder> favOrder = new LinkedHashMap<>();
                    favOrder.put(name, new FavOrder(new Date(), products, name));
                    FavoriteUtils.addToFav(favOrder);
                    FavoriteUtils.getFavList();
                    likeButtonView.showAnimation();
                    isFav = true;
                    Toast.makeText(getActivity().getApplicationContext()
                            , getString(R.string.fav_added), Toast.LENGTH_LONG).show();
                } else if (resultCode == Activity.RESULT_CANCELED) {
                    isFav = false;
                }

                break;
        }
    }

    @Click(R.id.goToHome)
    void goToMainScreen() {
        ((BaseActivity) getActivity()).goToMainScreen();
    }
}
