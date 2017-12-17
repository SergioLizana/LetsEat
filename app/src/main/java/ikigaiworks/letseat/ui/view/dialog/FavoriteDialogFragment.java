package ikigaiworks.letseat.ui.view.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import ikigaiworks.letseat.R;

import static ikigaiworks.letseat.app.LetsEatConstants.TAG_SAVE_FAV;

/**
 * Created by sergiolizanamontero on 24/10/17.
 */

@EFragment(R.layout.dialog_fav)
public class FavoriteDialogFragment extends DialogFragment implements DialogInterface.OnClickListener {

    private View view;
    AlertDialog.Builder builder;

    @ViewById(R.id.fav_name)
    EditText mFavName;

    private DialogInterface.OnDismissListener listener;

    @Override
    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        builder = new AlertDialog.Builder(getActivity());
        final LayoutInflater inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.dialog_fav, null);
        builder.setView(view);
        builder.setTitle("Really?");
        builder.setMessage("Are you sure?");
        Fragment f = getTargetFragment();
        //null should be your on click listener
        builder.setPositiveButton("OK", this);
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_CANCELED, getActivity().getIntent());
                dialog.dismiss();
            }
        });
        return builder.create();
    }

    public DialogInterface.OnDismissListener getListener() {
        return listener;
    }

    public void setListener(DialogInterface.OnDismissListener listener) {
        this.listener = listener;
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
    }


    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        if (!mFavName.getText().toString().isEmpty()) {
            Fragment f = getTargetFragment();
            Intent intentData = new Intent();
            intentData.putExtra(TAG_SAVE_FAV, mFavName.getText().toString());
            f.onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, intentData);
            dialogInterface.dismiss();
        } else {
            getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_CANCELED, getActivity().getIntent());
            Toast.makeText(getActivity(), getString(R.string.error_validation_fav_name), Toast.LENGTH_LONG).show();
            dialogInterface.dismiss();
        }
    }
}