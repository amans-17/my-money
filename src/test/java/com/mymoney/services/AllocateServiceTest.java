package com.mymoney.services;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.mymoney.exceptions.InvalidCommandException;
import com.mymoney.repositories.MonthlyPortfolio;
import com.mymoney.repositories.PortfolioRepository;

import org.junit.jupiter.api.BeforeEach;    
    
public class AllocateServiceTest {

    private PortfolioRepository portfolioRepository;
    private AllocateService allocateService;

    @BeforeEach
    public void setup(){
        portfolioRepository = new PortfolioRepository();
        allocateService = new AllocateService(portfolioRepository);
    }
        
    @Test
    public void emptyRepository_put_InitialInvestMent() {
        try {
            allocateService.initPortfolio(700.0, 200.0, 100.0);
            Double expectedGoldValue = 100.0;
            Double expectedEquitySplit = 0.70;
            MonthlyPortfolio mp = portfolioRepository.getPortfolioForMonth(0);
            assertEquals(expectedGoldValue, mp.getGoldValue());
            assertEquals(expectedEquitySplit, mp.getEquitySplit());
            assertEquals(0, portfolioRepository.getMonthsPassed());
        } catch(InvalidCommandException e) {
            String exceptionMessage = e.toString();
            assertTrue(exceptionMessage.equals("Invalid Command"));
        }       
    }

}
    