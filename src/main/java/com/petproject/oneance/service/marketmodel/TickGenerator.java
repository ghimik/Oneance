package com.petproject.oneance.service.marketmodel;

import com.petproject.oneance.model.Company;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Collection;
import java.util.List;

// TODO вынести на отдельный тред, jdbc разобраться, кэширование исторических данных
// TODO потенциально - факторы через связный список, стратегия формирования, абстрактная фабрика
public class TickGenerator {

    private List<Company> observableCompanies;


    public void tick() {
        // Command command = commandFactory.create();
        // commandExecutor.execute(commandConfigurer.formulateCommand(command));

    }

}

