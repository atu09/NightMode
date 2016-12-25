package atirek.com.nightmode;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Atirek on 11-12-2016.
 */

public class BaseActivity extends AppCompatActivity implements View.OnClickListener {

    AppConstants appConstants = new AppConstants(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if (appConstants.checkTheme()) {
            if (appConstants.getTheme().equals("Light")) {
                setTheme(R.style.AppThemeLight);
            } else {
                setTheme(R.style.AppThemeDark);
            }
        }
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_changeTheme:

                ViewGroup parent = (ViewGroup) getWindow().getDecorView().getRootView();

                if (appConstants.checkTheme()) {

                    if (appConstants.getTheme().equals("Light")) {
                        appConstants.setTheme("Dark");
                    } else {
                        appConstants.setTheme("Light");
                    }

                } else {
                    appConstants.setTheme("Dark");
                }


                break;
            case R.id.btn_nextScreen:
                startActivity(new Intent(this, Main2Activity.class));
                finish();
                break;
            case R.id.btn_previousScreen:
                startActivity(new Intent(this, MainActivity.class));
                finish();
                break;
        }
    }


    @Override
    public void onBackPressed() {

        if (this instanceof Main2Activity) {
            startActivity(new Intent(this, MainActivity.class));

        }
        finish();

    }


    @Override
    protected void onResume() {
        super.onResume();

        if (appConstants.checkTheme()) {

            if (appConstants.getTheme().equals("Dark")) {
                appConstants.setTheme("Dark");
            } else {
                appConstants.setTheme("Light");
            }

        } else {
            appConstants.setTheme("Dark");
        }

    }
}
