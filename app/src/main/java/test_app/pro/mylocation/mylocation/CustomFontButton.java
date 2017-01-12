package test_app.pro.mylocation.mylocation;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;

public class CustomFontButton extends Button {

    public CustomFontButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public CustomFontButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomFontButton(Context context) {
        super(context);
        init();
    }

    private void init() {
        if (!isInEditMode()) {
            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "caviar_dreams.ttf");
            setTypeface(tf);
        }
    }

}
