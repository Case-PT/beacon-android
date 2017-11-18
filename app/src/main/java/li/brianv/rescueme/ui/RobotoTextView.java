package li.brianv.rescueme.ui;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 * Created by petar.petrov on 7/14/2017.
 */

public class RobotoTextView extends android.support.v7.widget.AppCompatTextView {
    public RobotoTextView(Context context) {
        super(context);
    }

    public RobotoTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RobotoTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setTypeface(Typeface tf, int style) {
        super.setTypeface(tf, style);
        switch (style) {
            case Typeface.BOLD:
                this.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto-Bold.ttf"));
                break;
            case Typeface.ITALIC:
                this.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto-Italic.ttf"));
                break;
            case Typeface.BOLD_ITALIC:
                this.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto-BoldItalic.ttf"));
                break;
            default:
                this.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto-Regular.ttf"));
        }
    }
}
