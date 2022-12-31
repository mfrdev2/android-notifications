package com.example.android_notification.ui;

import android.content.Context;
import android.text.InputFilter;
import android.text.InputType;
import android.text.SpannableStringBuilder;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class PasswordView extends TextInputLayout {
    EditText editText;


    boolean passwordInvisible = true;

    public PasswordView(@NonNull Context context) {
        super(context);
        System.out.println(getEditText());

    }

    public PasswordView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        System.out.println(getEditText());
    }

    public PasswordView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        System.out.println(getEditText());
    }

    @Override
    public void setEndIconOnClickListener(@Nullable OnClickListener endIconOnClickListener) {
        setPasswordInVisibleMode(this);
        onEndIconClickOfPasswordInput(null);
        super.setEndIconOnClickListener(endIconOnClickListener);
    }

    public InputFilter getFilter() {
        return filter;
    }

    private void onEndIconClickOfPasswordInput(View view) {

        if (editText == null) {
            return;
        }
        int start = editText.getSelectionStart();
        int end = editText.getSelectionStart();
        if (passwordInvisible) {
            setPasswordVisibleMode(this);
        } else {
            setPasswordInVisibleMode(this);
        }
        editText.setSelection(start, end);
        passwordInvisible = !passwordInvisible;
    }


    private void setPasswordVisibleMode(TextInputLayout tilPassword) {
        if (editText == null) {
            return;
        }
        editText.setInputType(InputType.TYPE_CLASS_TEXT);
        editText.setTransformationMethod(null);
        //  textDirectionChange();
    }

    private void setPasswordInVisibleMode(TextInputLayout tilPassword) {
        if (editText == null) {
            return;
        }
        editText.setInputType(InputType.TYPE_CLASS_TEXT
                | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        editText.setTransformationMethod(new StarPasswordTransformationMethod());

        //   textDirectionChange();
    }


    private final InputFilter filter = (source, start, end, dest, dstart, dend) -> {
        final String ALLOW_CHARACTER = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ&#!@#$%^*?.:;={}[]%\\')(,+-/_\"0123456789";

        if (source instanceof SpannableStringBuilder) {
            SpannableStringBuilder sourceAsSpannableBuilder = (SpannableStringBuilder) source;
            for (int i = end - 1; i >= start; i--) {
                char currentChar = source.charAt(i);
                //!Character.isLetterOrDigit(currentChar) && !Character.isSpaceChar(currentChar)
                if (!ALLOW_CHARACTER.contains(Character.toString(currentChar))) {
                    sourceAsSpannableBuilder.delete(i, i + 1);
                }
            }
            return source;
        } else {
            StringBuilder filteredStringBuilder = new StringBuilder();
            for (int i = start; i < end; i++) {
                char currentChar = source.charAt(i);
                //Character.isLetterOrDigit(currentChar) || Character.isSpaceChar(currentChar)
                if (ALLOW_CHARACTER.contains(Character.toString(currentChar))) {
                    filteredStringBuilder.append(currentChar);
                }
            }
            return filteredStringBuilder.toString();
        }
    };


    public static class StarPasswordTransformationMethod extends PasswordTransformationMethod {
        @Override
        public CharSequence getTransformation(CharSequence source, View view) {
            return new PasswordCharSequence(source);
        }

        private class PasswordCharSequence implements CharSequence {
            private CharSequence mSource;

            public PasswordCharSequence(CharSequence source) {
                mSource = source; // Store char sequence
            }

            public char charAt(int index) {
                return '*'; // This is the important part
            }

            public int length() {
                return mSource.length(); // Return default
            }

            public CharSequence subSequence(int start, int end) {
                return mSource.subSequence(start, end); // Return default
            }
        }
    }
}
