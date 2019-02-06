package anik.rk.mediAssistant;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ACT_AboutDeveloper extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        fixTheme();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_aboutdeveloper);
    }

    private void fixTheme() {
        SharedPreferences preferences = getSharedPreferences("theme" , MODE_PRIVATE );
        if (preferences.contains("selectedTheme")){
            String selTheme = preferences.getString("selectedTheme", "no theme");
            assert selTheme != null;
            if (selTheme.equals("themeLight")){
                setTheme(R.style.LightTheme);
                getWindow().setNavigationBarColor(getResources().getColor(R.color.colorPrimary));
            }else if (selTheme.equals("themeDark")){

                setTheme(R.style.DarkTheme);
                getWindow().setStatusBarColor(getResources().getColor(R.color.darkPrimary));
                getWindow().setNavigationBarColor(getResources().getColor(R.color.darkPrimary));

            }else if (selTheme.equals("themeBlack")){
                setTheme(R.style.BlackTheme);
                getWindow().setStatusBarColor(Color.parseColor("#101010"));
                getWindow().setNavigationBarColor(Color.parseColor("#101010"));

            }else if (selTheme.equals("BlueGrey")){
                setTheme(R.style.BlueGrey);
                getWindow().setNavigationBarColor(Color.parseColor("#2a363c"));
                getWindow().setStatusBarColor(Color.parseColor("#2a363c"));

            }else if (selTheme.equals("NightMode")){
                setTheme(R.style.NightMode);
                getWindow().setNavigationBarColor(Color.parseColor("#373740"));
                getWindow().setStatusBarColor(Color.parseColor("#373740"));
            }
        }else {
            setTheme(R.style.LightTheme);
        }
    }
}
