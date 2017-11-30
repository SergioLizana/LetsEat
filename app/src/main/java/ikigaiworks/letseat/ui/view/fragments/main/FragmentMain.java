package ikigaiworks.letseat.ui.view.fragments.main;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.yarolegovich.discretescrollview.DiscreteScrollView;
import com.yarolegovich.discretescrollview.InfiniteScrollAdapter;
import com.yarolegovich.discretescrollview.Orientation;
import com.yarolegovich.discretescrollview.transform.ScaleTransformer;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import ikigaiworks.letseat.R;
import ikigaiworks.letseat.app.BaseActivity;
import ikigaiworks.letseat.model.Carrusel;
import ikigaiworks.letseat.model.CarruselSlide;
import ikigaiworks.letseat.ui.presenters.main.MainFragmentPresenterImpl;
import ikigaiworks.letseat.ui.view.adapters.CarruselAdapter;
import ikigaiworks.letseat.ui.view.fragments.main.bean.MainBean;
import ikigaiworks.letseat.utils.DiscreteScrollViewOptions;


@EFragment(R.layout.fragment_main)
public class FragmentMain extends Fragment implements DiscreteScrollView.OnItemChangedListener{


    @ViewById(R.id.picker)
    DiscreteScrollView scrollView;
    @ViewById(R.id.titleCarrusel)
    TextView title;

    MainFragmentPresenterImpl presenter;

    private InfiniteScrollAdapter infiniteAdapter;
    private CarruselAdapter adapter;


    @Bean
    MainBean mainBean;


    @AfterViews
    protected void init(){
        presenter = new MainFragmentPresenterImpl(getActivity().getApplicationContext(),this);
        presenter.setFragmentMain(this);
        getActivity().setTitle("Taste Bakery");
        initCarruselConf();
    }

    private void initCarruselConf(){
        adapter = new CarruselAdapter(mainBean.getData(),getActivity().getApplicationContext(),presenter);
        infiniteAdapter = InfiniteScrollAdapter.wrap(adapter);
        buildScrollView();
        presenter.retrieveSlides();
    }

    private void buildScrollView(){
        scrollView.setOrientation(Orientation.HORIZONTAL);
        scrollView.addOnItemChangedListener(this);
        scrollView.setAdapter(infiniteAdapter);
        scrollView.setItemTransitionTimeMillis(DiscreteScrollViewOptions.getTransitionTime());
        scrollView.setItemTransformer(new ScaleTransformer.Builder()
                .setMinScale(0.8f)
                .build());

    }

    public void printCarrusel(Carrusel carrusel){
        mainBean.setData(carrusel.getSlides());
        adapter.updateCarrusel(mainBean.getData());
        infiniteAdapter.notifyDataSetChanged();
    }

    private void onItemChanged(CarruselSlide item) {
        title.setText(item.getTitle());
    }

    public void onClickEvent(CarruselSlide c) {
        ((BaseActivity)getActivity()).manageIntents(c.getId());

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


    @Override
    public void onCurrentItemChanged(@Nullable RecyclerView.ViewHolder viewHolder, int adapterPosition) {
        int positionInDataSet = infiniteAdapter.getRealPosition(adapterPosition);
        onItemChanged(mainBean.getData().get(positionInDataSet));
    }


}
