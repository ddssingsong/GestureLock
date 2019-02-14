/*
 *  Copyright (C) 2014 The AppCan Open Source Project.
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.

 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.

 *  You should have received a copy of the GNU Lesser General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package com.dds.gestureunlock.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Shader;

public class ResourceUtil {

    public static String packageName;
    public static Resources resources;
    public static Context mContext;

    public static final String anim = "anim";
    public static final String animator = "animator";
    public static final String interpolator = "interpolator";
    public static final String menu = "menu";
    public static final String mipmap = "mipmap";
    public static final String array = "array";
    public static final String bool = "bool";
    public static final String stringArray = "string-array";
    public static final String attr = "attr";
    public static final String color = "color";
    public static final String dimen = "dimen";
    public static final String drawable = "drawable";
    public static final String id = "id";
    public static final String layout = "layout";
    public static final String raw = "raw";
    public static final String string = "string";
    public static final String style = "style";
    public static final String xml = "xml";
    public static final String styleable = "styleable";

    public static void init(Context ctx) {
        packageName = ctx.getPackageName();
        resources = ctx.getResources();
        mContext = ctx.getApplicationContext();
    }

    public static int getResDrawableID(String resName) {

        return resources.getIdentifier(resName, drawable, packageName);
    }

    public static int getResLayoutID(String resName) {

        return resources.getIdentifier(resName, layout, packageName);
    }

    public static int getResAnimID(String resName) {

        return resources.getIdentifier(resName, anim, packageName);
    }

    public static int getResAnimatorID(String resName) {

        return resources.getIdentifier(resName, animator, packageName);
    }

    public static int getResAttrID(String resName) {

        return resources.getIdentifier(resName, attr, packageName);
    }

    public static int getResColorID(String resName) {

        return resources.getIdentifier(resName, color, packageName);
    }

    public static int getResDimenID(String resName) {

        return resources.getIdentifier(resName, dimen, packageName);
    }

    public static int getResIdID(String resName) {

        return resources.getIdentifier(resName, id, packageName);
    }

    public static int getResRawID(String resName) {

        return resources.getIdentifier(resName, raw, packageName);
    }

    public static int getResStringID(String resName) {

        return resources.getIdentifier(resName, string, packageName);
    }

    public static int getResStyleID(String resName) {

        return resources.getIdentifier(resName, style, packageName);
    }

    public static int getResStyleableID(String name) {
        return resources.getIdentifier(name, styleable, packageName);
    }

    public static int getResXmlID(String resName) {

        return resources.getIdentifier(resName, xml, packageName);
    }

    public static int getResInterpolatorID(String resName) {

        return resources.getIdentifier(resName, interpolator, packageName);
    }

    public static int getResMenuID(String resName) {

        return resources.getIdentifier(resName, menu, packageName);
    }

    public static int getResMipmapID(String resName) {

        return resources.getIdentifier(resName, mipmap, packageName);
    }

    public static int getResArrayID(String resName) {

        return resources.getIdentifier(resName, array, packageName);
    }

    public static int getResBoolID(String resName) {

        return resources.getIdentifier(resName, bool, packageName);
    }

    public static int getResStringArrayID(String resName) {

        return resources.getIdentifier(resName, stringArray, packageName);
    }

    public static String getString(String resName) {
        int id = getResStringID(resName);
        return resources.getString(id);
    }

    public static String[] getStringArray(String resArrayName) {
        try {
            int id = getResArrayID(resArrayName);
            String[] arry = resources.getStringArray(id);
            return arry;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int dipToPixels(int dip) {
        float density = resources.getDisplayMetrics().density;
        int valuePixels = (int) (dip * density + 0.5f);
        return valuePixels;
    }


    public static int px2dip(float pxValue) {

        final float scale = resources.getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int parseColor(String inColor) {
        int reColor = 0;
        try {
            if (inColor != null && inColor.length() != 0) {
                inColor = inColor.replace(" ", "");
                if (inColor.charAt(0) == 'r') { //rgba
                    int start = inColor.indexOf('(') + 1;
                    int off = inColor.indexOf(')');
                    inColor = inColor.substring(start, off);
                    String[] rgba = inColor.split(",");
                    int r = Integer.parseInt(rgba[0]);
                    int g = Integer.parseInt(rgba[1]);
                    int b = Integer.parseInt(rgba[2]);
                    int a = Integer.parseInt(rgba[3]);
                    reColor = (a << 24) | (r << 16) | (g << 8) | b;
                } else if (inColor.startsWith("#")) { // #
                    String tmpColor = inColor.substring(1);
                    if (3 == tmpColor.length()) {
                        char[] t = new char[6];
                        t[0] = tmpColor.charAt(0);
                        t[1] = tmpColor.charAt(0);
                        t[2] = tmpColor.charAt(1);
                        t[3] = tmpColor.charAt(1);
                        t[4] = tmpColor.charAt(2);
                        t[5] = tmpColor.charAt(2);
                        inColor = "#" + String.valueOf(t);
                    }
                    reColor = Color.parseColor(inColor);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            reColor = 0;
        }
        return reColor;
    }

    public static Bitmap getLocalImg(Context ctx, String imgUrl) {
        if (imgUrl == null || imgUrl.length() == 0) {
            return null;
        }
        Bitmap bitmap = null;
        try {
            if (imgUrl.startsWith("/")) {
                bitmap = BitmapFactory.decodeFile(imgUrl);
                bitmap = createCircleImage(bitmap);
            }
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    public static Bitmap createCircleImage(Bitmap source) {
        int width = source.getWidth();
        int height = source.getHeight();
        float radius = Math.min(width, height) * 0.5f;
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        //画布设置遮罩效果
        paint.setShader(new BitmapShader(source, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
        //处理图像数据
        Bitmap bitmap = Bitmap.createBitmap(width, height, source.getConfig());
        Canvas canvas = new Canvas(bitmap);
        //bitmap的显示由画笔paint来决定
        canvas.drawCircle(width * 0.5f, height * 0.5f, radius, paint);
        return bitmap;
    }

}
