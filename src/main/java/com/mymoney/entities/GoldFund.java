package com.mymoney.entities;

public class GoldFund extends Fund{ 

    private final String name = "Gold";

    public GoldFund(Double value, Double sip, Double split) {
        super(value, sip, split);
    }

    @Override
    public String getName() {
        return this.name;
    }
}
