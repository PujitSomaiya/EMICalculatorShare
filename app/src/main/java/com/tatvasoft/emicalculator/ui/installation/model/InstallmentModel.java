package com.tatvasoft.emicalculator.ui.installation.model;

public class InstallmentModel {

    private Double installmentNumber;
    private Double openingNumber;
    private Double principle;
    private Double Doubleerest;
    private Double EMI;
    private Double rateOfInterest;
    private Double loanAmount;
    private Double year;

    public Double getYear() {
        return year++;
    }

    public void setYear(Double year) {
        this.year = year;
    }

    public InstallmentModel(Double installmentNumber,  Double Doubleerest, Double rateOfInterest, Double loanAmount,Double EMI,Double principle,Double openingNumber) {
        this.installmentNumber = installmentNumber;
        this.principle = principle;
        this.openingNumber = openingNumber;
        this.EMI = EMI;
        this.Doubleerest = Doubleerest;
        this.rateOfInterest = rateOfInterest;
        this.loanAmount = loanAmount;
    }


    public Double getInstallmentNumber() {
        return installmentNumber;
    }

    public Double getRateOfInterest() {
        return rateOfInterest;
    }

    public void setRateOfInterest(Double rateOfInterest) {
        this.rateOfInterest = rateOfInterest;
    }

    public Double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(Double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public void setInstallmentNumber(Double installmentNumber) {
        this.installmentNumber = installmentNumber;
    }

    public Double getOpeningNumber() {
        return openingNumber;
    }

    public void setOpeningNumber(Double openingNumber) {
        this.openingNumber = openingNumber;
    }

    public Double getPrinciple() {
        return principle;
    }

    public void setPrinciple(Double principle) {
        this.principle = principle;
    }

    public Double getInterest() {
        return Doubleerest;
    }

    public void setInterest(Double Doubleerest) {
        this.Doubleerest = Doubleerest;
    }

    public Double getEMI() {
        return EMI;
    }

    public void setEMI(Double EMI) {
        this.EMI = EMI;
    }


}
