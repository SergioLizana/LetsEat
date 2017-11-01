package ikigaiworks.letseat.manager;

import java.util.List;

import ikigaiworks.letseat.model.Category;
import ikigaiworks.letseat.model.Producto;
import ikigaiworks.letseat.ui.presenters.Presenter;

/**
 * Created by sergiolizanamontero on 21/9/17.
 */

public interface FirebaseManager {

    void getCategories(final Presenter.OperationCategories listener);
    void getSubCategories(Category category , final Presenter.OperationCategories listener);
    void getProductos(Category category ,final Presenter.OperationProducts listener);
    void getProducto(String idProducto, final Presenter.OperationProduct listener);

}
