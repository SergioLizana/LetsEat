package ikigaiworks.letseat.ui.view.fragments.menu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;

import butterknife.Unbinder;
import ikigaiworks.letseat.R;
import ikigaiworks.letseat.ui.presenters.menu.MenuFragmentPresenterImpl;

/**
 * Created by sergiolizanamontero on 21/9/17.
 */

@EFragment(R.layout.fragment_menu_categorias)
public class FragmentMenu extends Fragment {

    MenuFragmentPresenterImpl presenter;
    @AfterViews
    void init(){
        presenter = new MenuFragmentPresenterImpl();
        presenter.setMenuFragment(this);
        presenter.retrieveData();
    }

    public static FragmentMenu newInstance(){
        FragmentMenu fragmentMenu = new FragmentMenu();
        return fragmentMenu;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
