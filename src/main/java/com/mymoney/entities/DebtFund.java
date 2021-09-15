package com.mymoney.entities;

public class DebtFund extends Fund{ 

    private final String name = "Debt";
    
    public DebtFund(Double value, Double sip, Double split) {
        super(value, sip, split);
    }

    @Override
    public String getName() {
        return this.name;
    }
}
