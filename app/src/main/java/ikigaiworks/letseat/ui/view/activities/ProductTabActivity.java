package ikigaiworks.letseat.ui.view.activities;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ikigaiworks.letseat.R;
import ikigaiworks.letseat.app.BaseActivity;
import ikigaiworks.letseat.model.Category;
import ikigaiworks.letseat.model.beans.ProductsBean;
import ikigaiworks.letseat.ui.view.fragments.menu.FragmentProductList;
import ikigaiworks.letseat.ui.view.fragments.menu.FragmentProductList_;

@EActivity(R.layout.activity_product_tab)
public class ProductTabActivity extends BaseActivity {

    @ViewById(R.id.tabs)
    protected TabLayout tabLayout;
    @ViewById(R.id.viewpager)
    protected ViewPager viewPager;
    @Bean
    protected ProductsBean pBean;

    @AfterViews
    void init(){
        addToolbar();
        setToolbarTitle("Product");
        setToolbarBackgroundColor(R.color.colorPrimaryDark);
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        if(pBean.getCategory().getSubtype()== null){
            Fragment frag = FragmentProductList_.builder().category(pBean.getCategory()).build();
            adapter.addFragment(frag,pBean.getCategory().getName());
        }else{
           Map<String,Category> subCat = pBean.getCategory().getSubtype();
            for(Category cat: subCat.values()){
                Fragment frag = FragmentProductList_.builder().category(cat).build();
                adapter.addFragment(frag,cat.getName());
            }

        }
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
