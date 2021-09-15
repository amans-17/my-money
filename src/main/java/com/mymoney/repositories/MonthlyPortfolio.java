package com.mymoney.repositories;

import com.mymoney.entities.DebtFund;
import com.mymoney.entities.EquityFund;
import com.mymoney.entities.Fund;
import com.mymoney.entities.GoldFund;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MonthlyPortfolio {
    private Fund equityFund;
    private Fund debtFund;
    private Fund goldFund;

    public MonthlyPortfolio() {
    }


    public void createEquityFund(Double amount, Double sip, Double split) {
        this.equityFund = new EquityFund(amount, sip, split);
    }

    public void createDebtFund(Double amount, Double sip, Double split) {
        this.debtFund = new DebtFund(amount, sip, split);  
    }
    
    public void createGoldFund(Double amount, Double sip, Double split) {
        this.goldFund  = new GoldFund(amount, sip, split);
    }

    public void setEquitySIP(Double amount) {
        this.equityFund.setSIP(amount);
    }

    public void setDebtSIP(Double amount) {
        this.debtFund.setSIP(amount);
    }

    public void setGoldSIP(Double amount) {
        this.goldFund.setSIP(amount);
    }

    public void updateEquityValue(Double value) {
        this.equityFund.setValue(value);
    }

    public void updateDebtValue(Double value) {
        this.debtFund.setValue(value);
    }

    public void updateGoldValue(Double value) {
        this.goldFund.setValue(value);
    }

    public Double getEquityValue() {
        return this.equityFund.getValue();
    }

    public Double getDebtValue() {
        return this.debtFund.getValue();
    }

    public Double getGoldValue() {
        return this.goldFund.getValue();
    }
    
    public Double getEquitySIP() {
         return this.equityFund.getSIP();
    }

    public Double getDebtSIP() {
        return this.debtFund.getSIP();
   }

   public Double getGoldSIP() {
    return this.goldFund.getSIP();
    }

    public Double getEquitySplit() {
        return this.equityFund.getSplit();
    }

    public Double getDebtSplit() {
        return this.debtFund.getSplit();
    }

    public Double getGoldSplit() {
        return this.goldFund.getSplit();
    }
}
