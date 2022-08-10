package com.wallace.viewpagerandroid;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class AlternativeViewContainer extends FrameLayout {
    protected View defaultView;
    protected View alternativeView;

    public AlternativeViewContainer(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        switch (getChildCount()) {
            case 0:
            case 1:
            case 2:
                defaultView = getChildAt(0);
                if(defaultView != null) {
                    defaultView.setVisibility(VISIBLE);
                }
                alternativeView = getChildAt(1);

                if(alternativeView != null) {
                    alternativeView.setVisibility(GONE);
                }
                break;
        }
    }
}
