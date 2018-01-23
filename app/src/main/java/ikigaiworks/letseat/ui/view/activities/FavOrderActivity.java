package ikigaiworks.letseat.ui.view.activities;

import android.content.Intent;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

import ikigaiworks.letseat.R;
import ikigaiworks.letseat.app.BaseActivity;
import ikigaiworks.letseat.model.FavOrder;
import ikigaiworks.letseat.ui.view.fragments.lastorder.FragmentFavOrderList;
import ikigaiworks.letseat.ui.view.fragments.lastorder.FragmentFavOrderList_;
import ikigaiworks.letseat.utils.CartUtils;

import static ikigaiworks.letseat.app.LetsEatConstants.REQ_CODE_WIDGET_FLOW;
import static ikigaiworks.letseat.widget.WidgetProvider.WIDGET_ROW_ONCLICK;

/**
 * Created by sergiolizanamontero on 30/11/17.
 */

@EActivity(R.layout.activity_menu)
public class FavOrderActivity extends BaseActivity {

    FragmentFavOrderList lastOrderFragment;

    @AfterViews
    void init() {
        addNavigationDrawer();
        setToolbarTitle(getString(R.string.title_last_orders));
        setToolbarBackgroundColor(R.color.colorPrimaryDark);
       if(getIntent()!=null && getIntent().getExtras()!=null && getIntent().getExtras().get(WIDGET_ROW_ONCLICK)!=null){
           launchOrder((FavOrder) getIntent().getExtras().get(WIDGET_ROW_ONCLICK));
       }else{
           launchList();
       }
    }

    void launchList(){
        lastOrderFragment = FragmentFavOrderList_.builder().build();
        replaceFragment(lastOrderFragment, R.id.content_menu, getString(R.string.tag_name_menu), false, true);
    }

    void launchOrder(FavOrder favOrder){
        CartUtils.deleteCart();
        CartUtils.updateCart(favOrder.getProducts());
        CartActivity_.intent(this).isFav(true).flags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                .startForResult(REQ_CODE_WIDGET_FLOW);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==REQ_CODE_WIDGET_FLOW){
            MainActivity_.intent(this)
                    .flags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK)
                    .start();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


}
