package com.mymoney.commands;

import java.util.List;

public interface Command {
    void execute(List<String> tokens);
}
