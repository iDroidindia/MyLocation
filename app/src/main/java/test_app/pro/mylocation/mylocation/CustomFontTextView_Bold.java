package test_app.pro.mylocation.mylocation;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by manmohan on 2/6/16.
 */
public class CustomFontTextView_Bold extends TextView {

    public CustomFontTextView_Bold(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public CustomFontTextView_Bold(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomFontTextView_Bold(Context context) {
        super(context);
        init();
    }

    private void init() {
        if (!isInEditMode()) {
            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "caviar_dreams_bold.ttf");
            setTypeface(tf);
        }
    }

}