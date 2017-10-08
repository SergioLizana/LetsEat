package ikigaiworks.letseat.ui.presenters.menu;

import java.util.ArrayList;

import ikigaiworks.letseat.manager.FirebaseManagerImpl;
import ikigaiworks.letseat.model.Category;
import ikigaiworks.letseat.ui.presenters.Presenter;
import ikigaiworks.letseat.ui.view.fragments.menu.FragmentCategory;

/**
 * Created by sergiolizanamontero on 21/9/17.
 */

public class CategoryFragmentPresenterImpl implements Presenter {

    private FirebaseManagerImpl firebaseManager;
    private ArrayList<Category> categories;
    private FragmentCategory fragmentCategory;

   public CategoryFragmentPresenterImpl(){
        firebaseManager = new FirebaseManagerImpl();
    }

    public FragmentCategory getFragmentCategory(){
        return fragmentCategory;
    }
    public void setMenuFragment(FragmentCategory fragmentCategory){
        this.fragmentCategory = fragmentCategory;
    }


    @Override
    public void retrieveData() {
        firebaseManager.getCategories(this);
    }

    @Override
    public void printData(Object object) {
        categories = (ArrayList<Category>) object;
        fragmentCategory.printData(categories);
    }

    public void onClickEvent(Category c){
        fragmentCategory.launchDetail(c);
    }
}
