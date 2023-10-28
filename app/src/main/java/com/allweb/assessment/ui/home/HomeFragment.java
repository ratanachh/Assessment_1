package com.allweb.assessment.ui.home;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.LineBackgroundSpan;
import android.text.style.LineHeightSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.allweb.assessment.databinding.FragmentHomeBinding;
import com.allweb.assessment.ui.util.BackgroundColorSpanWithPaddingAndLineSpacing;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HomeFragment extends Fragment {
  private static final int DEFAULT_PADDING = 6;

  private FragmentHomeBinding binding;

  public View onCreateView(
      @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

    binding = FragmentHomeBinding.inflate(inflater, container, false);
    View root = binding.getRoot();

//    final var text = "Charlotte Perriand’s “La maison au bord de l’eau”";
    final var text = "ext which can This is the text which can \n use for test assessment!";
    displayMultilineTextWithHighLight(this.binding.textView, text.toUpperCase(), DEFAULT_PADDING);
    return root;
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    binding = null;
  }
  public static void displayMultilineTextWithHighLight(final TextView textView, String text, Integer padding) {
    text = trimSingleLineStringWithLength(text, 150);
    textView.setText(text, TextView.BufferType.SPANNABLE);
    textView.setPadding(padding, padding, padding, padding);

    String finalText = text;
    textView.post(() -> {
      var spannable = new SpannableString(textView.getText());
      LineBackgroundSpan bgSpan = (canvas, paint, left, right, top, baseline, bottom, textBreak, start, end, lineNumber) -> {
        var rect = new RectF();
        int textWidth = Math.round(paint.measureText(finalText, start, end));
        int paintColor = paint.getColor();
        int backgroundColor = textView.getHighlightColor();

//        final int recLeft = left - paddingSize / 2;
//        final int recTop = top - (currentLineNumber == 0 ? 20 : paddingSize / 4);
//        final int recRight = left + textWidth + paddingSize / 2;
//        final int recBottom = (int)(top + textView.getTextSize() + paddingSize / 2);

        int recLeft = 0;
        int recTop = 0;
        int recRight = 0;
        int recBottom = 0;

        rect.set(recLeft, recTop, recRight, recBottom);
        paint.setColor(paintColor);
        canvas.drawRect(rect, paint);
      };
      spannable.setSpan(bgSpan, 0, textView.getText().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
      spannable.setSpan();
      textView.setText(spannable);
    });
  }

  public static String trimSingleLineStringWithLength(String text, Integer length) {
    text = text.replaceAll("\n", "");
    if (text.length()> length) {
      return text.substring(0, length);
    }
    return text;
  }
}
