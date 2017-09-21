package ikigaiworks.letseat.ui.presenters.main;

import ikigaiworks.letseat.model.Carrusel;
import ikigaiworks.letseat.ui.presenters.Presenter;
import ikigaiworks.letseat.ui.view.fragments.main.FragmentMain;

/**
 * Created by sergiolizanamontero on 21/9/17.
 */

public class MainFragmentPresenterImpl extends Presenter implements MainFragmentPresenter  {

    Carrusel carrusel;
    FragmentMain fragmentMain;

    public MainFragmentPresenterImpl(){

    }

    @Override
    public void retrieveSlides() {
        if (carrusel != null){
            printSlides(carrusel);
        }else{
            carrusel = Carrusel.newInstance();
            printSlides(carrusel);
        }

    }

    @Override
    public void setMainFragmentActivity(FragmentMain fragmentMain) {
        this.fragmentMain = fragmentMain;
    }

    @Override
    public void printSlides(Carrusel carrusel) {
           if (fragmentMain != null) {
               fragmentMain.printCarrusel(carrusel);
           }
    }
}
