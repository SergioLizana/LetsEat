package ikigaiworks.letseat.ui.presenters.menu;

import org.androidannotations.annotations.Bean;

import java.util.ArrayList;

import ikigaiworks.letseat.manager.FirebaseManagerImpl;
import ikigaiworks.letseat.model.Category;
import ikigaiworks.letseat.model.Menu;
import ikigaiworks.letseat.ui.presenters.Presenter;
import ikigaiworks.letseat.ui.view.fragments.menu.FragmentMenu;

/**
 * Created by sergiolizanamontero on 21/9/17.
 */

public class MenuFragmentPresenterImpl implements Presenter {

    FirebaseManagerImpl firebaseManager;
    ArrayList<Category> categories;

    FragmentMenu fragmentMenu;

   public MenuFragmentPresenterImpl(){
        firebaseManager = new FirebaseManagerImpl();
    }

    public FragmentMenu getFragmentMenu(){
        return fragmentMenu;
    }
    public void setMenuFragment(FragmentMenu fragmentMenu){
        this.fragmentMenu = fragmentMenu;
    }


    @Override
    public void retrieveData() {
        firebaseManager.getCategories(this);
    }

    @Override
    public void printData(Object object) {
        categories = (ArrayList<Category>) object;

    }
}
