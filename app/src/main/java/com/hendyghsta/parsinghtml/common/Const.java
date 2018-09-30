package com.hendyghsta.parsinghtml.common;

import android.content.Context;
import android.os.Environment;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.util.Base64;

import com.hendyghsta.parsinghtml.R;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by hendyghsta on 08/14/2018.
 */
public class Const {

    public static final String FRAGMENT_RES_ID = "fragment_resource_id";
    public static final String DETAIL_ELEMENT = "detail_element";
    public static final String BITMAP_ID = "bitmap_id";
    public static final String FROM_LAYOUT = "from_layout";
    public static final String PARCEL = "parcel";
    public static final String ITEM_TEXT = "item_text";
    public static final String ID = "id";
    public static final String FOLDER_FILE = "folder_file";
    public static final int ITEM_VIEW_TYPE = 0;
    public static final String URL = "https://bioskop45.com";
    public static final int BANNER_AD_VIEW_TYPE = 1;

    public static File getParentFile(@NonNull Context context) {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            File file = new File(Environment.getExternalStoragePublicDirectory(context.getResources().getString(R.string.app_name)), "download");
            if (!file.isDirectory()){
                file.mkdirs();
            }
            return file;
        } else {
            return context.getCacheDir();
        }
    }

    @IntDef({ViewType.ANIME, ViewType.TOKUKATSU})
    @Retention(RetentionPolicy.SOURCE)
    public @interface ViewType {
        int ANIME = 100;
        int TOKUKATSU = 200;
    }

    public static String decodeString(String str) {
        byte[] decode = Base64.decode(str, 0);
        String str2 = "";
        try {
            return new String(decode, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return str2;
        } catch (Throwable th) {
            return str2;
        }
    }
}
