package com.wallace.viewpagerandroid;

import android.content.Context;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewContainer extends AlternativeViewContainer {
    RecyclerView recyclerView;
    Point mCenter;
    Point mInitialTouch;

    public RecyclerViewContainer(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs, 0);
        setClipChildren(false);
        mCenter = new Point();
        mInitialTouch = new Point();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        try {
           recyclerView = (RecyclerView) super.defaultView;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mCenter.x = w / 2;
        mCenter.y = h / 2;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            mInitialTouch.x = (int) event.getX();
            mInitialTouch.y = (int) event.getY();
            event.offsetLocation(mCenter.x - event.getX(), mCenter.y - event.getY());
        } else {
            event.offsetLocation(mCenter.x - event.getX(), mCenter.y - event.getY());
        }
        return recyclerView.dispatchTouchEvent(event);
    }
}
