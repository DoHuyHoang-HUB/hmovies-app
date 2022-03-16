package com.codingtok.hmovies.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.codingtok.hmovies.R;

public class ButtonIcon extends LinearLayout  {
    private static final String TAG = "ButtonIcon";

    private static final LayoutParams DEFAULT_LAYOUT_PARAMS =
            new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

    private static final int NULL_RESOURCE_ID = -1;

    private ImageView imageView;
    private TextView textView;
    private int iconResId;
    private int iconTintResId;
    private String text;


    public ButtonIcon(Context context) {
        super(context);
    }

    public ButtonIcon(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ButtonIcon(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ButtonIcon(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        final TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.ButtonIcon);

        iconResId = ta.getResourceId(R.styleable.ButtonIcon_icon, R.drawable.ic_add);
        iconResId = ta.getResourceId(R.styleable.ButtonIcon_iconTint, com.google.android.material.R.color.design_default_color_surface);

    }
}
