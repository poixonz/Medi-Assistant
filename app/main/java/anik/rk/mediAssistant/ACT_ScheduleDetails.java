package anik.rk.mediAssistant;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar ;


public class ACT_ScheduleDetails extends AppCompatActivity {

    private String MedicineName ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        fixTheme();fixAccent();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_scheduledetails);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        CollapsingToolbarLayout collapse = findViewById(R.id.collapse);
        collapse.setExpandedTitleTextAppearance(R.style.ExpandedAppBar);
        collapse.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar);
        collapse.getBackground().setAlpha(175);

        getIntentData() ;
        collapse.setTitle(MedicineName);
    }

    private void getIntentData() {
        if ( getIntent().hasExtra("Medicine_Name")){
            MedicineName = getIntent().getStringExtra("Medicine_Name");
        }
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
    private void fixAccent() {
        SharedPreferences Theme = getSharedPreferences("theme",MODE_PRIVATE);
        SharedPreferences.Editor editor = Theme.edit() ;
        if ( Theme.contains("colorPicked") ){
            String colorP = Theme.getString("colorPicked","no color");
            int styleIdAccent = getResources().getIdentifier(colorP+"Accent", "style", getPackageName());
            getTheme().applyStyle(styleIdAccent,true);
            editor.putString("previousColorAccent" , colorP );
            editor.apply();
        }else{
            editor.putString("previousColorAccent","Pink");
            editor.apply();
        }
    }
}
