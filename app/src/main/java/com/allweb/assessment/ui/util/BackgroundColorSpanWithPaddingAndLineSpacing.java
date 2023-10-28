package com.allweb.assessment.ui.util;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.style.LineBackgroundSpan;
import android.widget.TextView;

public class BackgroundColorSpanWithPaddingAndLineSpacing implements LineBackgroundSpan {

    private static final String TAG =  "BackgroundColorSpanWithPaddingAndLineSpacing";
    private final TextView textView;
    private final int paddingSize;
    private final RectF rect;

    public BackgroundColorSpanWithPaddingAndLineSpacing(TextView textView, int paddingSize) {
        super();
        this.paddingSize = paddingSize;
        this.rect = new RectF();
        this.textView = textView;
    }

    @Override
    public void drawBackground(Canvas c, Paint p, int left, int right, int top, int baseline, int bottom, CharSequence text, int start, int end, int currentLineNumber) {
        final int textWidth = Math.round(p.measureText(text, start, end));
        final int paintColor = p.getColor();
        final int backgroundColor = textView.getHighlightColor();

//        final int recLeft = left - paddingSize / 2;
//        final int recTop = top - (currentLineNumber == 0 ? 20 : paddingSize / 4);
//        final int recRight = left + textWidth + paddingSize / 2;
//        final int recBottom = (int)(top + textView.getTextSize() + paddingSize / 2);

        final int recLeft = -20;
        final int recTop = -20;
        final int recRight = 70;
        final int recBottom = 75;

        rect.set(recLeft, recTop, recRight, recBottom);
        p.setColor(backgroundColor);
        c.drawRect(rect, p);
        p.setColor(paintColor);
    }
}