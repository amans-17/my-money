package com.mymoney.services;

import com.mymoney.exceptions.InvalidCommandException;
import com.mymoney.repositories.MonthlyPortfolio;
import com.mymoney.repositories.PortfolioRepository;

public class AllocateService {

    private PortfolioRepository portfolioRepository;

    public AllocateService(PortfolioRepository portfolioRepository) {
        this.portfolioRepository = portfolioRepository;
    }

    public void initPortfolio(Double equityAmount, Double debtAmount, Double goldAmount) throws InvalidCommandException{
        Double totalAmount = equityAmount + debtAmount + goldAmount;
        MonthlyPortfolio mp = new MonthlyPortfolio();
        if(portfolioRepository.getMonthsPassed() != -1) {
            throw new InvalidCommandException();
        } 
        mp.createEquityFund(equityAmount, -1.0, equityAmount/totalAmount);
        mp.createDebtFund(debtAmount, -1.0, debtAmount/totalAmount);
        mp.createGoldFund(goldAmount, -1.0, goldAmount/totalAmount);
        portfolioRepository.addPortfolio(mp);
        return;
    }

}
