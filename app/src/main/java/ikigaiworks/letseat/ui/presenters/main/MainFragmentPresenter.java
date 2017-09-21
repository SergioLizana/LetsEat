package ikigaiworks.letseat.ui.presenters.main;

import ikigaiworks.letseat.model.Carrusel;
import ikigaiworks.letseat.ui.view.fragments.main.FragmentMain;

/**
 * Created by sergiolizanamontero on 21/9/17.
 */

public interface MainFragmentPresenter {

    void retrieveSlides();
    void setMainFragmentActivity(FragmentMain fragmentMain);
    void printSlides(Carrusel carrusel);



}
