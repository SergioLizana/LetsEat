package ikigaiworks.letseat.ui.view.customview;

import android.content.Context;
import android.util.AttributeSet;

import java.text.DecimalFormat;

/**
 * Created by sergiolizanamontero on 22/10/17.
 */

public class PriceTextView extends android.support.v7.widget.AppCompatTextView {

    public PriceTextView(Context context) {
        super(context);
    }

    public PriceTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PriceTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        DecimalFormat df = new DecimalFormat("#,##0.00€");
        CharSequence finalText;
        if(text!=null && !text.equals("")) {
            finalText = df.format(Double.parseDouble(text.toString()));
        }else{
            finalText = text;
        }
        super.setText(finalText, type);
    }

    public void setNumber(double number){
        super.setText(String.valueOf(number));
    }
}
