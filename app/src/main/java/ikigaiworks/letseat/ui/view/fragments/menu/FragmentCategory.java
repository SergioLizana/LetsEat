package ikigaiworks.letseat.ui.view.fragments.menu;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

import ikigaiworks.letseat.R;
import ikigaiworks.letseat.model.Category;
import ikigaiworks.letseat.model.beans.ProductsBean;
import ikigaiworks.letseat.ui.presenters.menu.CategoryFragmentPresenterImpl;
import ikigaiworks.letseat.ui.view.activities.ProductTabActivity_;
import ikigaiworks.letseat.ui.view.adapters.MenuAdapter;


@EFragment(R.layout.fragment_menu_categorias)
public class FragmentCategory extends Fragment {

    CategoryFragmentPresenterImpl presenter;
    ArrayList<Category> data;
    MenuAdapter adapter;

    @ViewById(R.id.recyler_menu_categorias)
    protected RecyclerView recyclerView;
    @ViewById(R.id.progressbar)
    protected ProgressBar progressBar;
    @Bean
    ProductsBean pBean;

    @AfterViews
    void init() {
        presenter = new CategoryFragmentPresenterImpl();
        presenter.setMenuFragment(this);

        if (pBean.getData()==null){
            data = new ArrayList<>();
            progressBar.setVisibility(View.VISIBLE);
            presenter.launchOperation();
        }else{
            data = pBean.getData();
        }
        configureRecyclerView();
    }

    private void configureRecyclerView() {
        adapter = new MenuAdapter(data, getActivity().getApplicationContext(), presenter);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    public void printData(ArrayList<Category> data) {
        this.data = data;
        pBean.setData(data);
        adapter.updateItem(data);
        progressBar.setVisibility(View.GONE);
    }

    public void launchDetail(Category category) {
        pBean.setCategory(category);
        Intent intent = ProductTabActivity_.intent(this).get();
        startActivity(intent);
    }

    public void onError(){
        Toast.makeText(getActivity().getApplicationContext(),getString(R.string.generic_error),Toast.LENGTH_LONG).show();
        if(getActivity()!=null) {
            getActivity().finish();
        }
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
