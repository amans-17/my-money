package com.mymoney.entities;

public class EquityFund extends Fund { 

    private final String name = "Equity";

    public EquityFund(Double value, Double sip, Double split) {
        super(value, sip, split);
    }
    
    @Override
    public String getName() {
        return this.name;
    }
}
