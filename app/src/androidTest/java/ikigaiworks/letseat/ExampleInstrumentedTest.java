package ikigaiworks.letseat;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import ikigaiworks.letseat.model.ProductToCart;
import ikigaiworks.letseat.utils.CartUtils;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("ikigaiworks.letseat", appContext.getPackageName());
    }

    @Test
    public void addCartTest() throws Exception{
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
        CartUtils.addToCart(productToCart10);

        assertEquals(true,CartUtils.getCartSize()>0);
    }

    @Test
    public void deleteCart() throws Exception{
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
        CartUtils.addToCart(productToCart10);
        CartUtils.deleteCart();

        assertEquals(true,CartUtils.getCartSize() == 0);
    }

    @Test
    public void updateCart() throws Exception{
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

        ProductToCart productToCart12= new ProductToCart();
        productToCart12.setQuantity(4);
        productToCart12.setCartId(1);
        productToCart12.setDiscount(0.0);
        productToCart12.setExtra("Zumo de Naranja");
        productToCart12.setExtraVisibility(0);
        productToCart12.setImage("https://firebasestorage.googleapis.com/v0/b/letseat-7fc44.appspot.com/o/products%2F04.jpg?alt=media&token=92fde2ca-07ff-4498-b5d6-a61de8711d38");
        productToCart12.setName("Bikini");
        productToCart12.setPrice(3.5);
        productToCart12.setReference("4");

        ArrayList<ProductToCart> productToCarts  = new ArrayList<>();
        productToCarts.add(productToCart10);
        productToCarts.add(productToCart11);
        productToCarts.add(productToCart12);

        CartUtils.updateCart(productToCarts);

        assertThat(1, is(equalTo(1)));
    }


}
