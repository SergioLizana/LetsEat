package ikigaiworks.letseat.utils;

import android.content.Context;

import java.util.Arrays;
import java.util.List;

import ikigaiworks.letseat.R;

/**
 * Created by sergiolizanamontero on 17/8/17.
 */

public class ImageGallery {

    Context c;

    public ImageGallery(Context c) {
        this.c = c;
    }

    public List<Integer> getData() {
        return Arrays.asList(
                R.drawable.carta_restaurante,
                R.drawable.shop1,
                R.drawable.shop2,
                R.drawable.shop3,
                R.drawable.shop4,
                R.drawable.shop5,
                R.drawable.shop6);

    }


}