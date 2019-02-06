package anik.rk.mediAssistant;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class ADAPTER_UpcomingView extends RecyclerView.Adapter<ADAPTER_UpcomingView.ViewHolder>{

    ArrayList<upcoming_list_items> listItems ;
    Context context ;

    public ADAPTER_UpcomingView(ArrayList<upcoming_list_items> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.row_upcoming,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        final upcoming_list_items items = listItems.get(i);

        holder.medicineName.setText(items.getMedicineName());
        holder.medicineType.setText(items.getMedicineType());
        holder.mediHave.setText(items.getMediHave());
        holder.daysToCom.setText(items.getDaysToCom());
        holder.takeTime.setText(items.getTakeTime());

        holder.ListParentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,ACT_ScheduleDetails.class);
                intent.putExtra("Medicine_Name" , items.getMedicineName() );
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listItems.size() ;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView medicineName , medicineType , mediHave , daysToCom , takeTime ;
        ConstraintLayout ListParentLayout ;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            medicineName = itemView.findViewById(R.id.medicineName);
            medicineType = itemView.findViewById(R.id.medicineType);
            mediHave = itemView.findViewById(R.id.mediHave);
            daysToCom = itemView.findViewById(R.id.daysToComplete);
            takeTime = itemView.findViewById(R.id.takeTime);

            ListParentLayout = itemView.findViewById(R.id.ListParentLayout);
        }
    }
}
