package com.petproject.oneance.service.marketmodel;

public class CommandFactory {

    private final String company;

    public CommandFactory(String companyName) {
        this.company = companyName;
    }

    public Command create() {
        return new InitialCommand(company);
    }

}
