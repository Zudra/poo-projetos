package com.fag.service;

import com.fag.domain.dto.UserAccountDTO;
import com.fag.domain.repositories.IBassRepository;
import com.fag.domain.repositories.IUserInterface;
import com.fag.domain.repositories.IUserRepository;

public class BankingService {
    
    private IUserInterface userInterface;
    private IUserRepository userRepository;
    private IBassRepository bassRepository;
    private Integer accountNumber =  1;

    public BankingService(IUserInterface gui, IUserRepository userDB, IBassRepository bassRepository) {
        this.userInterface = gui;
        this.userRepository = userDB;
        this.bassRepository = bassRepository;
    }

    public void execute(UserAccountDTO dto) {
        Integer option = userInterface.showHomeMenu(dto.getName());

        System.out.println(option);
    }

}