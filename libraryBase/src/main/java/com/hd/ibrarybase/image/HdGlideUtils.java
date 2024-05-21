package com.hd.ibrarybase.image;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.hd.ibrarybase.utils.HdDisplayUtil;

/**
 * 作者 YuQi
 * 注意代码尽量不要有警告
 * 2023/10/12
 **/
public class HdGlideUtils {
    private static Context context;

    public static void initGlide(Application application) {
        context = application;
    }

    /**
     * 网络图片
     *
     * @param view
     * @param url
     */
    public static void setImgNormal(ImageView view, String url) {
        if (null == url || TextUtils.isEmpty(url)) {
            return;
        }
        Glide.with(context).load(url).into(view);
    }

    /**
     * 圆角图片
     *
     * @param view
     * @param url
     * @param corner
     */
    public static void setImgCorner(ImageView view, String url, float corner) {
        if (null == url || TextUtils.isEmpty(url)) {
            return;
        }
        RequestOptions options = new RequestOptions().transform(new RoundedCorners(HdDisplayUtil.dp2px(corner, context.getResources())));
        Glide.with(context).load(url).apply(options).into(view);
    }


    public static void setImgCircle(ImageView view, String url) {
        if (null == url || TextUtils.isEmpty(url)) {
            return;
        }
        RequestOptions options = new RequestOptions().optionalCircleCrop();
        Glide.with(context).asBitmap().load(url).apply(options).into(view);
    }

    public static void setImgCorner(ImageView view, String url, float corner, boolean leftTop, boolean rightTop, boolean leftBottom, boolean rightBottom) {
        if (null == url || TextUtils.isEmpty(url)) {
            return;
        }
        CornerTransform cornerTransform = new CornerTransform(context, HdDisplayUtil.dp2px(corner, context.getResources()));
        cornerTransform.setNeedCorner(leftTop, rightTop, leftBottom, rightBottom);
        RequestOptions options = new RequestOptions().transform(cornerTransform);
        Glide.with(context).asBitmap().load(url).apply(options).into(view);

    }
    public static void setImgCorner(ImageView view, Integer url, float corner, boolean leftTop, boolean rightTop, boolean leftBottom, boolean rightBottom) {
        if (null == url ) {
            return;
        }
        CornerTransform cornerTransform = new CornerTransform(context, HdDisplayUtil.dp2px(corner, context.getResources()));
        cornerTransform.setNeedCorner(leftTop, rightTop, leftBottom, rightBottom);
        RequestOptions options = new RequestOptions().transform(cornerTransform);
        Glide.with(context).asBitmap().load(url).apply(options).into(view);
    }

    public static URLDrawable getURLDrawable(String source, TextView textView) {
        URLDrawable urlDrawable = new URLDrawable();
        Glide.with(context).asBitmap().load(source).into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                urlDrawable.bitmap = changeBitmapSize(resource);
                urlDrawable.setBounds(0, 0, changeBitmapSize(resource).getWidth(), changeBitmapSize(resource).getHeight());
                textView.invalidate();
                textView.setText(textView.getText());//不加这句显示不出来图片，原因不详
            }
        });
        return urlDrawable;
    }

    public static class URLDrawable extends BitmapDrawable {
        public Bitmap bitmap;

        @Override
        public void draw(Canvas canvas) {
            super.draw(canvas);
            if (bitmap != null) {
                canvas.drawBitmap(bitmap, 0, 0, getPaint());
            }
        }
    }

    private static Bitmap changeBitmapSize(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Log.e("width", "width:" + width);

        Log.e("height", "height:" + height);
        //设置想要的大小
        int newWidth = width;
        int newHeight = height;
        //计算压缩的比率
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        //获取想要缩放的matrix
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        //获取新的bitmap
        bitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);

        bitmap.getWidth();

        bitmap.getHeight();

        Log.e("newWidth", "newWidth" + bitmap.getWidth());

        Log.e("newHeight", "newHeight" + bitmap.getHeight());

        return bitmap;

    }

}
