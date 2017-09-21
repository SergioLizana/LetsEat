package ikigaiworks.letseat.manager;

import java.util.List;

import ikigaiworks.letseat.model.Category;
import ikigaiworks.letseat.model.Producto;
import ikigaiworks.letseat.ui.presenters.Presenter;

/**
 * Created by sergiolizanamontero on 21/9/17.
 */

public interface FirebaseManager {

    void getCategories(Presenter presenter);
    void getSubCategories(Category category , Presenter presenter);
    void getProductos(Category category ,Presenter presenter);
    void getProducto(int idProducto, Presenter presenter);

}
