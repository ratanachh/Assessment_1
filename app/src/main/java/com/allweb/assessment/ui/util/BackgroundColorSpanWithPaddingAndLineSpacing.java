package com.allweb.assessment.ui.util;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.style.LineBackgroundSpan;
import android.widget.TextView;

import androidx.annotation.NonNull;

public class BackgroundColorSpanWithPaddingAndLineSpacing implements LineBackgroundSpan {

    private static final String TAG = "BackgroundColorSpanWithPaddingAndLineSpacing";
    private final TextView textView;
    private final int paddingSize;
    private final RectF rect;

    public BackgroundColorSpanWithPaddingAndLineSpacing(TextView textView, int paddingSize) {
        this.paddingSize = paddingSize;
        this.rect = new RectF();
        this.textView = textView;
    }

    @Override
    public void drawBackground(Canvas c, Paint p, int left, int right, int top, int baseline, int bottom, @NonNull CharSequence text, int start, int end, int lineNumber) {
        final int textWidth = Math.round(p.measureText(
                text,
                start,
                this.trimSpacePosition(textView.getText().toString(), end)
        ));
        final int paintColor = p.getColor();
        final Paint.FontMetrics metrics = p.getFontMetrics();

        final int rBottom = (int) (baseline + paddingSize + metrics.descent);
        final int rTop = top - paddingSize;
        final int rLeft = left - paddingSize;
        final int rRight = left + textWidth + paddingSize;

        // Draw the background
        rect.set(rLeft, rTop, rRight, rBottom);
        p.setColor(textView.getHighlightColor());
        c.drawRect(rect, p);
        p.setColor(paintColor);
    }

    private int trimSpacePosition(String text, int position) {
        if (text.charAt(position - 1) == ' ') {
            position--;
        }
        return position;
    }
}