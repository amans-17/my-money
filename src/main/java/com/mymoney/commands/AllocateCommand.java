package com.mymoney.commands;

import java.util.List;

import com.mymoney.exceptions.InvalidCommandException;
import com.mymoney.services.AllocateService;

public class AllocateCommand implements Command {

    private AllocateService allocateService;

    public AllocateCommand(AllocateService allocateService) {
        this.allocateService = allocateService;
    }

    @Override
    public void execute(List<String> tokens) {
        try {
        Double equityAmount = Double.parseDouble(tokens.get(1));
        Double debtAmount = Double.parseDouble(tokens.get(2));
        Double goldAmount = Double.parseDouble(tokens.get(3));
        allocateService.initPortfolio(equityAmount, debtAmount, goldAmount); 
        } catch (InvalidCommandException e) {
            System.out.println(e); }   
    }
    
}
