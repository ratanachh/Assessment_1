package com.allweb.assessment.ui.home;

import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.allweb.assessment.databinding.FragmentHomeBinding;
import com.allweb.assessment.ui.util.BackgroundColorSpanWithPaddingAndLineSpacing;

public class HomeFragment extends Fragment {
  private static final int DEFAULT_PADDING = 6;

  private FragmentHomeBinding binding;

  public View onCreateView(
      @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

    binding = FragmentHomeBinding.inflate(inflater, container, false);
    View root = binding.getRoot();

    final var text = "Charlotte Perriand’s “La maison au bord de l’eau”";
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
    textView.setShadowLayer(padding /* radius */, 0, 0, 0 /* transparent */);
    textView.setText(text, TextView.BufferType.SPANNABLE);
    textView.setPadding(padding, padding, padding, padding);
    BackgroundColorSpanWithPaddingAndLineSpacing span =
            new BackgroundColorSpanWithPaddingAndLineSpacing(textView, padding);

    SpannableString spannableString = new SpannableString(text);
    spannableString.setSpan(span, 0, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

    textView.setText(spannableString);
  }

  public static String trimSingleLineStringWithLength(String text, Integer length) {
    text = text.replaceAll("\n", "");
    if (text.length()> length) {
      return text.substring(0, length);
    }
    return text;
  }
}
