package com.mymoney.services;

import java.time.Month;

import com.mymoney.exceptions.InvalidCommandException;
import com.mymoney.repositories.MonthlyPortfolio;
import com.mymoney.repositories.PortfolioRepository;

public class ChangeService {
    private PortfolioRepository portfolioRepository;

    public ChangeService(PortfolioRepository portfolioRepository) {
        this.portfolioRepository = portfolioRepository;
    }

    private int getMonthNumber(String month) {
        return Month.valueOf(month).getValue();
    }

    public void updateValues(Double equityChange, Double debtChange, Double goldChange, String month) throws InvalidCommandException {
        int lastMonth = portfolioRepository.getMonthsPassed();
        int currMonth = getMonthNumber(month);
        if(lastMonth + 1 != currMonth) {
            throw new InvalidCommandException();
        }
        MonthlyPortfolio lastMonthlyPortfolio  = portfolioRepository.getPortfolioForMonth(lastMonth);
        MonthlyPortfolio currenPortfolio = new MonthlyPortfolio();
        Double newEquityValue = lastMonthlyPortfolio.getEquityValue();
        Double newDebtValue = lastMonthlyPortfolio.getDebtValue();
        Double newGoldValue = lastMonthlyPortfolio.getGoldValue();
        if(currMonth >= 2) {
            newEquityValue += lastMonthlyPortfolio.getEquitySIP();
            newDebtValue += lastMonthlyPortfolio.getDebtSIP();
            newGoldValue += lastMonthlyPortfolio.getGoldSIP();
        }
        newEquityValue += (int)Math.floor(newEquityValue * equityChange);
        newDebtValue += (int)Math.floor(newDebtValue * debtChange); 
        newGoldValue += (int)Math.floor(newGoldValue * goldChange);
        
        if(currMonth == 6 || currMonth == 12) {
            Double totalValue = newEquityValue + newDebtValue + newGoldValue;
            newEquityValue = Math.floor(totalValue * lastMonthlyPortfolio.getEquitySplit());
            newDebtValue = Math.floor(totalValue * lastMonthlyPortfolio.getDebtSplit());
            newGoldValue = Math.floor(totalValue * lastMonthlyPortfolio.getGoldSplit());
        }
        currenPortfolio.createEquityFund(newEquityValue, lastMonthlyPortfolio.getEquitySIP(), lastMonthlyPortfolio.getEquitySplit());
        currenPortfolio.createDebtFund(newDebtValue, lastMonthlyPortfolio.getDebtSIP(), lastMonthlyPortfolio.getDebtSplit());
        currenPortfolio.createGoldFund(newGoldValue, lastMonthlyPortfolio.getGoldSIP(), lastMonthlyPortfolio.getGoldSplit());
        portfolioRepository.addPortfolio(currenPortfolio);
    }
}
