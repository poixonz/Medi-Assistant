package anik.rk.mediAssistant;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class Frag_Schedules extends Fragment {

    View SV ;
    private RecyclerView SchedulesRecycler ;
    private ADAPTER_RecyclerView adapter ;
    private ArrayList<ScheduleItems_MAIN> MAIN_LIST;
    public static final String WHICH_RECYCLERVIEW = "Frag_Schedules";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        SV = inflater.inflate(R.layout.frag_schedules, container, false);

        initializeRecyclerView();
        MAIN_LIST = new ArrayList<>();
        adapter = new ADAPTER_RecyclerView(MAIN_LIST,WHICH_RECYCLERVIEW);
        setListItems();
        SchedulesRecycler.setAdapter(adapter);

        return SV ;
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
    private void initializeRecyclerView() {
        SchedulesRecycler = SV.findViewById(R.id.SchedulesRecycler);
        SchedulesRecycler.setHasFixedSize(true);
        SchedulesRecycler.setLayoutManager( new LinearLayoutManager(getActivity()));
    }

}
