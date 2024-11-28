package com.fag;

import com.fag.infra.celcoin.CelcoinBassRepository;
import com.fag.infra.console.ConsoleUI;
import com.fag.infra.postgres.PostgresConnection;
import com.fag.infra.testdb.UserTestDBRepository;
import com.fag.service.Application;
import com.fag.service.BankingService;

public class Main {

    public static void main(String[] args) {

        ConsoleUserInterface consoleUI = new ConsoleUserInterface();
        SwingUserInterface swing = new SwingUserInterface();
        UserTestDB userTestDB = new UserTestDB();
        CelcoinBassRepository celcoinRepo = new CelcoinBassRepository();

        BankingService bankingService = new BankingService(consoleUI, userTestDB, celcoinRepo);

        Application app = new Application();

        PostgresConnection.getInstance();

        ConsoleUI consoleUI = new ConsoleUI();
        UserTestDBRepository userRepo = new UserTestDBRepository();

        app.execute(consoleUI, userRepo);
    }

}