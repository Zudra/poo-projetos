package com.fag;

import com.fag.infra.console.ConsoleUI;
import com.fag.infra.postgres.PostgresConnection;
import com.fag.infra.testdb.UserTestDBRepository;
import com.fag.service.Application;

public class Main {

    public static void main(String[] args) {

        Application app = new Application();

        PostgresConnection.getInstance();

        ConsoleUI consoleUI = new ConsoleUI();
        UserTestDBRepository userRepo = new UserTestDBRepository();

        app.execute(consoleUI, userRepo);

    }

}