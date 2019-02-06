package anik.rk.mediAssistant;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ADAPTER_RecyclerView extends RecyclerView.Adapter<ADAPTER_RecyclerView.ViewHolder>{

    private ArrayList<ScheduleItems_MAIN> scheduleItemMAINS;
    private String WHICH_RECYCLERVIEW ;

    ADAPTER_RecyclerView(ArrayList<ScheduleItems_MAIN> scheduleItemMAINS, String whichRecyclerView) {
        this.scheduleItemMAINS = scheduleItemMAINS;
        this.WHICH_RECYCLERVIEW = whichRecyclerView ;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        switch (WHICH_RECYCLERVIEW) {
            case Frag_Schedules.WHICH_RECYCLERVIEW: {
                View view = LayoutInflater.from(viewGroup.getContext()).
                        inflate(R.layout.row_schedules, viewGroup, false);
                return new ViewHolder(view);

            }
            case Frag_Dashboard.QUICK_RECYCLERVIEW: {
                View view = LayoutInflater.from(viewGroup.getContext()).
                        inflate(R.layout.row_quickoverview, viewGroup, false);
                return new ViewHolder(view);

            }
            case Frag_Notifications.WHICH_RECYCLERVIEW:
                View view = LayoutInflater.from(viewGroup.getContext()).
                        inflate(R.layout.row_notifications, viewGroup, false);
                return new ViewHolder(view);

            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder , int position ) {

        ScheduleItems_MAIN items = scheduleItemMAINS.get(position);

        if (WHICH_RECYCLERVIEW.equals(Frag_Schedules.WHICH_RECYCLERVIEW)){

            holder.medicineName.setText(items.getMedicineName());
            holder.medicineType.setText(items.getMedicineType());
            holder.minbeforeAfter.setText(items.getminbeforeAfter());
            holder.beforeOrAfter.setText(items.getBeforeOrAfter());
            holder.timeToTake.setText(items.getTimeToTake());
            holder.amOrPm.setText(items.getAmOrPm());
            holder.startDate.setText(items.getStartDate());
            holder.endDate.setText(items.getEndDate());
            holder.totalMedicine.setText(items.getTotalMedicine());
            holder.taken.setText(items.getTaken());
            holder.daysToCom.setText(items.getDaysToCom());
            holder.layout.setZ(6);

        }else if ( WHICH_RECYCLERVIEW.equals(Frag_Dashboard.QUICK_RECYCLERVIEW)){

            holder.medicineName.setText(items.getMedicineName());
            holder.medicineType.setText(items.getMedicineType());
            holder.timeToTake.setText(items.getTimeToTake());
            holder.amOrPm.setText(items.getAmOrPm());

        }else if ( WHICH_RECYCLERVIEW.equals(Frag_Notifications.WHICH_RECYCLERVIEW)){

            holder.medicineName.setText(items.getMedicineName());
            holder.medicineType.setText(items.getMedicineType());
            holder.timeToTake.setText(items.getTimeToTake());
            holder.amOrPm.setText(items.getAmOrPm());

            if (position == 0 || position == 1 ){

                holder.Notify_Dot.setVisibility(View.VISIBLE);
                holder.Notify_Overlay.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public int getItemCount() {

        return scheduleItemMAINS.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ConstraintLayout layout , ListParentLayout ;
        ImageView Notify_Dot ; View Notify_Overlay ;
        TextView medicineName , medicineType , beforeOrAfter , timeToTake , amOrPm , mediHave ;
        TextView startDate , endDate , totalMedicine , taken , daysToCom , minbeforeAfter;

        ViewHolder(@NonNull View item) {
            super(item);
            layout = item.findViewById(R.id.sc_parentLayout);
            medicineName = item.findViewById(R.id.medicineName);
            medicineType = item.findViewById(R.id.medicineType);
            minbeforeAfter = item.findViewById(R.id.minbeforeAfter);
            beforeOrAfter = item.findViewById(R.id.beforeOrAfter);
            timeToTake = item.findViewById(R.id.timeToTake);
            amOrPm = item.findViewById(R.id.amOrPm);
            startDate = item.findViewById(R.id.startDate);
            endDate = item.findViewById(R.id.endDate);
            totalMedicine = item.findViewById(R.id.totalMedicine);
            taken = item.findViewById(R.id.taken);
            daysToCom = item.findViewById(R.id.daysToCom);
            mediHave = itemView.findViewById(R.id.mediHave);
            Notify_Dot = item.findViewById(R.id.Notify_Dot);
            Notify_Overlay = item.findViewById(R.id.Notify_Overlay);
            ListParentLayout = itemView.findViewById(R.id.ListParentLayout);
        }
    }
}
