package ikigaiworks.letseat.ui.view.fragments.main.bean;

import android.os.Parcelable;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EBean;

import java.util.ArrayList;

import ikigaiworks.letseat.model.CarruselSlide;

/**
 * Created by sergiolizanamontero on 23/9/17.
 */
@EBean(scope = EBean.Scope.Singleton)
public class MainBean {

    private ArrayList<CarruselSlide> data;
    private Parcelable mListState;

    @AfterInject
    void afterInject() {
        data = new ArrayList<>();
    }

    public ArrayList<CarruselSlide> getData() {
        return data;
    }

    public void setData(ArrayList<CarruselSlide> data) {
        this.data = data;
    }

    public Parcelable getmListState() {
        return mListState;
    }

    public void setmListState(Parcelable mListState) {
        this.mListState = mListState;
    }




}
