package com.mymoney.commands;

import java.util.List;

import com.mymoney.exceptions.InvalidCommandException;
import com.mymoney.services.ChangeService;

public class ChangeCommand implements Command {
    ChangeService changeService;
    
    public ChangeCommand(ChangeService changeService) {
        this.changeService = changeService;
    }

    private Double getPercentage(String s) {
        String[] strs = s.split("%");
        return Double.parseDouble(strs[0])/100;
    }

    @Override
    public void execute(List<String> tokens) {
        
        Double equityChange = getPercentage(tokens.get(1));
        Double debtChange = getPercentage(tokens.get(2));
        Double goldChange = getPercentage(tokens.get(3));
        String month = tokens.get(4);
        try {
            changeService.updateValues(equityChange, debtChange, goldChange, month);
        } catch (InvalidCommandException e) {
            System.out.println(e);
        }
    }
    
}
