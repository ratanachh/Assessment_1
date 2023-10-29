package com.allweb.assessment.ui.util;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.TextPaint;
import android.text.style.BackgroundColorSpan;
import android.text.style.LineBackgroundSpan;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;

public class BackgroundColorSpanWithPaddingAndLineSpacing implements LineBackgroundSpan {

    private static final String TAG =  "BackgroundColorSpanWithPaddingAndLineSpacing";
    private final TextView textView;
    private final int paddingSize;
    private final RectF rect;

    public BackgroundColorSpanWithPaddingAndLineSpacing(TextView textView, int paddingSize) {
        this.paddingSize = paddingSize;
        this.rect = new RectF();
        this.textView = textView;
    }

    @Override
    public void drawBackground(Canvas c, Paint p, int left, int right, int top, int baseline, int bottom, CharSequence text, int start, int end, int lnum) {
        final int textWidth = Math.round(p.measureText(text, start, end));
        final int paintColor = p.getColor();

        Log.d(TAG, "drawBackground: " + baseline);

        final int rBottom =  baseline + paddingSize;
        // Draw the background
        rect.set(
                left - paddingSize,
                top - paddingSize / 2,
                left + textWidth + paddingSize,
                rBottom
        );
        p.setColor(textView.getHighlightColor());
        c.drawRect(rect, p);
        p.setColor(paintColor);
    }
}