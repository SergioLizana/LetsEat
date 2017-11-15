package ikigaiworks.letseat.ui.presenters.main;

import android.content.Context;
import android.util.Log;

import ikigaiworks.letseat.model.Carrusel;
import ikigaiworks.letseat.model.CarruselSlide;
import ikigaiworks.letseat.ui.view.fragments.main.FragmentMain;

/**
 * Created by sergiolizanamontero on 21/9/17.
 */

public class MainFragmentPresenterImpl  implements MainFragmentPresenter  {

    Carrusel carrusel;
    FragmentMain fragmentMain;
    Context c;

    public MainFragmentPresenterImpl(Context c, FragmentMain fragmentMain){
        this.fragmentMain = fragmentMain;
        this.c = c;

    }

    public Carrusel getCarrusel() {
        return carrusel;
    }

    public void setCarrusel(Carrusel carrusel) {
        this.carrusel = carrusel;
    }

    public FragmentMain getFragmentMain() {
        return fragmentMain;
    }

    public void setFragmentMain(FragmentMain fragmentMain) {
        this.fragmentMain = fragmentMain;
    }

    public void onClickEvent(CarruselSlide c){
        Log.d("Onclick","Onclick");
    }

    @Override
    public void retrieveSlides() {
        if (carrusel != null){
            printSlides(carrusel);
        }else{
            carrusel = Carrusel.newInstance(fragmentMain.getContext());
            printSlides(carrusel);
        }

    }

    @Override
    public void printSlides(Carrusel carrusel) {
        if (fragmentMain != null) {
            fragmentMain.printCarrusel(carrusel);
        }
    }
}
