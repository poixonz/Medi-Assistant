package anik.rk.mediAssistant;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;

public class Frag_Dashboard extends Fragment {

    View DashView ;
    TextView tvSeconds , tvCurrentTime , tvCurrentDate  ;
    TextView MSTHText ;
    public static final String QUICK_RECYCLERVIEW = "QUICK_RECYCLERVIEW";

    private ArrayList<upcoming_list_items> listItems ;

    private ArrayList<ScheduleItems_MAIN> MAIN_LIST ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container ,
                             Bundle savedInstanceState) {
        DashView = inflater.inflate(R.layout.frag_dashboard, container, false);
        initialize_page();

        RecyclerView upcomingView = DashView.findViewById(R.id.UpcomingView);
        RecyclerView quickOverview = DashView.findViewById(R.id.QuickOverview);

        upcomingView.setHasFixedSize(true);
        quickOverview.setHasFixedSize(true);

        upcomingView.setLayoutManager( new LinearLayoutManager(getContext()));

        LinearLayoutManager layoutManager = new LinearLayoutManager(
                getContext() , LinearLayoutManager.HORIZONTAL, false);
        quickOverview.setLayoutManager( layoutManager);

        listItems = new ArrayList<>();
        MAIN_LIST = new ArrayList<>();

        ADAPTER_UpcomingView ADAPTER_UpcomingView = new ADAPTER_UpcomingView(listItems, getContext());
        ADAPTER_RecyclerView ADAPTER_QuickOverView = new ADAPTER_RecyclerView(MAIN_LIST, QUICK_RECYCLERVIEW);

        setListItems();
        setList();

        upcomingView.setAdapter(ADAPTER_UpcomingView);
        quickOverview.setAdapter(ADAPTER_QuickOverView);

        return DashView ;
    }
    private void setListItems() {
        ScheduleItems_MAIN items = new ScheduleItems_MAIN(
                "Oxymopril",
                "Tablet",
                "21",
                "30",
                "Before",
                "09:30",
                "AM",
                "20.10.2018",
                "27.10.2018",
                "21",
                "3",
                "7"
        );
        MAIN_LIST.add(items);

        items = new ScheduleItems_MAIN(
                "Lansopenem",
                "Tablet",
                "15",
                "30",
                "After",
                "10:00",
                "AM",
                "20.10.2018",
                "23.10.2018",
                "12",
                "3",
                "4"
        );
        MAIN_LIST.add(items);

        items = new ScheduleItems_MAIN(
                "Acetaminophen",
                "Tablet",
                "6",
                "30",
                "After",
                "10:00",
                "AM",
                "20.10.2018",
                "23.10.2018",
                "12",
                "3",
                "4"
        );
        MAIN_LIST.add(items);

        items = new ScheduleItems_MAIN(
                "Loratadine",
                "Tablet",
                "27",
                "30",
                "After",
                "10:00",
                "AM",
                "20.10.2018",
                "25.10.2018",
                "15",
                "3",
                "5"
        );
        MAIN_LIST.add(items);
    }

    private void initialize_page() {
        tvCurrentTime = DashView.findViewById(R.id.tvCurrentTime);
        tvCurrentDate = DashView.findViewById(R.id.tvCurrentDate);
        tvSeconds = DashView.findViewById(R.id.tvSecond);

        runCurrentTime();showDate();
    }

    private void showDate() {
        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance().format(calendar.getTime());
        tvCurrentDate.setText(currentDate);
    }

    private void runCurrentTime() {
        final TextView AMPM = DashView.findViewById(R.id.AMPM);
        Thread thread = new Thread(){
            @Override
            public void run() {
                try{
                    while (!isInterrupted()){
                        Thread.sleep(100);
                        Objects.requireNonNull(getActivity()).runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                long time = System.currentTimeMillis();
                                @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
                                @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf2 = new SimpleDateFormat("a");
                                @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf3 = new SimpleDateFormat(":ss");
                                String FTime = sdf.format(time);
                                String FTime2 = sdf2.format(time);
                                String FTime3 = sdf3.format(time);
                                tvCurrentTime.setText(FTime);
                                AMPM.setText(FTime2);
                                tvSeconds.setText(FTime3);
                            }
                        });
                    }
                }catch (Exception ignored){

                }
            }
        };thread.start();
    }

    private void setList() {
        upcoming_list_items listItem = new upcoming_list_items(
                "Oxymopril",
                "Tablet",
                "21",
                "7 days to complete",
                "09:30am");
        listItems.add(listItem);

        listItem = new upcoming_list_items(
                "Lansopenem",
                "Tablet",
                "30",
                "10 days to complete",
                "10:00am");
        listItems.add(listItem);

        listItem = new upcoming_list_items(
                "Acetaminophen",
                "Injection",
                "12",
                "7 days to complete",
                "10:00am");
        listItems.add(listItem);

        listItem = new upcoming_list_items(
                "Codeine",
                "Tablet",
                "21",
                "7 days to complete",
                "09:30am");
        listItems.add(listItem);

        listItem = new upcoming_list_items(
                "Loratadine",
                "Drop",
                "",
                "",
                "09:30am");
        listItems.add(listItem);

        listItem = new upcoming_list_items(
                "Wellbutrin",
                "Tablet",
                "6",
                "3 days to complete",
                "10:00am");
        listItems.add(listItem);

        listItem = new upcoming_list_items(
                "Xanax",
                "Tablet",
                "16",
                "4 days to complete",
                "10:00am");
        listItems.add(listItem);
        listItem = new upcoming_list_items(
                "Wellbutrin",
                "Tablet",
                "6",
                "3 days to complete",
                "10:00am");
        listItems.add(listItem);

        listItem = new upcoming_list_items(
                "Xanax",
                "Tablet",
                "16",
                "4 days to complete",
                "10:00am");
        listItems.add(listItem);

    }

}
