package com.mymoney.config;

import com.mymoney.commands.AllocateCommand;
import com.mymoney.commands.BalanceCommand;
import com.mymoney.commands.ChangeCommand;
import com.mymoney.commands.CommandInvoker;
import com.mymoney.commands.RebalanceCommand;
import com.mymoney.commands.SIPCommand;
import com.mymoney.repositories.PortfolioRepository;
import com.mymoney.services.AllocateService;
import com.mymoney.services.BalanceService;
import com.mymoney.services.ChangeService;
import com.mymoney.services.RebalanceService;
import com.mymoney.services.SIPService;

public class ApplicationConfig {

    private PortfolioRepository portfolioRepository = new PortfolioRepository();

    private AllocateService allocateService = new AllocateService(portfolioRepository);
    private SIPService sipService = new SIPService(portfolioRepository);
    private ChangeService changeService = new ChangeService(portfolioRepository);
    private BalanceService balanceService = new BalanceService(portfolioRepository);
    private RebalanceService rebalanceService = new RebalanceService(portfolioRepository);

    private AllocateCommand allocateCommand = new AllocateCommand(allocateService);
    private SIPCommand sipCommand = new SIPCommand(sipService);
    private ChangeCommand changeCommand = new ChangeCommand(changeService);
    private BalanceCommand balanceCommand = new BalanceCommand(balanceService);
    private RebalanceCommand rebalanceCommand = new RebalanceCommand(rebalanceService);

    private final CommandInvoker commandInvoker = new CommandInvoker();

    public CommandInvoker getCommandInvoker() {
        commandInvoker.register("ALLOCATE", allocateCommand);
        commandInvoker.register("SIP", sipCommand);
        commandInvoker.register("CHANGE", changeCommand);
        commandInvoker.register("BALANCE", balanceCommand);
        commandInvoker.register("REBALANCE", rebalanceCommand);
        return commandInvoker;
    }


}
