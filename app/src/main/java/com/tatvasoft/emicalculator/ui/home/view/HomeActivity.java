package com.tatvasoft.emicalculator.ui.home.view;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.google.android.material.textfield.TextInputLayout;
import com.tatvasoft.emicalculator.R;
import com.tatvasoft.emicalculator.databinding.ActivityMainBinding;
import com.tatvasoft.emicalculator.ui.installation.view.InstallationViewActivity;
import com.tatvasoft.emicalculator.util.CommonUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    private int count = 0,YEAR;
    private Spinner spnDate;
    final Context context=this;
    private String futureDate,currentDate;
    private Date date;
    private ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main);
        initControls();
    }

    private void initControls() {

        initListeners();
    }

    private void initListeners() {
        binding.btnSubmit.setOnClickListener(this);
        binding.btnClear.setOnClickListener(this);
        binding.btnTime.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnSubmit) {
            checkDetails();
        }else if (v.getId()==R.id.btnClear){
            clearDetails();
        }else if (v.getId()==R.id.btnTime){
            showDialog();
        }
    }

    @SuppressLint("SimpleDateFormat")
    private void showDialog() {
        final Dialog dialog=new Dialog(context);
        dialog.setContentView(R.layout.dialog_time_period);
        final TextView tvStartDate=dialog.findViewById(R.id.tvStartDate);
        final TextView tvEndDate=dialog.findViewById(R.id.tvEndDate);
        spnDate=dialog.findViewById(R.id.spnDate);
        setSpinnerAdapter();
        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("MM");
        String month = dateFormat.format(calendar.getTime());
        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        date=calendar.getTime();
        currentDate= dateFormat.format(date);

        calendar.add(Calendar.DATE, 0);
        monthCheck(month);
        calendar.add(Calendar.MONTH, Integer.parseInt(binding.edNoOfInstallment.getText().toString())-1);
        calendar.add(Calendar.YEAR, YEAR);

        futureDate =dateFormat.format(calendar.getTime());
        tvStartDate.setText(currentDate);
        tvEndDate.setText((futureDate));
        spnDate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        dateFormat=new SimpleDateFormat("dd/MM/yyyy");
                        currentDate= dateFormat.format(date);
                        futureDate =dateFormat.format(calendar.getTime());
                        tvStartDate.setText(currentDate);
                        tvEndDate.setText((futureDate));
                        break;
                    case 1:
                        dateFormat=new SimpleDateFormat(" dd/MMM/yyyy");
                        currentDate= dateFormat.format(date);
                        futureDate =dateFormat.format(calendar.getTime());
                        tvStartDate.setText(currentDate);
                        tvEndDate.setText((futureDate));
                        break;
                    case 2:
                        dateFormat=new SimpleDateFormat("dd/MM/yy");
                        currentDate= dateFormat.format(date);
                        futureDate =dateFormat.format(calendar.getTime());
                        tvStartDate.setText(currentDate);
                        tvEndDate.setText((futureDate));
                        break;
                    case 3:
                        dateFormat=new SimpleDateFormat("yy/MM/dd");
                        currentDate= dateFormat.format(date);
                        futureDate =dateFormat.format(calendar.getTime());
                        tvStartDate.setText(currentDate);
                        tvEndDate.setText((futureDate));
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        dialog.show();
    }

    private void monthCheck(String month) {
        if ((Integer.parseInt(binding.edNoOfInstallment.getText().toString())+Integer.parseInt(month))>12&&(Integer.parseInt(binding.edNoOfInstallment.getText().toString())+Integer.parseInt(month))<24){
            YEAR=0;
        }else if ((Integer.parseInt(binding.edNoOfInstallment.getText().toString())+Integer.parseInt(month))>24&&(Integer.parseInt(binding.edNoOfInstallment.getText().toString())+Integer.parseInt(month))<36){
            YEAR=1;
        }else if ((Integer.parseInt(binding.edNoOfInstallment.getText().toString())+Integer.parseInt(month))>36&&(Integer.parseInt(binding.edNoOfInstallment.getText().toString())+Integer.parseInt(month))<48){
            YEAR=2;
        }else if ((Integer.parseInt(binding.edNoOfInstallment.getText().toString())+Integer.parseInt(month))>48&&(Integer.parseInt(binding.edNoOfInstallment.getText().toString())+Integer.parseInt(month))<60){
            YEAR=3;
        }else if ((Integer.parseInt(binding.edNoOfInstallment.getText().toString())+Integer.parseInt(month))>60&&(Integer.parseInt(binding.edNoOfInstallment.getText().toString())+Integer.parseInt(month))<72){
            YEAR=4;
        }else if ((Integer.parseInt(binding.edNoOfInstallment.getText().toString())+Integer.parseInt(month))>72&&(Integer.parseInt(binding.edNoOfInstallment.getText().toString())+Integer.parseInt(month))<84){
            YEAR=5;
        }else if ((Integer.parseInt(binding.edNoOfInstallment.getText().toString())+Integer.parseInt(month))>84&&(Integer.parseInt(binding.edNoOfInstallment.getText().toString())+Integer.parseInt(month))<96){
            YEAR=6;
        }else {
            YEAR=0;
        }
    }
    private void setSpinnerAdapter() {
        String[] items = new String[]{"DD/MM/YYYY","DD/MMM/YYYY","DD/MM/YY","YY/MM/DD"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spnDate.setAdapter(adapter);
    }

    private void clearDetails() {
        binding.edLoanAmount.setText("");
        binding.edNoOfInstallment.setText("");
        binding.edRateOfInterest.setText("");
        binding.edPrePayment.setText("6");
        binding.btnTime.setVisibility(View.GONE);
    }

    private void checkDetails() {
        if (CommonUtils.isEmptyEditText(binding.edLoanAmount) && CommonUtils.isNotNull(binding.edLoanAmount)) {
            binding.tilLoanAmount.setError(getString(R.string.err_loan_amount));
        } else if (CommonUtils.isEmptyEditText(binding.edRateOfInterest) && CommonUtils.isNotNull(binding.edRateOfInterest)) {
            binding.tilLoanAmount.setError("");
            binding.tilRateOfInterest.setError(getString(R.string.err_interest));
        } else if (CommonUtils.isEmptyEditText(binding.edNoOfInstallment) && CommonUtils.isNotNull(binding.edNoOfInstallment)) {
            binding.tilLoanAmount.setError("");
            binding.tilRateOfInterest.setError("");
            binding.tilNoOfInstallment.setError(getString(R.string.err_installment));
        }else if (Integer.parseInt(binding.edNoOfInstallment.getText().toString()) <= 6 || Integer.parseInt(binding.edNoOfInstallment.getText().toString()) >90) {
            binding.tilLoanAmount.setError("");
            binding.tilRateOfInterest.setError("");
            binding.tilNoOfInstallment.setError("No of installment should be greater than 6 and below 90");
        }  else if (CommonUtils.isEmptyEditText(binding.edPrePayment) && CommonUtils.isNotNull(binding.edPrePayment)) {
            binding.tilLoanAmount.setError("");
            binding.tilRateOfInterest.setError("");
            binding.tilNoOfInstallment.setError("");
            binding.tilPrePayment.setError(getString(R.string.err_prepayment));
        } else if (Integer.parseInt(binding.edPrePayment.getText().toString()) < 6 || Integer.parseInt(binding.edPrePayment.getText().toString()) >(Integer.parseInt(binding.edNoOfInstallment.getText().toString()) - 1)) {
            binding.tilLoanAmount.setError("");
            binding.tilRateOfInterest.setError("");
            binding.tilNoOfInstallment.setError("");
            binding.btnTime.setVisibility(View.GONE);
            binding.tilPrePayment.setError(getString(R.string.err_prepayment_month));
        } else {
            binding.tilLoanAmount.setError("");
            binding.tilRateOfInterest.setError("");
            binding.tilNoOfInstallment.setError("");
            binding.tilPrePayment.setError("");

            if (count == 1) {
                Toast.makeText(this, "enabled", Toast.LENGTH_SHORT).show();
                Intent installmentIntent=new Intent(this, InstallationViewActivity.class);
                installmentIntent.putExtra("Loan Amount",Double.parseDouble(binding.edLoanAmount.getText().toString()));
                installmentIntent.putExtra("Rate of interest",Double.parseDouble(binding.edRateOfInterest.getText().toString()));
                installmentIntent.putExtra("Installment",Double.parseDouble(binding.edNoOfInstallment.getText().toString()));
                startActivity(installmentIntent);
            }
            count=0;
            binding.btnTime.setVisibility(View.VISIBLE);
            count++;
        }
    }
}
