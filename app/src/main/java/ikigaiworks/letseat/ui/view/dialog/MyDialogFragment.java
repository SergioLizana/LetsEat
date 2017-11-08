package ikigaiworks.letseat.ui.view.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import ikigaiworks.letseat.R;
import ikigaiworks.letseat.model.ProductToCart;
import ikigaiworks.letseat.model.Producto;
import ikigaiworks.letseat.ui.view.customview.PriceTextView;
import ikigaiworks.letseat.utils.CommonUtils;

/**
 * Created by sergiolizanamontero on 24/10/17.
 */

@EFragment(R.layout.product_detail)
public class MyDialogFragment extends DialogFragment{
    private View view;
    @FragmentArg
    Producto mProduct;
    @ViewById(R.id.image_product)
    ImageView mProductImage;
    @ViewById(R.id.descProduct)
    TextView mProductDesc;
    @ViewById(R.id.title_product)
    TextView mProductTitle;
    @ViewById(R.id.spinner_extras)
    Spinner mExtras;
    @ViewById(R.id.addToCart)
    Button mCartButton;
    @ViewById(R.id.product_prize)
    PriceTextView mProductPrize;
    @ViewById(R.id.loadingImage)
    ProgressBar loader;
    @ViewById(R.id.space2)
    View line1;
    @ViewById(R.id.line1)
    View line2;
    @ViewById(R.id.extraText)
    TextView extraText;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final LayoutInflater inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.product_detail, null);
        builder.setView(view);
        return builder.create();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @AfterViews
    void calledAfterViewInjection() {
        loadElements();
    }

    private void loadElements(){
        loadImage();
        mProductDesc.setText(mProduct.getDescription());
        mProductTitle.setText(mProduct.getName());
        mProductPrize.setText(String.valueOf(mProduct.getPrice()));
        if (mProduct.getExtra()!=null) {
            loadSpinner();
        }else{
            line1.setVisibility(View.INVISIBLE);
            line2.setVisibility(View.INVISIBLE);
            extraText.setVisibility(View.INVISIBLE);
            mExtras.setVisibility(View.INVISIBLE);
        }
    }

    private void loadImage(){
        Glide.with(view.getContext())
                .load(mProduct.getImage())
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        loader.setVisibility(View.INVISIBLE);
                        mProductImage.setVisibility(View.VISIBLE);
                        return false;
                    }
                })
                .into(mProductImage);
    }

    private void loadSpinner(){
        ArrayList<String> extras = CommonUtils.getExtraAsString(mProduct.getExtra());
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item,extras);
        mExtras.setAdapter(adapter);
    }

    @Click(R.id.addToCart)
    void addToCart(){
        if (mExtras.getVisibility() == View.GONE){
            CommonUtils.addToCart(CommonUtils.parseProductToCart(mProduct,""));
        }else{
            CommonUtils.addToCart(CommonUtils.parseProductToCart(mProduct,mExtras.getSelectedItem().toString()));
        }
        ArrayList<ProductToCart> arrayList = CommonUtils.getCart();
        Log.d("ads","sdfa");
    }


}