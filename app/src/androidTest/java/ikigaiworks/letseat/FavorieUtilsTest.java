package ikigaiworks.letseat;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;

import ikigaiworks.letseat.model.FavOrder;
import ikigaiworks.letseat.model.ProductToCart;
import ikigaiworks.letseat.utils.FavoriteUtils;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class FavorieUtilsTest {


    @Test
    public void addFavTest() {
        LinkedHashMap<String, FavOrder> favItem = new LinkedHashMap<>();
        ProductToCart productToCart10 = new ProductToCart();
        productToCart10.setQuantity(4);
        productToCart10.setCartId(1);
        productToCart10.setDiscount(0.0);
        productToCart10.setExtra("Zumo de Naranja");
        productToCart10.setExtraVisibility(0);
        productToCart10.setImage("https://firebasestorage.googleapis.com/v0/b/letseat-7fc44.appspot.com/o/products%2F04.jpg?alt=media&token=92fde2ca-07ff-4498-b5d6-a61de8711d38");
        productToCart10.setName("Bikini");
        productToCart10.setPrice(3.5);
        productToCart10.setReference("4");

        ProductToCart productToCart11 = new ProductToCart();
        productToCart11.setQuantity(4);
        productToCart11.setCartId(1);
        productToCart11.setDiscount(0.0);
        productToCart11.setExtra("Zumo de Naranja");
        productToCart11.setExtraVisibility(0);
        productToCart11.setImage("https://firebasestorage.googleapis.com/v0/b/letseat-7fc44.appspot.com/o/products%2F04.jpg?alt=media&token=92fde2ca-07ff-4498-b5d6-a61de8711d38");
        productToCart11.setName("Bikini");
        productToCart11.setPrice(3.5);
        productToCart11.setReference("4");

        ProductToCart productToCart12 = new ProductToCart();
        productToCart12.setQuantity(4);
        productToCart12.setCartId(1);
        productToCart12.setDiscount(0.0);
        productToCart12.setExtra("Zumo de Naranja");
        productToCart12.setExtraVisibility(0);
        productToCart12.setImage("https://firebasestorage.googleapis.com/v0/b/letseat-7fc44.appspot.com/o/products%2F04.jpg?alt=media&token=92fde2ca-07ff-4498-b5d6-a61de8711d38");
        productToCart12.setName("Bikini");
        productToCart12.setPrice(3.5);
        productToCart12.setReference("4");

        ArrayList<ProductToCart> productToCarts = new ArrayList<>();
        productToCarts.add(productToCart10);
        productToCarts.add(productToCart11);
        productToCarts.add(productToCart12);
        FavOrder order = new FavOrder(new Date(), productToCarts, "test");
        favItem.put("test",order);
        FavoriteUtils.addToFav(favItem);

        LinkedHashMap<String, FavOrder> favs = FavoriteUtils.getFavList();
        assertTrue(FavoriteUtils.getFavList().containsKey("test"));
    }

    @Test
    public void getFavListTest() {
        LinkedHashMap<String, FavOrder> favItem = new LinkedHashMap<>();
        ProductToCart productToCart10 = new ProductToCart();
        productToCart10.setQuantity(4);
        productToCart10.setCartId(1);
        productToCart10.setDiscount(0.0);
        productToCart10.setExtra("Zumo de Naranja");
        productToCart10.setExtraVisibility(0);
        productToCart10.setImage("https://firebasestorage.googleapis.com/v0/b/letseat-7fc44.appspot.com/o/products%2F04.jpg?alt=media&token=92fde2ca-07ff-4498-b5d6-a61de8711d38");
        productToCart10.setName("Bikini");
        productToCart10.setPrice(3.5);
        productToCart10.setReference("4");

        ProductToCart productToCart11 = new ProductToCart();
        productToCart11.setQuantity(4);
        productToCart11.setCartId(1);
        productToCart11.setDiscount(0.0);
        productToCart11.setExtra("Zumo de Naranja");
        productToCart11.setExtraVisibility(0);
        productToCart11.setImage("https://firebasestorage.googleapis.com/v0/b/letseat-7fc44.appspot.com/o/products%2F04.jpg?alt=media&token=92fde2ca-07ff-4498-b5d6-a61de8711d38");
        productToCart11.setName("Bikini");
        productToCart11.setPrice(3.5);
        productToCart11.setReference("4");

        ProductToCart productToCart12 = new ProductToCart();
        productToCart12.setQuantity(4);
        productToCart12.setCartId(1);
        productToCart12.setDiscount(0.0);
        productToCart12.setExtra("Zumo de Naranja");
        productToCart12.setExtraVisibility(0);
        productToCart12.setImage("https://firebasestorage.googleapis.com/v0/b/letseat-7fc44.appspot.com/o/products%2F04.jpg?alt=media&token=92fde2ca-07ff-4498-b5d6-a61de8711d38");
        productToCart12.setName("Bikini");
        productToCart12.setPrice(3.5);
        productToCart12.setReference("4");

        ArrayList<ProductToCart> productToCarts = new ArrayList<>();
        productToCarts.add(productToCart10);
        productToCarts.add(productToCart11);
        productToCarts.add(productToCart12);
        FavOrder order = new FavOrder(new Date(), productToCarts, "test");
        favItem.put("test",order);
        FavoriteUtils.addToFav(favItem);

        assertNotNull(FavoriteUtils.getFavList());
    }

    @Test
    public void getFavByName() {
        assertNotNull(FavoriteUtils.getFavByName("test"));
    }


}
