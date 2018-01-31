package ikigaiworks.letseat.ui.presenters;

import java.util.ArrayList;

import ikigaiworks.letseat.model.Category;
import ikigaiworks.letseat.model.ProductToCart;
import ikigaiworks.letseat.model.Producto;

/**
 * Created by sergiolizanamontero on 21/9/17.
 */

public interface Presenter {
    interface OperationCategories {
        void onCategoriesReceived(ArrayList<Category> categories);
        void onErrorOperation();
    }

    interface OperationProducts {
        void onProductsReceived(ArrayList<Producto> products);
        void onErrorOperation();
    }

    interface OperationProduct {
        void onProductReceived(Producto producto);
        void onErrorOperation();
    }

    void launchOperation();
}
