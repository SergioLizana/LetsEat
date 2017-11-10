package ikigaiworks.letseat.ui.view.customview;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import ikigaiworks.letseat.R;
import ikigaiworks.letseat.utils.App;
import ikigaiworks.letseat.utils.CommonUtils;

/**
 * Created by sergiolizanamontero on 9/11/17.
 */
@EViewGroup(R.layout.cart_button_view)
public class CartFloatingButton extends RelativeLayout {

    @ViewById(R.id.cart_button_float)
    protected FloatingActionButton cartButton;
    @ViewById(R.id.item_number)
    protected TextView itemNumber;

    public CartFloatingButton(Context context) {
        super(context);
    }

    public CartFloatingButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CartFloatingButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CartFloatingButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void refreshCartCount() {
        itemNumber.setText(String.valueOf(CommonUtils.getCart().size()));
    }


}
