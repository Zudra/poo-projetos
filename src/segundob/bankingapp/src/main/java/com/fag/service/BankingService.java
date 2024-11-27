package com.fag.service;

import com.fag.domain.dto.UserAccountDTO;
import com.fag.domain.repositories.IUserInterface;

public class BankingService {
    
    private IUserInterface userInterface;

    public BankingService(IUserInterface userInterface) {
        this.userInterface = userInterface;
    }

    public void execute(UserAccountDTO dto) {
        Integer option = userInterface.showHomeMenu(dto.getName());

        System.out.println(option);
    }

}