package ikigaiworks.letseat.ui.presenters;

import java.util.ArrayList;
import java.util.List;

import ikigaiworks.letseat.model.Category;
import ikigaiworks.letseat.model.Producto;

/**
 * Created by sergiolizanamontero on 21/9/17.
 */

public interface Presenter {
    interface OperationCategories{
        void onCategoriesReceived(ArrayList<Category> categories);
    }
    interface OperationProducts{
        void onProductsReceived(ArrayList<Producto> products);
    }
    interface OperationProduct{
        void onProductReceived(Producto producto);
    }
    void launchOperation();
}
