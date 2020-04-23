package com.tatvasoft.emicalculator.ui.home.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.tatvasoft.emicalculator.R;
import com.tatvasoft.emicalculator.util.CommonUtils;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private TextInputLayout tilLoanAmount, tilRateOfInterest, tilNoOfInstallment, tilPrePayment;
    private EditText edLoanAmount, edRateOfInterest, edNoOfInstallment, edPrePayment;
    private Button btnSubmit, btnClear, btnTime;
    private int count = 0;

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

    private void showDialog() {

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
        } else if (CommonUtils.isEmptyEditText(edPrePayment) && CommonUtils.isNotNull(edPrePayment)) {
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
