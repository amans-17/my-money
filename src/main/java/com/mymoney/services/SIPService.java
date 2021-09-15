package com.mymoney.services;

import com.mymoney.exceptions.InvalidCommandException;
import com.mymoney.repositories.MonthlyPortfolio;
import com.mymoney.repositories.PortfolioRepository;

public class SIPService {
    
    private PortfolioRepository portfolioRepository;

    public SIPService(PortfolioRepository portfolioRepository) {
        this.portfolioRepository = portfolioRepository;
    }


    public void initPortfolio(Double equitySIP, Double debtSIP, Double goldSIP) throws InvalidCommandException {
        if(portfolioRepository.getMonthsPassed() != 0) {   //No funds Allocated yet
            throw new InvalidCommandException();
        }
        MonthlyPortfolio mp = portfolioRepository.getPortfolioForMonth(0); //  0 represents initial month 
        if(mp.getEquitySIP() != -1.0) {  //SIPs already set
            throw new InvalidCommandException();
        }
        mp.setEquitySIP(equitySIP);
        mp.setDebtSIP(debtSIP);
        mp.setGoldSIP(goldSIP);
        return;
    }
}
