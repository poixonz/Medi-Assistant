package anik.rk.mediAssistant;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class Frag_Notifications extends Fragment {

    RecyclerView recyclerView_frag_notify ;
    ADAPTER_RecyclerView adapter_recyclerView ;
    ArrayList<ScheduleItems_MAIN> MAIN_LIST ;
    public static final String WHICH_RECYCLERVIEW = "Frag_Notifications";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View NotifyView =  inflater.inflate(R.layout.frag_notifications, container, false);

        recyclerView_frag_notify = NotifyView.findViewById(R.id.RecyclerView_Frag_Notify);
        recyclerView_frag_notify.setHasFixedSize(true);
        recyclerView_frag_notify.setLayoutManager(new LinearLayoutManager(getActivity()));

        MAIN_LIST = new ArrayList<>();
        setListItems();
        adapter_recyclerView = new ADAPTER_RecyclerView(MAIN_LIST,WHICH_RECYCLERVIEW);

        recyclerView_frag_notify.setAdapter(adapter_recyclerView);

        return NotifyView;
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
}
