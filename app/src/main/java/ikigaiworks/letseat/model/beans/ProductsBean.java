package ikigaiworks.letseat.model.beans;

import org.androidannotations.annotations.EBean;

import java.util.ArrayList;

import ikigaiworks.letseat.model.Category;

/**
 * Created by sergiolizanamontero on 4/10/17.
 */

@EBean(scope = EBean.Scope.Singleton)
public class ProductsBean {

    private ArrayList<Category> data;
    private Category category;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public ArrayList<Category> getData() {
        return data;
    }

    public void setData(ArrayList<Category> data) {
        this.data = data;
    }
}
