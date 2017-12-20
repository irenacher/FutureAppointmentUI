package com.example.s0282656.futureappointmentui;



import android.content.Context;
import android.graphics.Typeface;


import java.util.*;
import java.util.Locale;

public enum SHCCustomFont {

    // SHC Font ttfs are placed in main/assets/fonts

    SANSPRO_BLACK("fonts/SourceSansPro-Black.ttf"),
    SANSPRO_LIGHT("fonts/SourceSansPro-Light.ttf"),
    SANSPRO_BLACKITALIC("fonts/SourceSansPro-BlackItalic.ttf"),
    SANSPRO_LIGHTITALIC("fonts/SourceSansPro-LightItalic.ttf"),
    SANSPRO_BOLD("fonts/SourceSansPro-Bold.ttf"),
    SANSPRO_REGULAR("fonts/SourceSansPro-Regular.ttf"),
    SANSPRO_BOLDITALIC("fonts/SourceSansPro-BoldItalic.ttf"),
    SANSPRO_SEMIBOLD("fonts/SourceSansPro-Semibold.ttf"),
    SANSPRO_EXTRALIGHT("fonts/SourceSansPro-ExtraLightItalic.ttf"),
    SANSPRO_SEMIBOLDITALIC("fonts/SourceSansPro-SemiboldItalic.ttf"),
    SANSPRO_EXTRALIGHTITALIC("fonts/SourceSansPro-ExtraLightItalic.ttf"),
    SANSPRO_ITALIC("fonts/SourceSansPro-Italic.ttf"),
    SHC_ICON_FONTS("fonts/shc-icon-fonts.ttf");

    private final String fileName;

    private static Map<SHCCustomFont,Typeface> typefaceCache = new HashMap<>();

    SHCCustomFont(String fileName) {
        this.fileName = fileName;
    }

    public static Typeface fromString(String fontName, Context context) {

        SHCCustomFont font = SHCCustomFont.valueOf(fontName.toUpperCase(Locale.US));
        Typeface customTypeface = SHCCustomFont.typefaceCache.get(font);

        if (customTypeface == null) {
            customTypeface = Typeface.createFromAsset(context.getAssets(), font.fileName);
            SHCCustomFont.typefaceCache.put(font, customTypeface);
        }

        return customTypeface;
    }
}
