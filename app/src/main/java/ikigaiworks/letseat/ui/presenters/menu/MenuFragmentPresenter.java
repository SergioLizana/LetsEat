package ikigaiworks.letseat.ui.presenters.menu;

import ikigaiworks.letseat.model.Menu;

/**
 * Created by sergiolizanamontero on 21/9/17.
 */

public interface MenuFragmentPresenter {
    void setMenuFragmentPresenter(MenuFragmentPresenter fragmentPresenter);
    void retrieveCategories(MenuFragmentPresenter fragmentPresenter);
    void printList(Menu menu);
}
