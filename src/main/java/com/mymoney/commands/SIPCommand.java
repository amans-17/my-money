package com.mymoney.commands;

import java.util.List;

import com.mymoney.exceptions.InvalidCommandException;
import com.mymoney.services.SIPService;

public class SIPCommand implements Command {
    
    private SIPService sipService;
    
    public SIPCommand(SIPService sipService) {
        this.sipService = sipService;
    }

    @Override
    public void execute(List<String> tokens) {
        try {
            Double equitySIP = Double.parseDouble(tokens.get(1));
            Double debtSIP = Double.parseDouble(tokens.get(2));
            Double goldSIP = Double.parseDouble(tokens.get(3));
            sipService.initPortfolio(equitySIP, debtSIP, goldSIP);
        } catch (InvalidCommandException e) {
            System.out.println(e);
        }        
    }
}
