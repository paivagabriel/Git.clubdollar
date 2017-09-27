package com.paivadeveloper.clubdollar.util;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;


public class Font {

    public static void setDancingScript(TextView tv){
        Typeface face = Typeface.createFromAsset( tv.getContext().getAssets(), "dancing_script.ttf");
        tv.setTypeface( face );
    }

    public static Typeface getDancingScript(Context context){
        return Typeface.createFromAsset( context.getAssets(), "dancing_script.ttf");
    }

    public static void setKaushanScript(TextView tv){
        Typeface face = Typeface.createFromAsset( tv.getContext().getAssets(), "kaushan_script.ttf");
        tv.setTypeface( face );
    }

    public static Typeface getKaushanScript(Context context){
        return Typeface.createFromAsset( context.getAssets(), "kaushan_script.ttf");
    }

    public static void setPacifico(TextView tv){
        Typeface face = Typeface.createFromAsset( tv.getContext().getAssets(), "pacifico.ttf");
        tv.setTypeface( face );
    }

    public static Typeface getPacifico(Context context){
        return Typeface.createFromAsset( context.getAssets(), "pacifico.ttf");
    }

    public static void setSatisfy(TextView tv){
        Typeface face = Typeface.createFromAsset( tv.getContext().getAssets(), "satisfy.ttf");
        tv.setTypeface( face );
    }

    public static Typeface getSatisfy(Context context){
        return Typeface.createFromAsset( context.getAssets(), "satisfy.ttf");
    }

    public static void setSedgwickAveDisplay(TextView tv){
        Typeface face = Typeface.createFromAsset( tv.getContext().getAssets(), "sedgwick_ave_display.ttf");
        tv.setTypeface( face );
    }

    public static Typeface getSedgwickAveDisplay(Context context){
        return Typeface.createFromAsset( context.getAssets(), "sedgwick_ave_display.ttf");
    }

    public static void setFascinateInline(TextView tv){
        Typeface face = Typeface.createFromAsset( tv.getContext().getAssets(), "FascinateInline.ttf");
        tv.setTypeface( face );
    }

    public static Typeface getFascinateInline(Context context){
        return Typeface.createFromAsset( context.getAssets(), "FascinateInline.ttf");
    }

    public static void setAmaticSC(TextView tv){
        Typeface face = Typeface.createFromAsset( tv.getContext().getAssets(), "AmaticSC.ttf");
        tv.setTypeface( face );
    }

    public static Typeface getAmaticSC(Context context){
        return Typeface.createFromAsset( context.getAssets(), "AmaticSC.ttf");
    }
}
