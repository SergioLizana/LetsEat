package ikigaiworks.letseat.manager;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseNetworkException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import ikigaiworks.letseat.app.FirebaseCommon;
import ikigaiworks.letseat.app.LetsEatConstants;
import ikigaiworks.letseat.model.Category;
import ikigaiworks.letseat.model.Menu;
import ikigaiworks.letseat.model.ProductToCart;
import ikigaiworks.letseat.model.Producto;
import ikigaiworks.letseat.ui.presenters.Presenter;
import ikigaiworks.letseat.ui.presenters.login.LoginPresenter;
import ikigaiworks.letseat.utils.CartUtils;

/**
 * Created by sergiolizanamontero on 21/9/17.
 */
public class FirebaseManagerImpl implements FirebaseManager {

    private static final String TAG = "FirebaseManager";
    public static FirebaseManagerImpl firebaseManagerImpl;


    public static FirebaseManagerImpl getInstance() {
        if (firebaseManagerImpl == null)
        {
            firebaseManagerImpl = new FirebaseManagerImpl();
        }
        return firebaseManagerImpl;
    }

    @Override
    public void getCategories(final Presenter.OperationCategories listener) {
        final ArrayList<Category> categoryList = new ArrayList<>();
        DatabaseReference myRef = FirebaseCommon.getFirebaseDatabase().getReference("CATEGORIES");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    Category category = postSnapshot.getValue(Category.class);
                    categoryList.add(category);
                }
                listener.onCategoriesReceived(categoryList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("test", "on cancelled");
            }
        });

    }

    @Override
    public void getSubCategories(Category category, final Presenter.OperationCategories listener) {

    }

    @Override
    public void getProductos(Category category, final Presenter.OperationProducts listener) {
        DatabaseReference menu =  FirebaseCommon.getFirebaseDatabase().getReference("MENU").child(category.getReference());
        menu.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Menu menu = dataSnapshot.getValue(Menu.class);
                listener.onProductsReceived(CartUtils.getProductByMap(menu.getProducts()));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("error",databaseError.toString());
            }
        });
    }

    @Override
    public void getProducto(String idProducto, final Presenter.OperationProduct listener) {
        DatabaseReference menu =  FirebaseCommon.getFirebaseDatabase().getReference("PRODUCTS").child(idProducto);
        menu.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Producto producto = dataSnapshot.getValue(Producto.class);
                listener.onProductReceived(producto);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("error",databaseError.toString());
            }
        });
    }

    @Override
    public void getFavorites(Presenter.OperationFavorite listener) {
    }

    @Override
    public void addFavorite(ProductToCart productToCart) {

        /*DatabaseReference lastOrders =  FirebaseCommon.getFirebaseDatabase().getReference("FAVORITES").child("orders").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        ProductToCart productToCart1 = new ProductToCart("1",1,"TEST",20.0,15.0,"092190","COLACAO","NFJSNDJNS");
        HashMap<String,ProductToCart> productInCart = new HashMap<>();
        productInCart.put("0",productToCart1);
        productInCart.put("1",productToCart1);
        productInCart.put("2",productToCart1);
        productInCart.put("3",productToCart1);

        LastOrder order1 = new LastOrder(new Date(),productInCart);
        HashMap<String,LastOrder> lastOrders2 = new HashMap<String,LastOrder>();
        lastOrders2.put("2",order1);

        LastOrders orders = new LastOrders();
        orders.setOrders(lastOrders2);

        OrderModel model = new OrderModel();
        HashMap<String,LastOrders> orderModel = new HashMap<String,LastOrders>();
        orderModel.put(FirebaseCommon.getFirebaseAuth().getCurrentUser().getUid(),orders);
        model.setOrders(orderModel);
        Map<String, Object> updates = new HashMap<String, Object>();
        updates.put("orders",orders);
        lastOrders.updateChildren(updates, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {

            }
        });

        lastOrders.setValue(model);*/
    }

    @Override
    public void removeFavorite(ProductToCart productToCart, Presenter.OperationFavoriteDelete listener) {

    }

    @Override
    public void signUp(String email, String password, final LoginPresenter.SignIn listener) {

        try {
            FirebaseCommon.getFirebaseAuth().createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        listener.signInOK();
                    } else {
                        try {
                            throw task.getException();
                        } catch (FirebaseAuthWeakPasswordException e) {
                            listener.signInKO(LetsEatConstants.SIGNIN_ERROR_WEAK_PASSWORD_CODE);
                        } catch (FirebaseAuthInvalidCredentialsException e) {
                            listener.signInKO(LetsEatConstants.SIGNIN_ERROR_INVALID_EMAIL_CODE);
                        } catch (FirebaseAuthUserCollisionException e) {
                            listener.signInKO(LetsEatConstants.SIGNIN_ERROR_USER_EXIST_CODE);
                        } catch (FirebaseAuthInvalidUserException e) {
                            listener.signInKO(LetsEatConstants.LOGIN_ERROR_USER_CODE);
                        } catch (FirebaseNetworkException e) {
                            listener.signInKO(LetsEatConstants.ERROR_UNEXPECTED_CODE);
                        } catch (FirebaseException e){
                            listener.signInKO(LetsEatConstants.SIGNIN_ERROR_WEAK_PASSWORD_CODE);
                        } catch (Exception e) {
                            listener.signInKO(LetsEatConstants.ERROR_UNEXPECTED_CODE);
                        }
                    }
                }
            });
        }catch (IllegalArgumentException e){
            listener.signInKO(LetsEatConstants.LOGIN_ERROR_PASSWORD_CODE);
        } catch (Exception e){
            listener.signInKO(LetsEatConstants.ERROR_UNEXPECTED_CODE);
        }
    }

    @Override
    public void login(String email, String password, final LoginPresenter.Login listener) {

        try {
            FirebaseCommon.getFirebaseAuth().signInWithEmailAndPassword(email
                    , password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {


                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "signInWithEmail:success");
                                FirebaseUser user = FirebaseCommon.getFirebaseAuth().getCurrentUser();
                                listener.loginOK();
                            } else {
                                try {
                                    throw task.getException();
                                } catch (FirebaseAuthInvalidUserException e) {
                                    listener.loginKO(LetsEatConstants.LOGIN_ERROR_INVALID_CREDENTIALS_CODE);
                                } catch (FirebaseAuthInvalidCredentialsException e) {
                                    listener.loginKO(LetsEatConstants.LOGIN_ERROR_INVALID_CREDENTIALS_CODE);
                                } catch (FirebaseNetworkException e) {
                                    listener.loginKO(LetsEatConstants.ERROR_UNEXPECTED_CODE);
                                } catch (Exception e) {
                                    listener.loginKO(LetsEatConstants.ERROR_UNEXPECTED_CODE);
                                }

                            }
                        }
                    });
        } catch (IllegalArgumentException e) {
            listener.loginKO(LetsEatConstants.LOGIN_ERROR_PASSWORD_CODE);
        } catch (Exception e){
            listener.loginKO(LetsEatConstants.ERROR_UNEXPECTED_CODE);
        }
    }

}
