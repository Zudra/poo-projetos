package com.fag.service;

import com.fag.domain.dto.UserAccountDTO;
import com.fag.domain.repositories.IBassRepository;
import com.fag.domain.repositories.IUserInterface;
import com.fag.domain.repositories.IUserRepository;

public class Application {

    public void execute(IUserInterface gui, IUserRepository userDB, IBassRepository bassRepo) {

        UserService userService = new UserService(gui, userDB);
        BankingService bankingService = new BankingService(gui, userDB, bassRepo);

        while (true) {
            Integer option = gui.showInitialScreenMenu();

            switch (option) {
                case 1:
                    UserAccountDTO user = userService.handleLogin();

                    if (user != null) {
                        bankingService.login(user);
                    }
                    break;
                case 2:
                    UserAccountDTO createdAcc = userService.handleOnboardingAcc();

                    if (createdAcc != null) {
                        bankingService.login(createdAcc);
                    }
                    break;
                case 3:
                    gui.showExitMessage();
                    return;
                default:
                    gui.showErrorMsg("Opção inválida!");
                    break;
            }
        }

    }

}