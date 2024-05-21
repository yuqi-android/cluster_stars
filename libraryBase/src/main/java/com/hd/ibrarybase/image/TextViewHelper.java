package com.hd.ibrarybase.image;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.Spanned;

import com.hd.libraryBase.R;


public class TextViewHelper {

    /**
     * @param context 上下文
     * @return
     */
    public static SpannableString setLeftImage(Context context,  String msg) {
        SpannableString spannableString = new SpannableString("  " + msg);
        Drawable leftDrawable = null;

        leftDrawable = context.getResources().getDrawable(R.drawable.icon_must_read);

        leftDrawable.setBounds(0, 0, leftDrawable.getIntrinsicWidth(), leftDrawable.getIntrinsicHeight());

        spannableString.setSpan(new HdImageSpan(leftDrawable), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableString;
    }

}