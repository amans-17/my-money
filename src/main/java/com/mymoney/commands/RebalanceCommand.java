package com.mymoney.commands;

import java.util.List;

import com.mymoney.dto.CommandOutput;
import com.mymoney.services.RebalanceService;

public class RebalanceCommand implements Command {
    RebalanceService rebalanceService;

    public RebalanceCommand(RebalanceService rebalanceService) {
        this.rebalanceService = rebalanceService;
    }

    @Override
    public void execute(List<String> tokens) {
        CommandOutput co = rebalanceService.getRebalance();
        System.out.println(co);        
    }

    
}