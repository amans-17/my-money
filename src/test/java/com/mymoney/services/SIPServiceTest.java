package com.mymoney.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.mymoney.exceptions.InvalidCommandException;
import com.mymoney.repositories.MonthlyPortfolio;
import com.mymoney.repositories.PortfolioRepository;    
    
public class SIPServiceTest {

    private PortfolioRepository portfolioRepository;
    private SIPService sipService;
   

    @BeforeEach
    public void setup() {
        portfolioRepository = new PortfolioRepository();
        sipService = new SIPService(portfolioRepository);
        AllocateService allocateService = new AllocateService(portfolioRepository);
        try { 
            allocateService.initPortfolio(700.0, 200.0, 100.0);
        } catch (InvalidCommandException e) {
            //assertTrue(1<0);
        }
    }
        
    @Test
    public void initSip_notSet() {
          MonthlyPortfolio mp = portfolioRepository.getPortfolioForMonth(0);
          Double expectedEquitySIP = -1.0;
          Double expectedDebtSIP = -1.0;
          Double expectedGoldSIP = -1.0;
          assertEquals(expectedEquitySIP, mp.getEquitySIP());
          assertEquals(expectedDebtSIP, mp.getDebtSIP());
          assertEquals(expectedGoldSIP, mp.getGoldSIP()); 
    }


    public void initSip_Set() {
        try {
            sipService.initPortfolio(300.0, 800.0, 900.0);
            Double expectedEquitySIP = 300.0;
            Double expectedDebtSIP = 800.0;
            Double expectedGoldSIP = 900.0;
            MonthlyPortfolio mp = portfolioRepository.getPortfolioForMonth(0);
            assertEquals(expectedEquitySIP, mp.getEquitySIP());
            assertEquals(expectedDebtSIP, mp.getDebtSIP());
            assertEquals(expectedGoldSIP, mp.getGoldSIP());
        } catch(InvalidCommandException e) {
          String exceptionMessage = e.toString();
          assertTrue(exceptionMessage.equals("Invalid Command"));
       } 
      }

}
    