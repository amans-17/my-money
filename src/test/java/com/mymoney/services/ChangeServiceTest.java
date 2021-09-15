package com.mymoney.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.mymoney.exceptions.InvalidCommandException;
import com.mymoney.repositories.MonthlyPortfolio;
import com.mymoney.repositories.PortfolioRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ChangeServiceTest {

    private PortfolioRepository portfolioRepository;
    private SIPService sipService;
    private AllocateService allocateService;
    private ChangeService changeService;

    @BeforeEach
    public void setup() {
        portfolioRepository = new PortfolioRepository();
        sipService = new SIPService(portfolioRepository);
        changeService = new ChangeService(portfolioRepository);
        allocateService = new AllocateService(portfolioRepository);
        try { 
            allocateService.initPortfolio(700.0, 200.0, 100.0);
            sipService.initPortfolio(300.0, 800.0, 900.0);
        } catch (InvalidCommandException e) {
            //assertTrue(1<0);
        }
    }

    @Test
    public void normalChange_inorder_firstMonth() {
        try {
            changeService.updateValues(0.10, 0.10, 0.10, "JANUARY");
            MonthlyPortfolio mp = portfolioRepository.getPortfolioForMonth(1);
            Double expectedEquityValue = 770.0;
            Double expectedDebtValue = 220.0;
            Double expectedGoldValue = 110.0;
            assertEquals(expectedEquityValue, mp.getEquityValue());
            assertEquals(expectedDebtValue, mp.getDebtValue());
            assertEquals(expectedGoldValue, mp.getGoldValue());
        } catch (InvalidCommandException e) {
            String exceptionMessage = e.toString();
           assertTrue(exceptionMessage.equals("Invalid Command"));
        }
        
    }

    @Test
    public void normalChange_inorder_secondMonth() {
        try {
            changeService.updateValues(0.10, 0.10, 0.10, "JANUARY");
            changeService.updateValues(0.10, 0.10, 0.10, "FEBRUARY");
            MonthlyPortfolio mp = portfolioRepository.getPortfolioForMonth(2);
            Double expectedEquityValue = 1177.0;
            Double expectedDebtValue = 1122.0;
            Double expectedGoldValue = 1111.0;
            assertEquals(expectedEquityValue, mp.getEquityValue());
            assertEquals(expectedDebtValue, mp.getDebtValue());
            assertEquals(expectedGoldValue, mp.getGoldValue());
        } catch (InvalidCommandException e) {
            String exceptionMessage = e.toString();
           assertTrue(exceptionMessage.equals("Invalid Command"));
        }
        
    }

    @Test
    public void Change_outOforder() {
        try {
            changeService.updateValues(10.0, 10.0, 10.0, "JANUARY");
            changeService.updateValues(10.0, 10.0, 10.0, "MARCH");
            MonthlyPortfolio mp = portfolioRepository.getPortfolioForMonth(1);
            Double expectedEquityValue = 1140.0;
            Double expectedDebtValue = 1040.0;
            Double expectedGoldValue = 1020.0;
            assertEquals(expectedEquityValue, mp.getEquityValue());
            assertEquals(expectedDebtValue, mp.getDebtValue());
            assertEquals(expectedGoldValue, mp.getGoldValue());
        } catch (InvalidCommandException e) {
            String exceptionMessage = e.toString();
           assertTrue(exceptionMessage.equals("Invalid Command"));
        }
        
    }
}
    