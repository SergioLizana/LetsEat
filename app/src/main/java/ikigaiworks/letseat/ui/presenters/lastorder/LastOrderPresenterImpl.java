package ikigaiworks.letseat.ui.presenters.lastorder;

import ikigaiworks.letseat.model.FavOrder;
import ikigaiworks.letseat.ui.view.fragments.lastorder.FragmentFavOrderList;

/**
 * Created by sergiolizanamontero on 30/11/17.
 */

public class LastOrderPresenterImpl {

    FragmentFavOrderList frag;

    public LastOrderPresenterImpl(FragmentFavOrderList frag) {
        this.frag = frag;
    }

    public void onClickEvent(FavOrder favOrder) {
        frag.goToCart(favOrder);
    }
}
