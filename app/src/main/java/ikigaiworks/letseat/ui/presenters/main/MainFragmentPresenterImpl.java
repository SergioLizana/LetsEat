package ikigaiworks.letseat.ui.presenters.main;

import android.util.Log;

import org.androidannotations.annotations.EBean;

import ikigaiworks.letseat.model.Carrusel;
import ikigaiworks.letseat.model.CarruselSlide;
import ikigaiworks.letseat.ui.presenters.Presenter;
import ikigaiworks.letseat.ui.view.fragments.main.FragmentMain;

/**
 * Created by sergiolizanamontero on 21/9/17.
 */

public class MainFragmentPresenterImpl  implements Presenter  {

    Carrusel carrusel;
    FragmentMain fragmentMain;

    public MainFragmentPresenterImpl(){

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
    public void retrieveData() {
        if (carrusel != null){
            printData(carrusel);
        }else{
            carrusel = Carrusel.newInstance(fragmentMain.getContext());
            printData(carrusel);
        }

    }

    @Override
    public void printData(Object object) {
        if (fragmentMain != null) {
            fragmentMain.printCarrusel((Carrusel)object);
        }
    }
}
