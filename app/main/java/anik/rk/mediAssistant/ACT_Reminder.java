package anik.rk.mediAssistant;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class ACT_Reminder extends AppCompatActivity {

    private TextView medicineName ;
    private ImageButton rem_sc_logo ;
    private ImageView arrows ;
    private String appliedTheme ;

    View reminder_scAnimator ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        fixTheme();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_reminder);

        medicineName = findViewById(R.id.medicineName);
        rem_sc_logo = findViewById(R.id.reminder_sc_logo);
        reminder_scAnimator = findViewById(R.id.reminder_scAnimator);
        arrows = findViewById(R.id.arrows);

        medicineName.setZ(20);
        rem_sc_logo.setZ(10);

        initPage() ;

        Animation animation = AnimationUtils.loadAnimation(this,R.anim.reminder_sc_oval_anim);
        Animation animation1 = AnimationUtils.loadAnimation(this,R.anim.arrow_anim);
        reminder_scAnimator.startAnimation(animation);
        arrows.startAnimation(animation1);
    }

    private void initPage() {
        ImageView reminder_big_oval = findViewById(R.id.reminder_big_oval);
        if (appliedTheme.equals("Light")){
            reminder_big_oval.setImageResource(R.drawable.reminder_oval);
        }else if (appliedTheme.equals("Dark")){
            reminder_big_oval.setImageResource(R.drawable.reminder_oval_dark);
        }
    }

    private void fixTheme() {
        SharedPreferences preferences = getSharedPreferences("theme" , MODE_PRIVATE );
        if (preferences.contains("selectedTheme")){
            String selTheme = preferences.getString("selectedTheme", "no theme");
            assert selTheme != null;
            if (selTheme.equals("themeLight")){
                setTheme(R.style.LightTheme);appliedTheme = "Light";
                getWindow().setNavigationBarColor(getResources().getColor(R.color.colorPrimary));
            }else if (selTheme.equals("themeDark")){
                setTheme(R.style.DarkTheme);appliedTheme = "Dark";
                getWindow().setStatusBarColor(getResources().getColor(R.color.darkPrimary));
                getWindow().setNavigationBarColor(getResources().getColor(R.color.darkPrimary));

            }else if (selTheme.equals("themeBlack")){
                setTheme(R.style.BlackTheme);appliedTheme = "Dark";
                getWindow().setStatusBarColor(Color.parseColor("#101010"));
                getWindow().setNavigationBarColor(Color.parseColor("#101010"));

            }else if (selTheme.equals("BlueGrey")){
                setTheme(R.style.BlueGrey);appliedTheme = "Dark";
                getWindow().setNavigationBarColor(Color.parseColor("#2a363c"));
                getWindow().setStatusBarColor(Color.parseColor("#2a363c"));

            }else if (selTheme.equals("NightMode")){
                setTheme(R.style.NightMode);appliedTheme = "Dark";
                getWindow().setNavigationBarColor(Color.parseColor("#373740"));
                getWindow().setStatusBarColor(Color.parseColor("#373740"));
            }
        }else {
            setTheme(R.style.LightTheme);appliedTheme = "Light";
        }
    }
}
