package com.mymoney.commands;

import java.util.List;

import com.mymoney.dto.CommandOutput;
import com.mymoney.exceptions.InvalidCommandException;
import com.mymoney.services.BalanceService;

public class BalanceCommand implements Command {
    
    BalanceService balanceService;

    public BalanceCommand(BalanceService balanceService) {
        this.balanceService = balanceService;
    }

    @Override
    public void execute(List<String> tokens) {
        try {
            CommandOutput co = balanceService.getBalance(tokens.get(1));
            System.out.println(co);
        } catch (InvalidCommandException e) {
            System.out.println(e);
        }        
    }

    

}
