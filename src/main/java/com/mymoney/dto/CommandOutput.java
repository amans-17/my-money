package com.mymoney.dto;

import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@NoArgsConstructor
public class CommandOutput {
    private String message;
    private Double equityAmount;
    private Double debtAmount;
    private Double goldAmount;

    public CommandOutput(Double equityAmount, Double debtAmount, Double goldAmount) {
        this.equityAmount = equityAmount;
        this.debtAmount = debtAmount;
        this.goldAmount = goldAmount;
    }

    @Override
    public String toString() {
        if(message == null) {
            return (this.equityAmount).intValue() + " " +
            (this.debtAmount).intValue() + " " +
            (this.goldAmount).intValue();
        } else {
            return message;
        }
    }
    @Override 
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CommandOutput)) {
            return false;
        }
        CommandOutput co = (CommandOutput) obj;
        return this.toString() == co.toString();
    }
}
