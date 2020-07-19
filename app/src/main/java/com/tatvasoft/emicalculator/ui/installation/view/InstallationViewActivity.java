package com.tatvasoft.emicalculator.ui.installation.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.content.Intent;
import android.os.Bundle;
import com.tatvasoft.emicalculator.R;
import com.tatvasoft.emicalculator.databinding.ActivityInstallationViewBinding;
import com.tatvasoft.emicalculator.ui.installation.model.InstallmentModel;

import java.util.ArrayList;

public class InstallationViewActivity extends AppCompatActivity {

    private Double LoanAmount,RateOfInterest,Installment,storeAmount;
    private ActivityInstallationViewBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_installation_view);
        initControls();
    }

    private void initControls() {
        getIntentData();
        getDataList();
        initListeners();
        setAdapter();
    }

    private void setAdapter() {
        storeAmount=LoanAmount;
        binding.rvInstallments.setAdapter(new InstallationRecyclerAdapter(this,getDataList()));
    }

    private void initListeners() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getBaseContext(), LinearLayoutManager.VERTICAL, false);
        binding.rvInstallments.setLayoutManager(linearLayoutManager);
    }

    private void getIntentData() {
        Intent homeIntent=getIntent();
        LoanAmount=homeIntent.getDoubleExtra("Loan Amount",0);
        RateOfInterest=homeIntent.getDoubleExtra("Rate of interest",0);
        Installment=homeIntent.getDoubleExtra("Installment",0);
    }

    private ArrayList<InstallmentModel> getDataList() {
        ArrayList<InstallmentModel> installmentData = new ArrayList<>();
        for (double i = (Installment+1); i>1; i--){
            Double interest=(LoanAmount*RateOfInterest)/100;
            Double principle=LoanAmount/Installment;
            Double EMI=principle+interest;
            if (i==(Installment+1)){
                storeAmount=LoanAmount-principle;
            }
            Double opening=storeAmount;
            if (opening<=0){
            opening=0.0;
            }
            installmentData.add(new InstallmentModel(i,interest,RateOfInterest,LoanAmount,EMI,principle,opening));
            storeAmount=storeAmount-principle;
        }
        return installmentData;
    }
}
