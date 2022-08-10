package com.wallace.viewpagerandroid;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class PageIndicatorDecoration extends RecyclerView.ItemDecoration {
    int colorActive;
    int colorInactive;
    int diameter;
    private final float paddingBetween;
    private final float paddingBottom;
    private final float radius = (diameter * PaintCursor.DP) / 2F;
    private final Paint paint;

    public PageIndicatorDecoration(int colorActive, int colorInactive) {
        this.colorActive = colorActive;
        this.colorInactive = colorInactive;
        this.diameter = 8;
        this.paddingBetween = 8 * PaintCursor.DP;
        this.paddingBottom = 18 * PaintCursor.DP + this.radius;

        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
    }

    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDrawOver(c, parent, state);

        int itemCount = parent.getAdapter().getItemCount();
        PageIndicatorDecoration.PaintCursor cursor = getIndicatorCursor(parent);
        float spacing = paddingBetween + (2F * radius);
        float firstX = cursor.x - spacing * (itemCount - 1) / 2F;

        cursor.x = firstX;
        for (int i = 0; i < itemCount; i++) {
            draw(c, cursor, colorInactive);
            cursor.x += spacing;
        }

        LinearLayoutManager layoutManager = (LinearLayoutManager) parent.getLayoutManager();
        if (layoutManager != null) {
            int activePosition = layoutManager.findFirstCompletelyVisibleItemPosition();
            if (activePosition != RecyclerView.NO_POSITION) {
                cursor.x = firstX + spacing * activePosition;
                draw(c, cursor, colorActive);
            }
        }
    }

    private PaintCursor getIndicatorCursor(RecyclerView parent) {
        PaintCursor cursor = new PaintCursor(0F, 0F);
        cursor.x = parent.getWidth() / 2F;
        cursor.y = parent.getHeight() - paddingBottom;
        return cursor;
    }

    private void draw(Canvas c, PaintCursor cursor, int color) {
        paint.setColor(color);
        c.drawCircle(cursor.x, cursor.y, radius, paint);
    }

    static class PaintCursor {
        static float DP = Resources.getSystem().getDisplayMetrics().density;
        public float x = 0F;
        public float y = 0F;

        public PaintCursor(float x, float y) {
            this.x = x;
            this.y = y;
        }
    }
}
