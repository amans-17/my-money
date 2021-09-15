package com.mymoney.services;

import com.mymoney.dto.CommandOutput;
import com.mymoney.repositories.MonthlyPortfolio;
import com.mymoney.repositories.PortfolioRepository;

public class RebalanceService {
    private PortfolioRepository portfolioRepository;

    public RebalanceService(PortfolioRepository portfolioRepository) {
        this.portfolioRepository = portfolioRepository;
    }

    public CommandOutput getRebalance() {
        MonthlyPortfolio mp;
        CommandOutput co = new CommandOutput();
        int monthsPassed = portfolioRepository.getMonthsPassed();
        if(monthsPassed < 6) {
            co.setMessage("CANNOT_REBALANCE");
            return co;
        } else if(monthsPassed == 12) {
            mp = portfolioRepository.getPortfolioForMonth(12);
        } else {
            mp = portfolioRepository.getPortfolioForMonth(6);
        }
        co.setDebtAmount(mp.getDebtValue());
        co.setEquityAmount(mp.getEquityValue());
        co.setGoldAmount(mp.getGoldValue());
        return co;
    }
}
