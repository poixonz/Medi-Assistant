package anik.rk.mediAssistant;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Objects;

public class ACT_Main extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "ACT_Main";

    Frag_Dashboard _fragDashboard;
    Frag_Schedules _fragSchedules;
    Frag_Notifications _fragNotifications;
    TextView toolbarText ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        fixTheme();fixAccent();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle(0);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ImageButton FAB = findViewById(R.id.FAB);
        FAB.setZ(100);

        _fragDashboard = new Frag_Dashboard();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.container, _fragDashboard);
        ft.commit();
        bottomNavigationOnClick();

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
                getWindow().setNavigationBarColor(getResources().getColor(R.color.darkPrimary));
            }else if (selTheme.equals("themeBlack")){
                setTheme(R.style.BlackTheme);

            }else if (selTheme.equals("BlueGrey")){
                setTheme(R.style.BlueGrey);
                getWindow().setNavigationBarColor(Color.parseColor("#2a363c"));

            }else if (selTheme.equals("NightMode")){
                setTheme(R.style.NightMode);
                getWindow().setNavigationBarColor(Color.parseColor("#373740"));
            }
        }else {
            setTheme(R.style.LightTheme);
        }
    }

    private void bottomNavigationOnClick() {
        BottomNavigationView bottomNav = findViewById(R.id.BottomNav);
        bottomNav.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                _fragSchedules = new Frag_Schedules();
                _fragNotifications = new Frag_Notifications();
                toolbarText = findViewById(R.id.toolbarText);
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                int id = menuItem.getItemId();
                if ( id == R.id.btm_nav_schedules){

                    ft.replace(R.id.container, _fragSchedules);
                    toolbarText.setText("Schedules");
                    ((NavigationView)findViewById(R.id.nav_view)).setCheckedItem(R.id.nav_schedules);

                }else if ( id == R.id.btm_nav_dash){

                    ft.replace(R.id.container, _fragDashboard);
                    toolbarText.setText("Medi Assistant");
                    ((NavigationView)findViewById(R.id.nav_view)).setCheckedItem(R.id.nav_dashboard);

                }else if ( id == R.id.btm_nav_notify){

                    ft.replace(R.id.container, _fragNotifications);
                    toolbarText.setText("Notifications");
                    ((NavigationView)findViewById(R.id.nav_view)).setCheckedItem(R.id.nav_notify);
                }
                ft.commit();
                return true ;
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Intent intent = new Intent(ACT_Main.this,ACT_Settings.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        _fragDashboard = new Frag_Dashboard();
        _fragSchedules = new Frag_Schedules();
        _fragNotifications = new Frag_Notifications();

        toolbarText = findViewById(R.id.toolbarText);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        int id = item.getItemId();
        if (id == R.id.nav_settings) {

            Intent intent = new Intent(ACT_Main.this,ACT_Settings.class);
            startActivity(intent);

        }else if (id == R.id.nav_reminder) {

            Intent intent = new Intent(ACT_Main.this,ACT_Reminder.class);
            startActivity(intent);

        }else if ( id == R.id.nav_schedules){

            ft.replace(R.id.container, _fragSchedules);
            ft.commit();
            toolbarText.setText("Schedules");
            ((BottomNavigationView)findViewById(R.id.BottomNav)).setSelectedItemId(R.id.btm_nav_schedules);

        }else if ( id == R.id.nav_dashboard ){

            ft.replace(R.id.container, _fragDashboard);
            ft.commit();
            toolbarText.setText("Medi Assistant");
            ((BottomNavigationView)findViewById(R.id.BottomNav)).setSelectedItemId(R.id.btm_nav_dash);
        }else if ( id == R.id.nav_notify ){

            ft.replace(R.id.container, _fragNotifications );
            ft.commit();
            toolbarText.setText("Notifications");
            ((BottomNavigationView)findViewById(R.id.BottomNav)).setSelectedItemId(R.id.btm_nav_notify);

        }else if (id == R.id.nav_aboutapp) {

            Intent intent = new Intent(ACT_Main.this,ACT_AboutApp.class);
            startActivity(intent);

        }else if (id == R.id.nav_aboutdev) {

            Intent intent = new Intent(ACT_Main.this,ACT_AboutDeveloper.class);
            startActivity(intent);

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }

    public void setOnClickFAB(View view ){
        Intent intent = new Intent(ACT_Main.this , ACT_AddSchedules.class );
        startActivity(intent);
    }
    private void fixAccent() {
        SharedPreferences Theme = getSharedPreferences("theme",MODE_PRIVATE);
        if ( Theme.contains("colorPicked") ){
            String colorP = Theme.getString("colorPicked","no color");
            int styleIdAccent = getResources().getIdentifier(colorP+"Accent", "style", getPackageName());
            getTheme().applyStyle(styleIdAccent,true);
        }
    }
}
