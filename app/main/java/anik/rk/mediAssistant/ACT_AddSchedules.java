package anik.rk.mediAssistant;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.Group;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ACT_AddSchedules extends AppCompatActivity implements View.OnLongClickListener {

    private ImageView ASClose ;
    private TextView SAVE ;
    private View Card_View ;
    private Button tab_time1 , tab_time2 , tab_time3 ;
    private Button btnStartDate , btnEndDate ;
    public static final int DIALOG_ID = 0 ;
    private int year , month , day ;
    private boolean startDate ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        themeFixer(); fixAccent();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_addschedule);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(0);
        initializePage();

        getCurrentDate();setCurrentDate();

        ASClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ACT_AddSchedules.super.onBackPressed();
            }
        });
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

    private void setCurrentDate() {
        btnStartDate = findViewById(R.id.btnStartDate);
        Calendar calendar = Calendar.getInstance();
        String text = formatDate(calendar).format(calendar.getTime());
        btnStartDate.setText(text);
    }

    private void getCurrentDate() {
        Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);
    }

    public void showDatePicker( View view ){
        if ( view.getId() == R.id.btnStartDate ){
            startDate = true ;
        }
        showDialog(DIALOG_ID);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        if ( id == DIALOG_ID ){
            return new DatePickerDialog(this, onDateSet , year , month , day );
        }
        return null ;
    }

    private DatePickerDialog.OnDateSetListener onDateSet
            = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year_x, int month_x, int dayOfMonth) {
            year = year_x ;
            month = month_x ;
            day = dayOfMonth ;

            Calendar calendar = Calendar.getInstance();
            String text = formatDate(calendar).format(calendar.getTime());

            if ( startDate ){
                btnStartDate.setText(text);
                startDate = false ;
            }else{
                btnEndDate = findViewById(R.id.btnEndDate);
                btnEndDate.setText(text);
            }
        }
    };

    private SimpleDateFormat formatDate( Calendar calendar ) {
        calendar.set(year, month, day);
        SimpleDateFormat format = new SimpleDateFormat("dd MMM, yyyy");
        return format ;
    }
    private void initializePage() {
        tab_time1 = findViewById(R.id.tab_time1);
        tab_time2 = findViewById(R.id.tab_time2);
        tab_time3 = findViewById(R.id.tab_time3);
        ASClose = findViewById(R.id.ASClose);
        SAVE = findViewById(R.id.SAVE);
        Card_View = findViewById(R.id.Card_View);

        SAVE.setZ(20);
        Card_View.setZ(2);
        ASClose.setZ(20);

        tab_time1.setOnLongClickListener(this);
        tab_time2.setOnLongClickListener(this);
        tab_time3.setOnLongClickListener(this);


    }
    public void onClickCategories( View view ){
        int id = view.getId();

        TransitionManager.beginDelayedTransition((ConstraintLayout)findViewById(R.id.Card_View));
        Group Group_Tablet = findViewById(R.id.Group_Tablet);
        Group HiddenOptions = findViewById(R.id.HiddenOptions);
        Group BeforeAfter = findViewById(R.id.BeforeOrAfter);
        Group Group_Drops = findViewById(R.id.Drop_Group);

        switch (id){
        case R.id.ctg_Tablet:

            if ( Group_Tablet.getVisibility() == View.VISIBLE ){

                Group_Tablet.setVisibility(View.GONE );
                BeforeAfter.setVisibility(View.GONE);
                HiddenOptions.setVisibility(View.GONE);

                (findViewById(R.id.ctg_Tablet)).setAlpha((float)0.25);
                (findViewById(R.id.ctg_Capsule)).setAlpha((float) 0.25);
                (findViewById(R.id.ctg_Injection)).setAlpha((float)0.25);
                (findViewById(R.id.ctg_Drops)).setAlpha((float)0.25);

            }else{
                Group_Drops.setVisibility(View.GONE );
                Group_Tablet.setVisibility(View.VISIBLE);
                BeforeAfter.setVisibility(View.VISIBLE);
                HiddenOptions.setVisibility(View.VISIBLE);
                (findViewById(R.id.ctg_Tablet)).setAlpha(1);

                (findViewById(R.id.ctg_Capsule)).setAlpha((float) 0.25);
                (findViewById(R.id.ctg_Injection)).setAlpha((float)0.25);
                (findViewById(R.id.ctg_Drops)).setAlpha((float)0.25);
            }
            break;

        case R.id.ctg_Drops:

            if (Group_Drops.getVisibility() == View.GONE ){

                Group_Tablet.setVisibility(View.GONE);
                BeforeAfter.setVisibility(View.GONE);

                Group_Drops.setVisibility(View.VISIBLE);
                HiddenOptions.setVisibility(View.VISIBLE);
                (findViewById(R.id.quantity)).setVisibility(View.GONE);
                (findViewById(R.id.ctg_Drops)).setAlpha(1);

                (findViewById(R.id.ctg_Tablet)).setAlpha((float) 0.25);
                (findViewById(R.id.ctg_Capsule)).setAlpha((float) 0.25);
                (findViewById(R.id.ctg_Injection)).setAlpha((float)0.25);

            }else{

                Group_Drops.setVisibility(View.GONE);
                HiddenOptions.setVisibility(View.GONE);
                Group_Tablet.setVisibility(View.GONE);
                BeforeAfter.setVisibility(View.GONE);
                (findViewById(R.id.quantity)).setVisibility(View.GONE);

                (findViewById(R.id.ctg_Drops)).setAlpha((float)0.25);
                (findViewById(R.id.ctg_Tablet)).setAlpha((float) 0.25);
                (findViewById(R.id.ctg_Capsule)).setAlpha((float) 0.25);
                (findViewById(R.id.ctg_Injection)).setAlpha((float)0.25);

            }
            break;
        case R.id.ctg_Capsule:

            if ( Group_Tablet.getVisibility() == View.VISIBLE ){

                Group_Tablet.setVisibility(View.GONE );
                BeforeAfter.setVisibility(View.GONE);
                HiddenOptions.setVisibility(View.GONE);
                (findViewById(R.id.ctg_Capsule)).setAlpha((float)0.25);
                (findViewById(R.id.ctg_Tablet)).setAlpha((float) 0.25);
                (findViewById(R.id.ctg_Injection)).setAlpha((float)0.25);
                (findViewById(R.id.ctg_Drops)).setAlpha((float)0.25);

            }else{
                Group_Drops.setVisibility(View.GONE);
                Group_Tablet.setVisibility(View.VISIBLE);
                BeforeAfter.setVisibility(View.VISIBLE);
                HiddenOptions.setVisibility(View.VISIBLE);
                (findViewById(R.id.ctg_Capsule)).setAlpha(1);

                (findViewById(R.id.ctg_Tablet)).setAlpha((float) 0.25);
                (findViewById(R.id.ctg_Injection)).setAlpha((float)0.25);
                (findViewById(R.id.ctg_Drops)).setAlpha((float)0.25);
            }
            break;
        case R.id.ctg_Injection:

            if (Group_Drops.getVisibility() == View.GONE ){

                Group_Tablet.setVisibility(View.GONE);
                BeforeAfter.setVisibility(View.GONE);

                Group_Drops.setVisibility(View.VISIBLE);
                HiddenOptions.setVisibility(View.VISIBLE);
                (findViewById(R.id.quantity)).setVisibility(View.GONE);
                (findViewById(R.id.ctg_Injection)).setAlpha(1);

                (findViewById(R.id.ctg_Tablet)).setAlpha((float) 0.25);
                (findViewById(R.id.ctg_Capsule)).setAlpha((float) 0.25);
                (findViewById(R.id.ctg_Drops)).setAlpha((float)0.25);

            }else{

                Group_Drops.setVisibility(View.GONE);
                HiddenOptions.setVisibility(View.GONE);
                (findViewById(R.id.quantity)).setVisibility(View.GONE);
                (findViewById(R.id.ctg_Injection)).setAlpha((float)0.25);

                (findViewById(R.id.ctg_Tablet)).setAlpha((float) 0.25);
                (findViewById(R.id.ctg_Capsule)).setAlpha((float) 0.25);
                (findViewById(R.id.ctg_Drops)).setAlpha((float)0.25);

            }
            break;
        default:
            return;
        }

    }
    private void setOnLongClick( View v ) {
        Button b = (Button) v;
        if ( v.getAlpha() == 1 ) {
            v.setAlpha((float) 0.25);
            b.setCompoundDrawablesWithIntrinsicBounds( R.drawable.circle_cross, 0, 0, 0);
        } else {
            v.setAlpha(1);
            b.setCompoundDrawablesWithIntrinsicBounds( R.drawable.circle_done, 0, 0, 0);
        }
    }
    private void themeFixer() {
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
                getWindow().setNavigationBarColor(Color.parseColor("#282828"));
            }else if (selTheme.equals("themeBlack")){
                setTheme(R.style.BlackTheme);
                getWindow().setStatusBarColor(Color.parseColor("#101010"));
                getWindow().setNavigationBarColor(Color.parseColor("#101010"));

            }else if (selTheme.equals("BlueGrey")){
                setTheme(R.style.BlueGrey);
                getWindow().setStatusBarColor(Color.parseColor("#2a363c"));
                getWindow().setNavigationBarColor(Color.parseColor("#2a363c"));

            }else if (selTheme.equals("NightMode")){
                setTheme(R.style.NightMode);
                getWindow().setStatusBarColor(Color.parseColor("#373740"));
                getWindow().setNavigationBarColor(Color.parseColor("#373740"));
            }
        }else {
            setTheme(R.style.LightTheme);
            //appliedTheme = "Light" ;
        }
    }
    @Override
    public boolean onLongClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.tab_time1:
                setOnLongClick(v);
                return true ;
            case R.id.tab_time2:
                setOnLongClick(v);
                return true ;
            case R.id.tab_time3:
                setOnLongClick(v);
                return true ;
            default:
                return false;
        }
    }
    public void onClickBeforeAfter( View view ){
        view.getResources().getColor(R.color.colorAccent);
    }

    public void showTimePicker( View view ){

    }
}
