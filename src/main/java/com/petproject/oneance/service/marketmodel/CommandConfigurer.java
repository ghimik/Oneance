package com.petproject.oneance.service.marketmodel;

import java.util.Collection;
import java.util.List;

public class CommandConfigurer {

    private final Collection<MarketFactor> factors = List.of();

    public Command formulateCommand(Command initial) {
        Command command = initial;
        for (MarketFactor factor : factors) {
            command = factor.applyOn(initial);
        }
        return initial;
    }

}
