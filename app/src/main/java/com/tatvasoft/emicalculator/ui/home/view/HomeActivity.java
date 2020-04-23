package com.tatvasoft.emicalculator.ui.home.view;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
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

import com.google.android.material.textfield.TextInputLayout;
import com.tatvasoft.emicalculator.R;
import com.tatvasoft.emicalculator.util.CommonUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private TextInputLayout tilLoanAmount, tilRateOfInterest, tilNoOfInstallment, tilPrePayment;
    private EditText edLoanAmount, edRateOfInterest, edNoOfInstallment, edPrePayment;
    private Button btnSubmit, btnClear, btnTime;
    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    private int count = 0,YEAR;
    private Spinner spnDate;
    final Context context=this;
    private String futureDate,currentDate;
    private Date date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initControls();
    }

    private void initControls() {

        initListeners();
    }

    private void initListeners() {
        tilLoanAmount = findViewById(R.id.tilLoanAmount);
        tilRateOfInterest = findViewById(R.id.tilRateOfInterest);
        tilNoOfInstallment = findViewById(R.id.tilNoOfInstallment);
        tilPrePayment = findViewById(R.id.tilPrePayment);
        edLoanAmount = findViewById(R.id.edLoanAmount);
        edRateOfInterest = findViewById(R.id.edRateOfInterest);
        edNoOfInstallment = findViewById(R.id.edNoOfInstallment);
        edPrePayment = findViewById(R.id.edPrePayment);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnClear = findViewById(R.id.btnClear);
        btnTime = findViewById(R.id.btnTime);
        btnSubmit.setOnClickListener(this);
        btnClear.setOnClickListener(this);
        btnTime.setOnClickListener(this);
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
        spnDate=dialog.findViewById(R.id.spnDate);
        final TextView tvStartDate=dialog.findViewById(R.id.tvStartDate);
        final TextView tvEndDate=dialog.findViewById(R.id.tvEndDate);
        setSpinnerAdapter();
        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("MM");
        String month = dateFormat.format(calendar.getTime());
        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        date=calendar.getTime();
        currentDate= dateFormat.format(date);

        calendar.add(Calendar.DATE, 0);
        monthCheck(month);
        calendar.add(Calendar.MONTH, Integer.parseInt(edNoOfInstallment.getText().toString()));
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
        if ((Integer.parseInt(edNoOfInstallment.getText().toString())+Integer.parseInt(month))>=12&&(Integer.parseInt(edNoOfInstallment.getText().toString())+Integer.parseInt(month))<24){
            YEAR=1;
        }else if ((Integer.parseInt(edNoOfInstallment.getText().toString())+Integer.parseInt(month))>=24&&(Integer.parseInt(edNoOfInstallment.getText().toString())+Integer.parseInt(month))<36){
            YEAR=2;
        }else if ((Integer.parseInt(edNoOfInstallment.getText().toString())+Integer.parseInt(month))>=36&&(Integer.parseInt(edNoOfInstallment.getText().toString())+Integer.parseInt(month))<48){
            YEAR=3;
        }else if ((Integer.parseInt(edNoOfInstallment.getText().toString())+Integer.parseInt(month))>=48&&(Integer.parseInt(edNoOfInstallment.getText().toString())+Integer.parseInt(month))<60){
            YEAR=4;
        }else if ((Integer.parseInt(edNoOfInstallment.getText().toString())+Integer.parseInt(month))>=60&&(Integer.parseInt(edNoOfInstallment.getText().toString())+Integer.parseInt(month))<72){
            YEAR=5;
        }else if ((Integer.parseInt(edNoOfInstallment.getText().toString())+Integer.parseInt(month))>=72&&(Integer.parseInt(edNoOfInstallment.getText().toString())+Integer.parseInt(month))<84){
            YEAR=6;
        }else if ((Integer.parseInt(edNoOfInstallment.getText().toString())+Integer.parseInt(month))>=84&&(Integer.parseInt(edNoOfInstallment.getText().toString())+Integer.parseInt(month))<96){
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
        edLoanAmount.setText("");
        edNoOfInstallment.setText("");
        edRateOfInterest.setText("");
        edPrePayment.setText("6");
        btnTime.setVisibility(View.GONE);
    }

    private void checkDetails() {
        if (CommonUtils.isEmptyEditText(edLoanAmount) && CommonUtils.isNotNull(edLoanAmount)) {
            tilLoanAmount.setError(getString(R.string.err_loan_amount));
        } else if (CommonUtils.isEmptyEditText(edRateOfInterest) && CommonUtils.isNotNull(edRateOfInterest)) {
            tilLoanAmount.setError("");
            tilRateOfInterest.setError(getString(R.string.err_interest));
        } else if (CommonUtils.isEmptyEditText(edNoOfInstallment) && CommonUtils.isNotNull(edNoOfInstallment)) {
            tilLoanAmount.setError("");
            tilRateOfInterest.setError("");
            tilNoOfInstallment.setError(getString(R.string.err_installment));
        }else if (Integer.parseInt(edNoOfInstallment.getText().toString()) <= 6 || Integer.parseInt(edNoOfInstallment.getText().toString()) >90) {
            tilLoanAmount.setError("");
            tilRateOfInterest.setError("");
            tilNoOfInstallment.setError("No of installment should be greater than 6 and below 90");
        }  else if (CommonUtils.isEmptyEditText(edPrePayment) && CommonUtils.isNotNull(edPrePayment)) {
            tilLoanAmount.setError("");
            tilRateOfInterest.setError("");
            tilNoOfInstallment.setError("");
            tilPrePayment.setError(getString(R.string.err_prepayment));
        } else if (Integer.parseInt(edPrePayment.getText().toString()) < 6 || Integer.parseInt(edPrePayment.getText().toString()) >(Integer.parseInt(edNoOfInstallment.getText().toString()) - 1)) {
            tilLoanAmount.setError("");
            tilRateOfInterest.setError("");
            tilNoOfInstallment.setError("");
            btnTime.setVisibility(View.GONE);
            tilPrePayment.setError(getString(R.string.err_prepayment_month));
        } else {
            tilLoanAmount.setError("");
            tilRateOfInterest.setError("");
            tilNoOfInstallment.setError("");
            tilPrePayment.setError("");

            if (count == 1) {
                Toast.makeText(this, "enabled", Toast.LENGTH_SHORT).show();
            }
            count=0;
            btnTime.setVisibility(View.VISIBLE);
            count++;
        }
    }
}
