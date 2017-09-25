package ikigaiworks.letseat.ui.view.fragments.main.bean;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.InstanceState;

import java.util.ArrayList;

import ikigaiworks.letseat.model.Carrusel;
import ikigaiworks.letseat.model.CarruselSlide;

/**
 * Created by sergiolizanamontero on 23/9/17.
 */
@EBean(scope = EBean.Scope.Singleton)
public class MainBean {

    private ArrayList<CarruselSlide> data;


    @AfterInject
    void afterInject(){
        data = new ArrayList<CarruselSlide>();
    }

    public ArrayList<CarruselSlide> getData() {
        return data;
    }

    public void setData(ArrayList<CarruselSlide> data) {
        this.data = data;
    }
}
