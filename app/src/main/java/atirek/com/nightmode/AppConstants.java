package atirek.com.nightmode;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Atirek on 11-12-2016.
 */

public class AppConstants {

    String themePrefs = "themePrefs";
    Context context;
    Snackbar snackbar;
    Toast toast;

    public AppConstants(Context context) {
        this.context = context;
    }

    public String getTheme() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(themePrefs, Context.MODE_PRIVATE);
        String theme = sharedPreferences.getString(themePrefs, null);
        return theme;
    }

    public void clearTheme() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(themePrefs, Context.MODE_PRIVATE);
        if (sharedPreferences.contains(themePrefs)) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.commit();
        }
    }

    public void setTheme(String theme) {
        SharedPreferences.Editor editor = getThemeEditor();
        editor.putString(themePrefs, theme);
        editor.commit();
        Log.d("Theme>>>", "Theme Changed: " + theme);

        ViewGroup parent = (ViewGroup) ((BaseActivity) context).getWindow().getDecorView();
        snackbar = Snackbar.make(parent, "Theme Changed", Snackbar.LENGTH_SHORT);
        toast = Toast.makeText(context, "Theme Changed", Toast.LENGTH_SHORT);

        if (getTheme().equals("Light")) {
            setThemeLight(parent);
            context.setTheme(R.style.AppThemeLight);
        } else {
            setThemeDark(parent);
            context.setTheme(R.style.AppThemeDark);
        }

/*
        ((BaseActivity) context).finish();
        context.startActivity(new Intent(context, ((BaseActivity) context).getClass()));
        ((BaseActivity) context).overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
*/

        snackbar.show();
        toast.show();
        //Toast.makeText(context, "Theme Changed", Toast.LENGTH_SHORT).show();
    }

    public void setThemeLight(ViewGroup parent) {
        if (parent != null) {
            for (int i = 0; i < parent.getChildCount(); i++) {
                final View child = parent.getChildAt(i);
                if (child instanceof ViewGroup) {
                    //((ViewGroup) child).setBackgroundColor(context.getResources().getColor(android.R.color.white));
                    if (child instanceof RelativeLayout) {
                        ((RelativeLayout) child).setBackgroundColor(context.getResources().getColor(android.R.color.white));
                    }

                    snackbar.getView().setBackgroundColor(context.getResources().getColor(R.color.colorPrimaryDark));
                    ((TextView) snackbar.getView().findViewById(android.support.design.R.id.snackbar_text)).setTextColor(context.getResources().getColor(android.R.color.black));

                    //toast.getView().setBackgroundColor(context.getResources().getColor(R.color.colorPrimaryDark));
                    ((TextView) toast.getView().findViewById(android.R.id.message)).setTextColor(context.getResources().getColor(android.R.color.black));

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        Window window = ((BaseActivity) context).getWindow();
                        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                        window.setStatusBarColor(context.getResources().getColor(R.color.colorPrimaryDark));
                    }
                    setThemeLight(((ViewGroup) child));
                } else {
                    /*if (child instanceof View) {
                        ((View) child).setBackgroundColor(context.getResources().getColor(android.R.color.white));
                    }*/
                    if (child instanceof TextView) {
                        ((TextView) child).setTextColor(context.getResources().getColor(android.R.color.black));
                        ((TextView) child).setBackgroundColor(context.getResources().getColor(android.R.color.transparent));
                    }
                    if (child instanceof Button) {
                        ((Button) child).setTextColor(context.getResources().getColor(android.R.color.black));
                        ((Button) child).setBackgroundColor(context.getResources().getColor(android.R.color.darker_gray));
                    }
                    if (child instanceof EditText) {
                        ((Button) child).setTextColor(context.getResources().getColor(android.R.color.black));
                    }
                    if (child instanceof ImageView) {
                        ((ImageView) child).setColorFilter(ContextCompat.getColor(context, R.color.colorPrimaryDark));
                        ((ImageView) child).setBackgroundColor(context.getResources().getColor(android.R.color.transparent));
                    }
                }
            }
        }
    }

    public void setThemeDark(ViewGroup parent) {
        if (parent != null) {
            for (int i = 0; i < parent.getChildCount(); i++) {
                final View child = parent.getChildAt(i);
                if (child instanceof ViewGroup) {
                    //((ViewGroup) child).setBackgroundColor(context.getResources().getColor(android.R.color.black));
                    if (child instanceof RelativeLayout) {
                        ((RelativeLayout) child).setBackgroundColor(context.getResources().getColor(android.R.color.black));
                    }

                    snackbar.getView().setBackgroundColor(context.getResources().getColor(R.color.colorAccent));
                    ((TextView) snackbar.getView().findViewById(android.support.design.R.id.snackbar_text)).setTextColor(context.getResources().getColor(android.R.color.white));
                    //toast.getView().setBackgroundColor(context.getResources().getColor(R.color.colorAccent));
                    ((TextView) toast.getView().findViewById(android.R.id.message)).setTextColor(context.getResources().getColor(android.R.color.white));

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        Window window = ((BaseActivity) context).getWindow();
                        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                        window.setStatusBarColor(context.getResources().getColor(R.color.colorAccent));
                    }
                    setThemeDark(((ViewGroup) child));
                } else {
                    /*if (child instanceof View) {
                        ((View) child).setBackgroundColor(context.getResources().getColor(android.R.color.black));
                    }*/
                    if (child instanceof TextView) {
                        ((TextView) child).setTextColor(context.getResources().getColor(android.R.color.white));
                        ((TextView) child).setBackgroundColor(context.getResources().getColor(android.R.color.transparent));
                    }
                    if (child instanceof Button) {
                        ((Button) child).setTextColor(context.getResources().getColor(android.R.color.white));
                        ((Button) child).setBackgroundColor(context.getResources().getColor(android.R.color.darker_gray));
                    }
                    if (child instanceof EditText) {
                        ((Button) child).setTextColor(context.getResources().getColor(android.R.color.white));
                    }
                    if (child instanceof ImageView) {
                        ((ImageView) child).setColorFilter(ContextCompat.getColor(context, R.color.colorAccent));
                        ((ImageView) child).setBackgroundColor(context.getResources().getColor(android.R.color.transparent));
                    }
                }
            }
        }
    }

    public boolean checkTheme() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(themePrefs, Context.MODE_PRIVATE);
        return sharedPreferences.contains(themePrefs);
    }

    public SharedPreferences.Editor getThemeEditor() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(themePrefs, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return editor;
    }

}
