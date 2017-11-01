package ikigaiworks.letseat.ui.view.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

import ikigaiworks.letseat.R;
import ikigaiworks.letseat.model.Producto;

/**
 * Created by sergiolizanamontero on 24/10/17.
 */

@EFragment
public class MyDialogFragment extends DialogFragment {
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
        //some code
    }

}