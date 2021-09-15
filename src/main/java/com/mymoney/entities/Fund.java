package com.mymoney.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public abstract class Fund {

    protected Double value;
    protected Double SIP;
    protected Double split; 
    
    public abstract String getName();
}
