package com.mymoney.repositories;

import java.util.ArrayList;
import java.util.List;

public class PortfolioRepository {
    public List<MonthlyPortfolio> portfolio;

    public PortfolioRepository() {
        portfolio = new ArrayList<MonthlyPortfolio>();
    }

    public MonthlyPortfolio getPortfolioForMonth(int month){
        return portfolio.get(month);
    }

    public void addPortfolio(MonthlyPortfolio monthlyPortfolio) {
        portfolio.add(monthlyPortfolio);
    }

    public void updatePortfolioForMonth(MonthlyPortfolio monthlyPortfolio, int month) {
        portfolio.set(month, monthlyPortfolio);
    }

    public int getMonthsPassed() {
        return portfolio.size()-1;
    }
}
