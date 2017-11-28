package ikigaiworks.letseat.manager;

import ikigaiworks.letseat.model.Category;
import ikigaiworks.letseat.model.ProductToCart;
import ikigaiworks.letseat.ui.presenters.Presenter;
import ikigaiworks.letseat.ui.presenters.login.LoginPresenter;

/**
 * Created by sergiolizanamontero on 21/9/17.
 */

public interface FirebaseManager {

    void getCategories(final Presenter.OperationCategories listener);
    void getSubCategories(Category category , final Presenter.OperationCategories listener);
    void getProductos(Category category ,final Presenter.OperationProducts listener);
    void getProducto(String idProducto, final Presenter.OperationProduct listener);
    void getFavorites(final Presenter.OperationFavorite listener);
    void addFavorite(ProductToCart productToCart);
    void removeFavorite(ProductToCart productToCart, final Presenter.OperationFavoriteDelete listener);
    void signUp(String email, String password, final LoginPresenter.SignIn listener);
    void login(String email, String password ,final LoginPresenter.Login listener);

}
