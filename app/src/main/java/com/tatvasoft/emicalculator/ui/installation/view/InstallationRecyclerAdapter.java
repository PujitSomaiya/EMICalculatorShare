package com.tatvasoft.emicalculator.ui.installation.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;

import com.tatvasoft.emicalculator.R;
import com.tatvasoft.emicalculator.ui.installation.model.InstallmentModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class InstallationRecyclerAdapter extends RecyclerView.Adapter<InstallationRecyclerAdapter.InstallmentViewHolder> {

    private final Context context;
    private boolean isGridView = false;
    private ArrayList<InstallmentModel> installmentModels;
    private InstallmentModel installmentData;
    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    private Date date;
    private int count = 0,YEAR;




    public InstallationRecyclerAdapter(Context context, ArrayList<InstallmentModel> installmentData) {
        this.context = context;
        this.installmentModels = installmentData;



    }



    @NonNull
    @Override
    public InstallmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = isGridView ?
                LayoutInflater.from(context).inflate(R.layout.listitem_installment, parent, false)
                :
                LayoutInflater.from(context).inflate(R.layout.listitem_installment, parent, false);

        return new InstallmentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final InstallmentViewHolder holder, final int position) {
        installmentData=installmentModels.get(position);
        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        calendar.add(Calendar.DATE, 0);
        calendar.add(Calendar.MONTH, position);
        calendar.add(Calendar.YEAR, 0);
        String futureDate =dateFormat.format(calendar.getTime());
        {
            holder.tvInstallmentNo.setText(String.valueOf(position+1));
            holder.tvInstallmentDateNo.setText(String.valueOf(futureDate));
            holder.tvOpeningBalanceNo.setText(String.valueOf(installmentData.getOpeningNumber()));
            holder.tvEMINo.setText(String.valueOf(installmentData.getEMI()));
            holder.tvInterestNo.setText(String.valueOf(installmentData.getInterest()));
            holder.tvPrincipleNo.setText(String.valueOf(installmentData.getPrinciple()));
        }


    }




    @Override
    public int getItemCount() {
        return installmentModels.size();

    }

    public class InstallmentViewHolder extends RecyclerView.ViewHolder  {
        private final TextView tvInstallmentNo;
        private final TextView tvInstallmentDateNo;
        private final TextView tvOpeningBalanceNo;
        private final TextView tvEMINo;
        private final TextView tvInterestNo;
        private final TextView tvPrincipleNo;



        public InstallmentViewHolder(View itemView) {
            super(itemView);

            tvInstallmentNo = itemView.findViewById(R.id.tvInstallmentNo);
            tvInstallmentDateNo = itemView.findViewById(R.id.tvInstallmentDateNo);
            tvOpeningBalanceNo = itemView.findViewById(R.id.tvOpeningBalanceNo);
            tvEMINo = itemView.findViewById(R.id.tvEMINo);
            tvInterestNo = itemView.findViewById(R.id.tvInterestNo);
            tvPrincipleNo = itemView.findViewById(R.id.tvPrincipleNo);

        }


    }

}


