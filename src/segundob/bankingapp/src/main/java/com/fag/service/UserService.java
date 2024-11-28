package com.fag.service;

import java.util.UUID;

import com.fag.domain.dto.LoginDTO;
import com.fag.domain.dto.UserAccountDTO;
import com.fag.domain.repositories.IUserInterface;
import com.fag.domain.repositories.IUserRepository;

public class UserService {
    
    private IUserInterface userInterface;

    private IUserRepository userRepository;

    public UserService(IUserInterface userInterface, IUserRepository userRepository) {
        this.userInterface = userInterface;
        this.userRepository = userRepository;
    }

    public UserAccountDTO handleLogin() {
        LoginDTO login = userInterface.getLoginData();

        UserAccountDTO user = userRepository.findUserBy(login.getDocument());

        if (user == null) {
            userInterface.showErrorMsg("Usuário não encontrado!");
        }
        
        return user;
    }

    public UserAccountDTO handleOnboardingAcc() {
        UserAccountDTO user = userInterface.getCreateUserData();

        user.setId(UUID.randomUUID().toString());

        userRepository.createUser(user);

        return user;
    }

}