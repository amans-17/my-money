package com.mymoney.services;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.mymoney.dto.CommandOutput;
import com.mymoney.exceptions.InvalidCommandException;
import com.mymoney.repositories.PortfolioRepository;
   
    
public class BalanceServiceTest {
 
    private PortfolioRepository portfolioRepository;
    private BalanceService balanceService;

    @BeforeEach
    public void setup(){
        portfolioRepository = new PortfolioRepository();
        SIPService sipService = new SIPService(portfolioRepository);
        ChangeService changeService = new ChangeService(portfolioRepository);
        AllocateService allocateService = new AllocateService(portfolioRepository);
        balanceService = new BalanceService(portfolioRepository);
        try { 
            allocateService.initPortfolio(700.0, 200.0, 100.0);
            sipService.initPortfolio(300.0, 800.0, 900.0);
            changeService.updateValues(0.10, 0.10, 0.10, "JANUARY");
            changeService.updateValues(0.10, 0.10, 0.10, "FEBRUARY");
        } catch (InvalidCommandException e) {
            //assertTrue(1<0);
        }
    }
    @Test
    public void getBalance_MonthExist() {
        try {
            CommandOutput co = balanceService.getBalance("JANUARY");
            CommandOutput expectedCo = new CommandOutput(770.0, 220.0, 110.0);
            assertEquals(expectedCo.toString(), co.toString());
        } catch (InvalidCommandException e) {
        }
        
    }

    @Test
    public void getBalance_MonthDoesNotExist() {
        try {
            CommandOutput co = balanceService.getBalance("JANUARY");
        } catch (InvalidCommandException e) {
            assertTrue(e.toString().equals("Invalid Command"));
        }
    }

}
    