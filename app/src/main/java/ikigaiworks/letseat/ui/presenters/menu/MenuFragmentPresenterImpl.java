package ikigaiworks.letseat.ui.presenters.menu;

import android.content.Intent;
import android.util.Log;

import org.androidannotations.annotations.Bean;

import java.util.ArrayList;

import ikigaiworks.letseat.manager.FirebaseManagerImpl;
import ikigaiworks.letseat.model.Category;
import ikigaiworks.letseat.model.Menu;
import ikigaiworks.letseat.ui.presenters.Presenter;
import ikigaiworks.letseat.ui.view.activities.ProductTabActivity;
import ikigaiworks.letseat.ui.view.activities.ProductTabActivity_;
import ikigaiworks.letseat.ui.view.fragments.menu.FragmentMenu;
import ikigaiworks.letseat.ui.view.fragments.product.ProductFragment;

/**
 * Created by sergiolizanamontero on 21/9/17.
 */

public class MenuFragmentPresenterImpl implements Presenter {

    private FirebaseManagerImpl firebaseManager;
    private ArrayList<Category> categories;
    private FragmentMenu fragmentMenu;

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
        fragmentMenu.printData(categories);
    }

    public void onClickEvent(Category c){
        fragmentMenu.launchDetail();
    }
}
