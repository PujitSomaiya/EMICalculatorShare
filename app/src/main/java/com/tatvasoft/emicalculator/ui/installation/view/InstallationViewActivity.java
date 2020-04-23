package com.tatvasoft.emicalculator.ui.installation.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.tatvasoft.emicalculator.R;
import com.tatvasoft.emicalculator.ui.installation.model.InstallmentModel;

import java.security.KeyStore;
import java.util.ArrayList;

public class InstallationViewActivity extends AppCompatActivity {

    private Double LoanAmount,RateOfInterest,Installment;
    private InstallationRecyclerAdapter installationRecyclerAdapter;
    private LinearLayoutManager linearLayoutManager;
    private RecyclerView rvInstallments;
    private ArrayList<InstallmentModel> installmentData = new ArrayList<>();
    Double storeAmount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_installation_view);
        rvInstallments=findViewById(R.id.rvInstallments);


        Intent homeIntent=getIntent();
        LoanAmount=homeIntent.getDoubleExtra("Loan Amount",0);
        RateOfInterest=homeIntent.getDoubleExtra("Rate of interest",0);
        Installment=homeIntent.getDoubleExtra("Installment",0);
        getDataList();
        linearLayoutManager = new LinearLayoutManager(getBaseContext(), LinearLayoutManager.VERTICAL, false);
        rvInstallments.setLayoutManager(linearLayoutManager);
        storeAmount=LoanAmount;
        rvInstallments.setAdapter(new InstallationRecyclerAdapter(this,getDataList()));
    }
    private ArrayList<InstallmentModel> getDataList() {
        installmentData = new ArrayList<>();
        for (Double i=(Installment+1);i>1;i--){
            Double Interest=(LoanAmount*RateOfInterest)/100;
//            Double EMI= ((LoanAmount*(RateOfInterest/100))/(Math.pow((1+(RateOfInterest/100)),Installment))-1)*12;
            Double principle=LoanAmount/Installment;
            Double EMI=principle+Interest;
            if (i==(Installment+1)){
                storeAmount=LoanAmount-principle;
            }
            Double opening=storeAmount;
            if (opening<=0){
            opening=0.0;
            }
            installmentData.add(new InstallmentModel(i,Interest,RateOfInterest,LoanAmount,EMI,principle,opening));
            storeAmount=storeAmount-principle;
        }
        return installmentData;
    }
}
