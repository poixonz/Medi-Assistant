package anik.rk.mediAssistant;

import android.animation.Animator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;

public class ACT_Settings extends AppCompatActivity {

    private String appliedTheme , colorAccent = "Cyan";
    private CardView themes ;
    private View overlay ;
    private CardView colorPicker ;
    private ImageButton previousSelected ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        themeFixer();fixAccent();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_settings);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(0);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        overlay = findViewById(R.id.overLay);
        themes = findViewById( R.id.themes );
        colorPicker = findViewById(R.id.colorPicker);
        previousSelected = findViewById(R.id.Teal);

        setSelectedTheme();setAppliedAccentName();
    }

    private void setAppliedAccentName() {
        TextView tvColorAccent = findViewById(R.id.tvColorAccent);
        tvColorAccent.setText(colorAccent);
    }

    private void fixAccent() {
        SharedPreferences Theme = getSharedPreferences("theme",MODE_PRIVATE);
        SharedPreferences.Editor editor = Theme.edit() ;
        if ( Theme.contains("colorPicked") ){
            colorAccent = Theme.getString("colorPicked","no color");
            int styleIdAccent = getResources().getIdentifier(colorAccent+"Accent", "style", getPackageName());
            getTheme().applyStyle(styleIdAccent,true);
            editor.putString("previousColorAccent" , colorAccent );
            editor.apply();
        }else{
            editor.putString("previousColorAccent","Cyan");
            editor.apply();
        }
    }

    private void setSelectedTheme() {
        RadioButton themeLight = findViewById(R.id.themeLight);
        RadioButton themeDark = findViewById(R.id.themeDark);
        RadioButton themeBlack = findViewById(R.id.themeBlack);
        RadioButton BlueGrey = findViewById(R.id.themeBlueGrey);
        RadioButton NightMode = findViewById(R.id.themeNightMode);
        TextView themedesc = findViewById(R.id.themedesc);

        if (appliedTheme.equals("Light")){
            themeLight.setChecked(true);
            themedesc.setText("Pure Light");
        }else if (appliedTheme.equals("Dark")){
            themeDark.setChecked(true);
            themedesc.setText("Kinda Dark");
        }else if (appliedTheme.equals("Black")){
            themeBlack.setChecked(true);
            themedesc.setText("Pure Black");
        }else if (appliedTheme.equals("BlueGrey")){
            BlueGrey.setChecked(true);
            themedesc.setText("Blue Grey");
        }else if (appliedTheme.equals("NightMode")){
            NightMode.setChecked(true);
            themedesc.setText("Night Mode");
        }
    }

    private void themeFixer() {
        SharedPreferences preferences = getSharedPreferences("theme" , MODE_PRIVATE );
        if (preferences.contains("selectedTheme")){
            String selTheme = preferences.getString("selectedTheme", "no theme");
            assert selTheme != null;
            if (selTheme.equals("themeLight")){
                setTheme(R.style.LightTheme);
                appliedTheme = "Light" ;
                getWindow().setNavigationBarColor(Color.parseColor("#f1f1f1"));

            }else if (selTheme.equals("themeDark")){
                setTheme(R.style.DarkTheme);
                appliedTheme = "Dark" ;
                getWindow().setStatusBarColor(getResources().getColor(R.color.darkPrimary));
                getWindow().setNavigationBarColor(getResources().getColor(R.color.darkPrimary));

            }else if (selTheme.equals("themeBlack")){
                setTheme(R.style.BlackTheme);
                appliedTheme = "Black" ;
                getWindow().setStatusBarColor(Color.parseColor("#101010"));
                getWindow().setNavigationBarColor(Color.parseColor("#101010"));

            }else if (selTheme.equals("BlueGrey")){
                setTheme(R.style.BlueGrey);
                appliedTheme = "BlueGrey" ;
                getWindow().setStatusBarColor(Color.parseColor("#2a363c"));
                getWindow().setNavigationBarColor(Color.parseColor("#2a363c"));

            }else if (selTheme.equals("NightMode")){
                setTheme(R.style.NightMode);
                appliedTheme = "NightMode" ;
                getWindow().setStatusBarColor(Color.parseColor("#373740"));
                getWindow().setNavigationBarColor(Color.parseColor("#373740"));
            }
        }else {
            setTheme(R.style.LightTheme);
            appliedTheme = "Light" ;
        }
    }

    public void SettingsListLayout(View view ){
        int ID = view.getId();
        Switch notifySwitch = findViewById(R.id.notifySwitch);
        Switch vibrateSwitch = findViewById(R.id.VibrateSwitch);

        if( ID == R.id.NotifyLay ){
            if(notifySwitch.isChecked()){
                notifySwitch.setChecked(false);
            }else{
                notifySwitch.setChecked(true);
            }
        }else if ( ID == R.id.VibrateLay ){
            if ( vibrateSwitch.isChecked() ){
                vibrateSwitch.setChecked(false);
            }else{
                vibrateSwitch.setChecked(true);
            }
        }
    }

    public void onClickThemes( View view ){
        //Toolbar toolbar = findViewById(R.id.toolbar);

        overlay.setZ(5);
        themes.setZ(10);
        ConstraintLayout layout = findViewById(R.id.layout);
        TransitionManager.beginDelayedTransition(layout);

        if( themes.getVisibility() == View.VISIBLE ){
            //toolbar.setAlpha((float) 1);
            themes.setVisibility(View.GONE);
            overlay.setVisibility(View.GONE);
        }else{
            //toolbar.setAlpha((float) 0.5);
            themes.setVisibility(View.VISIBLE);
            overlay.setVisibility(View.VISIBLE);
        }
    }

    public void onClickOverlay( View view ){
        previousSelected.setImageResource(0);
        overlay.setVisibility(View.GONE);
        if (colorPicker.getVisibility() == View.INVISIBLE ){
            makeCircularAnimationTwo(themes);
        }else{
            makeCircularAnimationTwo(colorPicker);
        }
    }
    public void onClickCancel( View view ){
        onClickOverlay(view);
    }
    public void onClickLightDark( View view ){
        RadioButton button = (RadioButton) view;

        SharedPreferences preferences = getSharedPreferences("theme" , MODE_PRIVATE );
        SharedPreferences.Editor editor = preferences.edit();

        if ( button.getId() == R.id.themeLight ){
            editor.putString("selectedTheme" , "themeLight" );
        }else if ( button.getId() == R.id.themeDark ){
            editor.putString("selectedTheme" , "themeDark" );
        }else if ( button.getId() == R.id.themeBlack ){
            editor.putString("selectedTheme" , "themeBlack" );
        }else if ( button.getId() == R.id.themeBlueGrey){
            editor.putString("selectedTheme" , "BlueGrey" );
        }else if ( button.getId() == R.id.themeNightMode){
            editor.putString("selectedTheme" , "NightMode" );
        }
        editor.apply();

        Intent intent = new Intent(ACT_Settings.this , ACT_Settings.class);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        finish();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ACT_Settings.this,ACT_Main.class);
        startActivity(intent);
    }

    public void OnClickLayoutAccentColor( View view ){
        getWindow().setStatusBarColor(getResources().getColor(R.color.overlayStatusBar));
        overlay.setZ(10);
        colorPicker.setZ(11);
        colorPicker.setVisibility(View.VISIBLE);
        makeCircularAnimation(colorPicker);
        overlay.setVisibility(View.VISIBLE);
        setSelectedThemeColor();
    }
    public void onClickColorPickerColorButton( View view ){
        previousSelected.setImageResource(0);
        ImageButton button = (ImageButton)view ;
        previousSelected = button ;
        button.setImageResource(R.drawable.done);
        button.setScaleType(ImageView.ScaleType.CENTER_CROP);
        colorAccent = getResources().getResourceEntryName(view.getId());
    }
    private void makeCircularAnimation(final View view) {
        int CenterX = (int)(view.getX() + view.getWidth()) / 2 ;
        int CenterY = (int)(view.getY() + view.getHeight()) / 2 ;
        float radius = Math.max(view.getHeight(),view.getWidth()) * 2.0f ;
        if (view.getVisibility() == View.INVISIBLE )
            view.setVisibility(View.VISIBLE);
        ViewAnimationUtils.createCircularReveal(view,CenterX,CenterY,0,radius).start();
    }
    private void makeCircularAnimationTwo(final View view){
        int CenterX = (int)(view.getX() + view.getWidth()) / 2 ;
        int CenterY = (int)(view.getY() + view.getHeight()) / 2 ;
        float radius = Math.max(view.getHeight(),view.getWidth()) * 2.0f ;
        Animator animator = ViewAnimationUtils.createCircularReveal(view,CenterX,CenterY,radius,0);
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }
            @Override
            public void onAnimationEnd(Animator animation) {
                view.setVisibility(View.INVISIBLE); }
            @Override
            public void onAnimationCancel(Animator animation) {

            }
            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animator.start();
    }
    public void onClickSet( View view ){

        SharedPreferences Theme = getSharedPreferences("theme" ,MODE_PRIVATE);
        SharedPreferences.Editor editor = Theme.edit() ;
        editor.putString("colorPicked",colorAccent);
        editor.apply();

        makeCircularAnimationTwo(colorPicker);
        overlay.setVisibility(View.GONE);

        Intent intent = new Intent(this,ACT_Settings.class);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        finish();
    }
    private void setSelectedThemeColor() {
        SharedPreferences Theme = getSharedPreferences("theme",MODE_PRIVATE);
        if (Theme.contains("previousColorAccent")){

            String previousCA = Theme.getString("previousColorAccent","no color");

            Resources res = getResources();

            int id = res.getIdentifier(previousCA , "id" , getPackageName()) ;
            ImageButton buttonAccent = findViewById(id);
            previousSelected = findViewById(id) ;

            buttonAccent.setImageResource(R.drawable.done);
            buttonAccent.setScaleType(ImageView.ScaleType.FIT_XY);
        }
    }

    public void goToAboutApp( View view ){
        Intent intent = new Intent( this , ACT_AboutApp.class );
        startActivity(intent);
    }

    public void goToAboutDev( View view ){
        Intent intent = new Intent( this , ACT_AboutDeveloper.class );
        startActivity(intent);
    }
}
