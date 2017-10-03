package ikigaiworks.letseat.ui.view.fragments.menu;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

import ikigaiworks.letseat.R;
import ikigaiworks.letseat.model.Category;
import ikigaiworks.letseat.ui.presenters.menu.MenuFragmentPresenterImpl;
import ikigaiworks.letseat.ui.view.activities.ProductTabActivity_;
import ikigaiworks.letseat.ui.view.adapters.MenuAdapter;



@EFragment(R.layout.fragment_menu_categorias)
public class FragmentMenu extends Fragment {

    MenuFragmentPresenterImpl presenter;
    ArrayList<Category> data;
    MenuAdapter adapter;
    @ViewById(R.id.recyler_menu_categorias)
    protected RecyclerView recyclerView;

    @AfterViews
    void init() {
        data = new ArrayList<>();
        presenter = new MenuFragmentPresenterImpl();
        presenter.setMenuFragment(this);
        configureRecyclerView();
        presenter.retrieveData();
    }

    private void configureRecyclerView(){
        adapter = new MenuAdapter(data, getActivity().getApplicationContext(), presenter);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    public void printData(ArrayList<Category> data){
        this.data = data;
        adapter.updateItem(data);
    }

    public void launchDetail(){
        Intent intent = ProductTabActivity_.intent(this).get();
        startActivity(intent);
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
