package com.petproject.oneance.service.marketmodel;

public class InitialCommand implements Command {

    private final String company;

    public InitialCommand(String company) {
        this.company = company;
    }


    @Override
    public String getCompanyName() {
        return company;
    }

    @Override
    public Double getChanges() {
        return 0.0;
    }
}
