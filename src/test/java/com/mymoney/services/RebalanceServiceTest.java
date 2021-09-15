package com.mymoney.services;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.mymoney.dto.CommandOutput;
import com.mymoney.exceptions.InvalidCommandException;
import com.mymoney.repositories.PortfolioRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;    
    
public class RebalanceServiceTest {

    
    private PortfolioRepository portfolioRepository;
    private RebalanceService rebalanceService;

    @BeforeEach
    public void setup(){
        portfolioRepository = new PortfolioRepository();
        SIPService sipService = new SIPService(portfolioRepository);
        ChangeService changeService = new ChangeService(portfolioRepository);
        AllocateService allocateService = new AllocateService(portfolioRepository);
        rebalanceService = new RebalanceService(portfolioRepository);
        try { 
            allocateService.initPortfolio(700.0, 200.0, 100.0);
            sipService.initPortfolio(300.0, 800.0, 900.0);
            changeService.updateValues(0.10, 0.10, 0.10, "JANUARY");
            changeService.updateValues(0.10, 0.10, 0.10, "FEBRUARY");
            changeService.updateValues(0.10, 0.10, 0.10, "MARCH");
            changeService.updateValues(0.10, 0.10, 0.10, "APRIL");
            changeService.updateValues(0.10, 0.10, 0.10, "MAY");
            changeService.updateValues(0.10, 0.10, 0.10, "JUNE");
        } catch (InvalidCommandException e) {
            //assertTrue(1<0);
        }
    }
    @Test
    public void getRebalanceTest() {
        CommandOutput co = rebalanceService.getRebalance();
        CommandOutput expectedCo = new CommandOutput(10637.0, 3039.0, 1519.0);
        assertEquals(expectedCo.toString(), co.toString());        
    }
}
    