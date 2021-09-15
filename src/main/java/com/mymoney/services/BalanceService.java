package com.mymoney.services;

import java.time.Month;

import com.mymoney.dto.CommandOutput;
import com.mymoney.exceptions.InvalidCommandException;
import com.mymoney.repositories.MonthlyPortfolio;
import com.mymoney.repositories.PortfolioRepository;

public class BalanceService {

    private PortfolioRepository portfolioRepository;

    public BalanceService(PortfolioRepository portfolioRepository) {
        this.portfolioRepository = portfolioRepository;
    }

    private int getMonthNumber(String month) {
        return Month.valueOf(month).getValue();
    }

    public CommandOutput getBalance(String month) throws InvalidCommandException {
        CommandOutput co = new CommandOutput();
        int monthsPassed = portfolioRepository.getMonthsPassed();
        int monthNum = getMonthNumber(month);
        if(monthNum > monthsPassed) {
            throw new InvalidCommandException();
        }
        MonthlyPortfolio mp = portfolioRepository.getPortfolioForMonth(monthNum);
        co.setDebtAmount(mp.getDebtValue());
        co.setEquityAmount(mp.getEquityValue());
        co.setGoldAmount(mp.getGoldValue());
        return co;
    }
}
